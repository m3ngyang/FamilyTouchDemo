package com.familytouch;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;

import com.familytouch.ui.DevicePage;
import com.familytouch.ui.HomePage;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Intent ser = new Intent(this, LauncherService.class);
		startService(ser);
		init();
	}

	public void init() {
		startActivity(new Intent(MainActivity.this, HomePage.class));
		finish();
	}
}