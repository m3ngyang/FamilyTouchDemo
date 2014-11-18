package com.familytouch.ui;

import com.familytouch.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CompoundButton;
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
	}

}
