/**
 * @author Kaffa
 * HomePage.java存在如下问题
 * 1.两个维度对fragment进行了控制
 * 2.listView的只能线性布局，暂时用黑色块进行填充，并取消了Selector的黄色背景
 * 3.首页问题，暂时用height为1的黑色块填充
 */
package com.familytouch.ui;

import com.familytouch.R;
import com.familytouch.R.layout;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class HomePage2 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_homepage2);
		initView();
	}
	
	public void initView(){
		ImageButton remindBtn = (ImageButton)findViewById(R.id.left_remind);
		remindBtn.setOnClickListener(null);
		
		View mainView = findViewById(R.id.mainView);
 	}
}
