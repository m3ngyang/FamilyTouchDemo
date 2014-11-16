package com.familytouch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.familytouch.data.Constant;
import com.familytouch.service.CheckTimeoutService;
import com.familytouch.ui.HomePage;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		startActivity(new Intent(MainActivity.this, HomePage.class));
		Constant.touchTime = System.currentTimeMillis();
		startService(new Intent(MainActivity.this, CheckTimeoutService.class));
		finish();
	}
}
