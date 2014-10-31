package com.familytouch.ui;

import com.familytouch.R;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebBrowser extends Activity {
	Intent intent;
	WebView browserView;
	WebSettings cmWebSettings;
	String urlString;
	
//	public WebBrowser(String url) {
//		// TODO Auto-generated constructor stub
//		this.urlString = url;
//	}
	
	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_webbrowser);
		intent = getIntent();
		urlString = intent.getStringExtra("url");
		browserView = (WebView) findViewById(R.id.webbrowser);
		browserView.loadUrl(urlString);
		browserView.setWebViewClient(new WebViewClient());
		cmWebSettings = browserView.getSettings();
		cmWebSettings.setJavaScriptEnabled(true);
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
