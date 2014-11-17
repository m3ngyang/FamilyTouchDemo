package com.familytouch;

import android.os.Bundle;
import android.util.Log;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.familytouch.ui.HomePage;
import com.familytouch.ui.LoginPage;
import com.familytouch.ui.ScreenSaverActivity;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		BroadcastReceiver mMasterResetReceiver = new BroadcastReceiver() {

			@Override
			public void onReceive(Context context, Intent intent) {
				// TODO Auto-generated method stub
				try {
					Intent i = new Intent();
					i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					i.setClass(context, ScreenSaverActivity.class);
					Log.i("DEBUG", "broadcast");
					context.startActivity(i);
				} catch (Exception e) {
					// TODO: handle exception
					Log.i("Output", e.toString());
				}
				
			}
		};

		registerReceiver(mMasterResetReceiver, new IntentFilter(
				Intent.ACTION_SCREEN_OFF));

		startActivity(new Intent(MainActivity.this, HomePage.class));
	}
}