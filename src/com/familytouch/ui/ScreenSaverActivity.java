/*
 * @author yj
 * refactored by Kaffa
 */
package com.familytouch.ui;

import com.familytouch.R;
import com.familytouch.data.Constant;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

public class ScreenSaverActivity extends Activity {
	private WakeLock mWakeLock;
	private ImageSwitcher imageSwitcher;
	private int index = 0;

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_screen_saver);

		PowerManager pm = (PowerManager) getSystemService(POWER_SERVICE);
		mWakeLock = pm.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP
				| PowerManager.SCREEN_DIM_WAKE_LOCK
				| PowerManager.ON_AFTER_RELEASE, "SimpleTimer");

		init();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		mWakeLock.release();//must release() after acquire()
		super.onPause();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		mWakeLock.acquire();
		super.onResume();
	}

	protected void init() {
		imageSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher);
		imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
			@Override
			public View makeView() {
				ImageView imageView = new ImageView(ScreenSaverActivity.this);
				imageView.setScaleType(ImageView.ScaleType.FIT_XY);
				return imageView;
			}
		});
		imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(
				ScreenSaverActivity.this, android.R.anim.fade_in));
		imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(
				ScreenSaverActivity.this, android.R.anim.fade_out));
		imageSwitcher.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				Constant.isInSwitcherMode = false;
				Constant.touchTime = System.currentTimeMillis();
				finish();
				return false;
			}
		});
		imageSwitcher.setImageResource(Constant.IMG_SWITCHER[0]);

		new Thread(new Runnable() {// 每4s换图
					@Override
					public void run() {
						while (true) {
							try {
								Thread.sleep(4000);
								index++;
								if (index >= Constant.IMG_SWITCHER.length)
									index = 0;

								Message message = new Message();
								message.arg1 = index;
								handler.sendMessage(message);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
				}).start();
	}

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			imageSwitcher.setImageResource(Constant.IMG_SWITCHER[msg.arg1]);
			super.handleMessage(msg);
		};
	};
}
