package com.familytouch.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.familytouch.R;
import com.familytouch.control.WholeViewOnTouchListener;
import com.familytouch.data.Constant;

public class LoginPage extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_loginpage);
		
		Button btnLogin = (Button) findViewById(R.id.btnlogin);
		btnLogin.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Constant.touchTime = System.currentTimeMillis();
				startActivity(new Intent(LoginPage.this, HomePage.class));
				finish();
			}
		});
		
		findViewById(R.id.loginpage).setOnTouchListener(new WholeViewOnTouchListener(this));
	}
	
}
