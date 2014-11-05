package com.familytouch.ui;

import com.familytouch.R;
import com.familytouch.data.Constant;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
	}
	
}
