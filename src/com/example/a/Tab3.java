package com.example.a;


 
import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



@SuppressLint("NewApi")
public class Tab3<extend> extends Fragment {
	TextView textView;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View tab3= inflater.inflate(R.layout.activity_tab3, container,false);
		 textView=(TextView) tab3.findViewById(R.id.textView5);
		 textView.setText("tab3");
		return tab3;
		
	}

}
