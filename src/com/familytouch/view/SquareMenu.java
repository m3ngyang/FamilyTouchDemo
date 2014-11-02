/**
 * @author Kaffa
 */
package com.familytouch.view;

import java.util.ArrayList;

import com.familytouch.R;
import com.familytouch.data.Constant;
import com.familytouch.entity.GridEntity;
import com.familytouch.util.GridEntityGenerater;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class SquareMenu extends Activity {
	private LinearLayout layout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.layout_squaremenu);
		layout = new LinearLayout(this);
		setContentView(layout);	//important
		
		Intent intent = getIntent();
		int[] lab = intent.getIntArrayExtra(Constant.LABLISTKEY);
		int[] img = intent.getIntArrayExtra(Constant.ICOLISTKEY);
		ArrayList<GridEntity> arrayList = GridEntityGenerater.generate(img,lab);
		
		View view = SquarePage.generateSquarePage(getApplicationContext(), arrayList);
		layout.addView(view);
	}
}
