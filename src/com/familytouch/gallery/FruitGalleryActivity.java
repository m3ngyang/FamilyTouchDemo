package com.familytouch.gallery;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;

import com.familytouch.R;
import com.familytouch.control.GalleryImageAdapter;
import com.familytouch.data.Constant;

public class FruitGalleryActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_gallerypage);
		
		Gallery gallery = (Gallery) findViewById(R.id.gallery);
		gallery.setAdapter(new GalleryImageAdapter(getApplicationContext(), Constant.IMG_FRUIT));
		gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
				Toast.makeText(getApplicationContext(), "Clicked.", Toast.LENGTH_SHORT).show();
			}
			
		});
		
	}
	
	

}
