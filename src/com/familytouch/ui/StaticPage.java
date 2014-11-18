package com.familytouch.ui;

import com.familytouch.R;
import com.familytouch.control.BackOnClickLlistener;
import com.familytouch.control.HomeBtnOnClickListener;
import com.familytouch.control.SearchBtnOnClickListener;
import com.familytouch.data.Constant;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class StaticPage extends Activity {
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_staticpage);
		
		Intent intent = getIntent();
		int backgroundImg = intent.getIntExtra(Constant.STATICPAGEKEY, 0);
		
		LinearLayout bg = (LinearLayout) findViewById(R.id.staticBackground);
		bg.setBackgroundResource(backgroundImg);
		
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
