package com.example.a;

import android.annotation.SuppressLint;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



@SuppressLint({ "NewApi", "ValidFragment" })
public class Tab4 extends Fragment {
	TextView textView;
	EditText username;
    EditText password;
	 Button login,register;
	private Context context;
	UserService uService;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		 
		 View tab4= inflater.inflate(R.layout.activity_tab4, container,false);
		 textView=(TextView) tab4.findViewById(R.id.textView6);
		 textView.setText("tab4");
		 username=(EditText) tab4.findViewById(R.id.username);
		 password=(EditText) tab4.findViewById(R.id.password);
		 login=(Button) tab4.findViewById(R.id.login);
		 register=(Button) tab4.findViewById(R.id.Register);
		
		 uService=new UserService(this.context);
		login.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				String name=username.getText().toString();
				String pass=password.getText().toString();
				Log.i("TAG",name+"_"+pass);
				
				boolean flag=uService.login(name, pass);
				if(flag){
					Log.i("TAG","µÇÂ¼³É¹¦");
					Toast.makeText(getActivity(), "µÇÂ¼³É¹¦", Toast.LENGTH_LONG).show();
					//Intent intent=new Intent(getActivity(),Tab1.class);
					//startActivity(intent);
				}else{
					Log.i("TAG","µÇÂ¼Ê§°Ü");
					Toast.makeText(getActivity(), "µÇÂ¼Ê§°Ü", Toast.LENGTH_LONG).show();
				}
			}
		});
		register.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent=new Intent(getActivity(),RegisterActivity.class);
				startActivity(intent);
			}
		});
		 
		return tab4;
}
	public void onAttach(Activity activity){
		super.onAttach(activity);
		this.context=(MainActivity)activity;
	}
	
}
	
