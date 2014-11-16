/**
 * @author Kaffa
 */
package com.familytouch.ui;

import com.familytouch.R;
import com.familytouch.control.BackOnClickLlistener;
import com.familytouch.control.HomeBtnOnClickListener;
import com.familytouch.control.WholeViewOnTouchListener;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageButton;

public class SearchPage extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.aty_searchpage);

		ImageButton backBtn = (ImageButton) findViewById(R.id.btn_back);
		backBtn.setOnClickListener(new BackOnClickLlistener(this));

		ImageButton homeBtn = (ImageButton) findViewById(R.id.btn_home);
		homeBtn.setOnClickListener(new HomeBtnOnClickListener(this));
		
		findViewById(R.id.searchpage).setOnTouchListener(new WholeViewOnTouchListener(this));
	}
}
