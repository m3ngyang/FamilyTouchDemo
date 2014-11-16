/**
 * @author Kaffa
 */
package com.familytouch.ui;

import com.familytouch.R;
import com.familytouch.control.BackOnClickLlistener;
import com.familytouch.control.HomeBtnOnClickListener;
import com.familytouch.control.SearchBtnOnClickListener;
import com.familytouch.control.WholeViewOnTouchListener;
import com.familytouch.data.Constant;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;

public class WebBrowser extends Activity {
	Intent intent;
	WebView browserView;
	WebSettings cmWebSettings;
	String urlString;
	
	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.aty_webpage);
		intent = getIntent();
		urlString = intent.getStringExtra(Constant.WEBURL);
		browserView = (WebView) findViewById(R.id.webbrowser);
		browserView.loadUrl(urlString);
		browserView.setWebViewClient(new WebViewClient());
		cmWebSettings = browserView.getSettings();
		cmWebSettings.setJavaScriptEnabled(true);
		
		ImageButton searchBtn = (ImageButton) findViewById(R.id.btn_search);
		searchBtn.setOnClickListener(new SearchBtnOnClickListener(this));
		ImageButton homeBtn = (ImageButton)findViewById(R.id.btn_home);
		homeBtn.setOnClickListener(new HomeBtnOnClickListener(this));
		ImageButton backBtn = (ImageButton)findViewById(R.id.btn_back);
		backBtn.setOnClickListener(new BackOnClickLlistener(this));
		
		findViewById(R.id.webpage).setOnTouchListener(new WholeViewOnTouchListener(this));
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
