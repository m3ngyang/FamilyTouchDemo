package com.familytouch.service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.familytouch.data.Constant;
import com.familytouch.ui.ImageSwitcherPage;

public class CheckTimeoutService extends IntentService{

	public CheckTimeoutService() {
		super("CheckTimeoutService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		Log.i("CheckTimeoutService", "onHandleIntent()");
		
		ScheduledExecutorService excutor = Executors.newScheduledThreadPool(1);
		final ScheduledFuture<?> future = excutor.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				long time = System.currentTimeMillis();
				if(!Constant.isInSwitcherMode && (time - Constant.touchTime >= 8000)) {
					Constant.isInSwitcherMode = true;
					Log.i("CheckTimeoutService", "Bingo!");
					
					Intent intent = new Intent();
					intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					intent.setClass(getApplicationContext(), ImageSwitcherPage.class);
					startActivity(intent);
				}
			}
		}, 0, 1, TimeUnit.SECONDS);
		
	}

}
