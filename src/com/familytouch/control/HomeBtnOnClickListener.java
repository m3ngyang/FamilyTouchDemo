/**
 * @author Kaffa
 */
package com.familytouch.control;

import com.familytouch.data.Constant;
import com.familytouch.ui.HomePage;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

public class HomeBtnOnClickListener implements OnClickListener {
	Activity activity;
	
	public HomeBtnOnClickListener(Activity activity){
		this.activity = activity;
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Constant.touchTime = System.currentTimeMillis();
		activity.startActivity(new Intent(activity, HomePage.class));
	}
}
