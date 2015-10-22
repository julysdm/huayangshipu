package com.example.a;

import android.annotation.SuppressLint;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;




@SuppressLint("NewApi")
public class Tab1 extends Fragment{
	TextView textView;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View tab1= inflater.inflate(R.layout.activity_tab1, container,false);
		 textView=(TextView) tab1.findViewById(R.id.textView1);
		 textView.setText("tab1");
		return tab1;
		
	}
	
	 
}
