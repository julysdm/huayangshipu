package com.example.search;
//import com.example.phone1.R;
//import com.example.phone1.search;

import java.util.ArrayList;
import java.util.List;

import model.Search_out;



import com.example.myinterface.firstinside;
import com.example.sqlite.Younidb;
//import com.example.sqlite.Younidb;
//import com.example.sqlite.search_DatabaseHelper;
//import com.example.youni.Firstpanel;
//import com.example.youni.Firstpanel;
import com.example.zz.R;
import com.example.zz.first_inside_Adapter;

import android.os.Bundle;
import android.app.Activity;
//import android.content.Intent;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class searchpanel extends Activity {
    private Younidb younidb;
//    private search_DatabaseHelper dbHelper;
    private List<Search_out> searchoutList;
    private List<firstinside> firstinsideList=new ArrayList<firstinside>();
	private List<String>dataList=new ArrayList<String>();
	static String tab_field02 = "detailed";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search);
//		younidb=Younidb.getInstance(this);
//		initfirstinsides();
//		first_inside_Adapter adapter=new first_inside_Adapter(searchpanel.this,R.layout.first_inside,firstinsideList);
//		ListView listView =(ListView)findViewById(R.id.listView1);
//		listView.setAdapter(adapter);
		Button search=(Button)findViewById(R.id.button1);
		search.setOnClickListener(new OnClickListener(){
	    	public  void onClick(View v){
	    		initfirstinsides();
				first_inside_Adapter adapter=new first_inside_Adapter(searchpanel.this,R.layout.first_inside,firstinsideList);
	    		ListView listView =(ListView)findViewById(R.id.lv_server);
	    		listView.setAdapter(adapter);
//	    		Intent intent = new Intent(searchpanel.this,search.class);
//	    		startActivity(intent);
//	    		EditText editText=(EditText)findViewById(R.id.editText1);
//				String search=editText.getText().toString();
//				SQLiteDatabase db=dbHelper.getWritableDatabase();
//				List<Search_out> list =new ArrayList<Search_out>();
//				String current_sql_sel = "SELECT  * FROM "+"search_out" +" where "+tab_field02+" like '%"+search+"%'";
//				Cursor cursor=db.rawQuery(current_sql_sel, null);
//				if(cursor.moveToFirst()){
//					do{
//						Search_out search_out=new Search_out();
//						search_out.setId(cursor.getInt(cursor.getColumnIndex("id")));
//						search_out.setName(cursor.getString(cursor.getColumnIndex("name")));
//						search_out.setDetailed(cursor.getString(cursor.getColumnIndex("detailed")));
//						search_out.setLike(cursor.getString(cursor.getColumnIndex("like")));
//						search_out.setHistory(cursor.getString(cursor.getColumnIndex("history")));
//						search_out.setTime(cursor.getString(cursor.getColumnIndex("time")));
//						list.add(search_out);
//					}while(cursor.moveToNext());
//				}
//				if(list.size()>0){
//					dataList.clear();
//					for(Search_out search_out:list){
//						dataList.add(search_out.getName());
//						firstinside fi1=new firstinside(search_out.getName(),R.drawable.showpic,search_out.getDetailed());
//						firstinsideList.add(fi1);
//					}
//				}
			}
				
	    	
	    });

	}
	private void  initfirstinsides (){
		EditText editText=(EditText)findViewById(R.id.et_search);
		String search=editText.getText().toString();
		younidb=Younidb.getInstance(this);
		searchoutList=younidb.loadSearch_out(tab_field02,search);
		// TODO Auto-generated method stub
		if(searchoutList.size()>0){
			dataList.clear();
			for(Search_out search_out:searchoutList){
				dataList.add(search_out.getName());
				firstinside fi1=new firstinside(search_out.getName(),R.drawable.showpic,search_out.getDetailed());
				firstinsideList.add(fi1);
				
			}
		}else{
			firstinside fi1=new firstinside("ÎÞ",R.drawable.showpic,"ÎÞÄÚÈÝ");
			firstinsideList.add(fi1);
		}
	}

	//@Override
	//public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.main, menu);
		//return true;
	//}

}

