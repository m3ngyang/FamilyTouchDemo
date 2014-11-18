package com.familytouch.ui;

import com.familytouch.R;
import com.familytouch.R.id;
import com.familytouch.R.layout;
import com.familytouch.control.BackOnClickLlistener;
import com.familytouch.control.HomeBtnOnClickListener;
import com.familytouch.control.SearchBtnOnClickListener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;

public class DevicePage extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_devicepage);
		
		Switch switcher = (Switch) findViewById(R.id.phoneSwitch);
		switcher.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked){
					startActivity(new Intent(getApplicationContext(), LoginPage.class));
				}
			}
		});
		
		ImageButton searchBtn = (ImageButton) findViewById(R.id.btn_search);
		searchBtn.setOnClickListener(new SearchBtnOnClickListener(this));
		ImageButton homeBtn = (ImageButton)findViewById(R.id.btn_home);
		homeBtn.setOnClickListener(new HomeBtnOnClickListener(this));
		ImageButton backBtn = (ImageButton)findViewById(R.id.btn_back);
		backBtn.setOnClickListener(new BackOnClickLlistener(this));
		
		ImageButton iconBtn = (ImageButton)findViewById(R.id.btn_appicon);
		iconBtn.setOnClickListener(new HomeBtnOnClickListener(this));
	}

}