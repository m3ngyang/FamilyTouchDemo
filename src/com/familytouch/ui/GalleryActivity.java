package com.familytouch.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.Toast;

import com.familytouch.R;
import com.familytouch.control.BackOnClickLlistener;
import com.familytouch.control.GalleryImageAdapter;
import com.familytouch.control.HomeBtnOnClickListener;
import com.familytouch.control.SearchBtnOnClickListener;
import com.familytouch.data.Constant;

@SuppressWarnings("deprecation")
public class GalleryActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_gallerypage);
		
		Gallery gallery = (Gallery) findViewById(R.id.gallery);
		Intent intent = getIntent();
		int[] imgList = intent.getIntArrayExtra(Constant.GALLERYKEY);

		gallery.setAdapter(new GalleryImageAdapter(getApplicationContext(), imgList));
		gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
				Toast.makeText(getApplicationContext(), "Clicked.", Toast.LENGTH_SHORT).show();
			}
			
		});	
		
		ImageButton searchBtn = (ImageButton)findViewById(R.id.btn_search);
		searchBtn.setOnClickListener(new SearchBtnOnClickListener(this));
		ImageButton homeBtn = (ImageButton)findViewById(R.id.btn_home);
		homeBtn.setOnClickListener(new HomeBtnOnClickListener(this));
		ImageButton backBtn = (ImageButton)findViewById(R.id.btn_back);
		backBtn.setOnClickListener(new BackOnClickLlistener(this));
	}
}