package com.familytouch.control;

import android.app.Activity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.familytouch.data.Constant;

public class WholeViewOnTouchListener implements View.OnTouchListener{
	Activity aty;
	
	public WholeViewOnTouchListener(Activity aty){
		this.aty = aty;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		Constant.touchTime = System.currentTimeMillis();
//		Log.i("WholeViewOnTouchListener", "touched");
		return true;
	}

}
