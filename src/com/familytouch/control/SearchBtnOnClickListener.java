/**
 * @author Kaffa
 */
package com.familytouch.control;

import com.familytouch.data.Constant;
import com.familytouch.ui.SearchPage;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class SearchBtnOnClickListener implements OnClickListener {
	Activity activity;
	
	public SearchBtnOnClickListener(Activity act){
		this.activity = act;
	}
	
//	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Log.i("debug","listening");
		Constant.touchTime = System.currentTimeMillis();
		activity.startActivity(new Intent(activity,SearchPage.class));
	}
}