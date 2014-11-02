package com.familytouch;

import com.familytouch.ui.HomePage;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		startActivity(new Intent(MainActivity.this, HomePage.class));
		
		finish();
	}
}
