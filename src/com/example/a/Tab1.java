package com.example.a;

import android.annotation.SuppressLint;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;




@SuppressLint("NewApi")
public class Tab1 extends Fragment{
	
	Button search;
	ListView listView1;
	EditText editText1;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View tab1= inflater.inflate(R.layout.activity_tab1, container,false);
		listView1 = (ListView) tab1.findViewById(R.id.listView1);
	    editText1 = (EditText) tab1.findViewById(R.id.editText1);
		
		search=(Button)tab1.findViewById(R.id.search);
		search.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Object recipe;
				Intent intent=new Intent(getActivity(),Recipe.class);
				startActivity(intent);
			}
		});
		return tab1;
		
	}
	
	 
}
