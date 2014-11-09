package com.familytouch;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;

import com.familytouch.ui.LoginPage;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		startActivity(new Intent(MainActivity.this, LoginPage.class));
		finish();
	}
}
