package com.example.tabs_viewpage;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;




@SuppressLint("NewApi")
public class Tab1 extends Fragment{
	TextView textView;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View tab1= inflater.inflate(R.layout.tab1, container,false);
		 textView=(TextView) tab1.findViewById(R.id.textView1);
		 textView.setOnClickListener(new MyOnClickListener());
		return tab1;
		
	}
	
	class MyOnClickListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.textView1:
				Toast.makeText(getActivity().getApplicationContext(), "µã»÷ÁË", 1).show();
				break;

			default:
				break;
			}
		}
		
	}
}
