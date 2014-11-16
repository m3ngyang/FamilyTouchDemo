/**
 * @author Kaffa
 */
package com.familytouch.control;

import com.familytouch.data.Constant;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;

public class BackOnClickLlistener implements OnClickListener {
	Activity activity;
	
	public BackOnClickLlistener(Activity act){
		this.activity = act;
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Constant.touchTime = System.currentTimeMillis();
		activity.finish();
	}
}
