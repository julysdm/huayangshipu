package com.example.a;
import java.io.File;
import java.io.IOException;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class Tab3<extend> extends Fragment {
	TextView textView;
	private static final String tag = "MainActivity";
	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	private static final int PICK_IMAGE_ACTIVITY_REQUEST_CODE = 200;
	private static final int RESULT_OK = 0;
	private static final int RESULT_CANCELED = 0;

	private ImageView imageView;
	private Context context;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View tab3= inflater.inflate(R.layout.activity_tab3, container,false);
		 textView=(TextView) tab3.findViewById(R.id.textView5);
		 //textView.setText("tab3");
		 super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_tab3);

			imageView = (ImageView) tab3.findViewById(R.id.image_view);

			Button button = (Button) tab3.findViewById(R.id.open_camera);
			button.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					takePicture();
				}
			});

			Button pickImageBtn = (Button) tab3.findViewById(R.id.pick_image);
			pickImageBtn.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					openAlbum();
				}
			});
			return tab3;

		}

		private void setContentView(int activityTab3) {
		// TODO Auto-generated method stub
		
	}

		private static String picFileFullName;
		//拍照
	    public void takePicture(){
	    	String state = Environment.getExternalStorageState();  
	        if (state.equals(Environment.MEDIA_MOUNTED)) {  
	            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); 
	            File outDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);  
	            if (!outDir.exists()) {  
	            	outDir.mkdirs();  
	            }  
	            File outFile =  new File(outDir, System.currentTimeMillis() + ".jpg");  
	            picFileFullName = outFile.getAbsolutePath();
	            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(outFile));  
	            intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);  
	            startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);  
	        } else{
	        	Log.e(tag, "请确认已经插入SD卡");
	        }
	    }
	    
	    //打开本地相册
	    public void openAlbum(){
	    	Intent intent = new Intent();
	    	intent.setType("image/*");   
	        intent.setAction(Intent.ACTION_GET_CONTENT);   
	    	this.startActivityForResult(intent, PICK_IMAGE_ACTIVITY_REQUEST_CODE);
	    }
	    
	 	@Override
	 	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	 		super.onActivityResult(requestCode, resultCode, data);
	 		if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
	 			if (resultCode == RESULT_OK) {
	 				Log.e(tag, "获取图片成功，path="+picFileFullName);
	 				toast("获取图片成功，path="+picFileFullName);
	 				setImageView(picFileFullName);
	 			} else if (resultCode == RESULT_CANCELED) {
	 				// 用户取消了图像捕获
	 			} else {
	 				// 图像捕获失败，提示用户
	 				Log.e(tag, "拍照失败");
	 			}
	 		} else if (requestCode == PICK_IMAGE_ACTIVITY_REQUEST_CODE) {
	 			if (resultCode == RESULT_OK) {
	 				Uri uri = data.getData();
	 				if(uri != null){
	 					String realPath = getRealPathFromURI(uri);
	 					Log.e(tag, "获取图片成功，path="+realPath);
	 					toast("获取图片成功，path="+realPath);
	 					setImageView(realPath);
	 				}else{
	 					Log.e(tag, "从相册获取图片失败");
	 				}
	 			}
	 		}
	 	}
	 	
	 	private void setImageView(String realPath){
	 		Bitmap bmp = BitmapFactory.decodeFile(realPath);
	 		int degree = readPictureDegree(realPath);
	 		if(degree <= 0){
	 			imageView.setImageBitmap(bmp);
	 		}else{
	 			Log.e(tag, "rotate:"+degree);
	 			//创建操作图片是用的matrix对象
	 	 		Matrix matrix=new Matrix();
	 	 		//旋转图片动作
	 	 		matrix.postRotate(degree);
	 	 		//创建新图片
	 	 		Bitmap resizedBitmap=Bitmap.createBitmap(bmp,0,0,bmp.getWidth(),bmp.getHeight(),matrix,true);
	 	 		imageView.setImageBitmap(resizedBitmap);
	 		}
	 	}
	 	
	 	/**
	     * This method is used to get real path of file from from uri<br/>
	     * http://stackoverflow.com/questions/11591825/how-to-get-image-path-just-captured-from-camera
	     * 
	     * @param contentUri
	     * @return String
	     */
		public String getRealPathFromURI(Uri contentUri){
	        try{
	            String[] proj = {MediaStore.Images.Media.DATA};
	            // Do not call Cursor.close() on a cursor obtained using this method, 
	            // because the activity will do that for you at the appropriate time
	            MainActivity activity = (MainActivity)getActivity();
	            Cursor cursor = activity.managedQuery(contentUri, proj, null,
						null, null);

	            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
	            cursor.moveToFirst();
	            return cursor.getString(column_index);
	        }catch (Exception e){
	            return contentUri.getPath();
	        }
		}
		
		/** 
	     * 读取照片exif信息中的旋转角度<br/>
	     * http://www.eoeandroid.com/thread-196978-1-1.html
	     * 
	     * @param path 照片路径 
	     * @return角度 
	     */  
	    public static int readPictureDegree(String path) {  
	            int degree  = 0;  
	            try {  
	                    ExifInterface exifInterface = new ExifInterface(path);  
	                    int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);  
	                    switch (orientation) {  
	                    case ExifInterface.ORIENTATION_ROTATE_90:  
	                            degree = 90;  
	                            break;  
	                    case ExifInterface.ORIENTATION_ROTATE_180:  
	                            degree = 180;  
	                            break;  
	                    case ExifInterface.ORIENTATION_ROTATE_270:  
	                            degree = 270;  
	                            break;  
	                    }  
	            } catch (IOException e) {  
	                    e.printStackTrace();  
	            }  
	            return degree;  
	    }  
		
		public void toast(String msg){
			Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
		} 
	

      public void onAttach(Activity activity){
	     super.onAttach(activity);
	     this.context=(MainActivity)activity;
}

}
