package com.example.tabs_viewpage;

import android.annotation.SuppressLint;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



@SuppressLint({ "NewApi", "ValidFragment" })
public class Tab4 extends Fragment {
	TextView textView;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View tab4= inflater.inflate(R.layout.tab4, container,false);
		 textView=(TextView) tab4.findViewById(R.id.textView4);
		 textView.setText("tab4");
		return tab4;
		
	}
	 
}
