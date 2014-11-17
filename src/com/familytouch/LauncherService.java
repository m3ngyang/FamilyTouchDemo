//package com.familytouch;
//
//import com.familytouch.ui.LoginPage;
//import com.familytouch.ui.ScreenSaverActivity;
//
//import android.app.Service;
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.content.IntentFilter;
//import android.os.IBinder;
//import android.provider.Settings;
//import android.util.Log;
//
//public class LauncherService extends Service {
//	final int timeOut = 8;
//
//	@Override
//	public void onCreate() {
//		// TODO Auto-generated method stub
//
//		// change the screen off time of the system
//		android.provider.Settings.System.putInt(getContentResolver(),
//				Settings.System.SCREEN_OFF_TIMEOUT, timeOut * 1000);
//
//		BroadcastReceiver mMasterResetReceiver = new BroadcastReceiver() {
//
//			@Override
//			public void onReceive(Context context, Intent intent) {
//				// TODO Auto-generated method stub
//				try {
//					Intent i = new Intent();
//					i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//					i.setClass(context, ScreenSaverActivity.class);
//					Log.i("DEBUG", "broadcast");
//					context.startActivity(i);
//				} catch (Exception e) {
//					// TODO: handle exception
//					Log.i("Output", e.toString());
//				}
//
//			}
//		};
//
//		registerReceiver(mMasterResetReceiver, new IntentFilter(
//				Intent.ACTION_SCREEN_OFF));
//		init();
//
//		super.onCreate();
//	}
//
//	@Override
//	public IBinder onBind(Intent arg0) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	public void init() {
//		startActivity(new Intent(this, LoginPage.class));
//	}
//}
