package com.familytouch;

import com.familytouch.gallery.CourseGalleryActivity;
import com.familytouch.gallery.FruitGalleryActivity;
import com.familytouch.gallery.GroupbuyGalleryActivity;
import com.familytouch.gallery.NearbyGalleryActivity;
import com.familytouch.gallery.PetGalleryActivity;
import com.familytouch.ui.HomePage;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
//		startActivity(new Intent(MainActivity.this, HomePage.class));
		startActivity(new Intent(MainActivity.this, FruitGalleryActivity.class));
//		startActivity(new Intent(MainActivity.this, NearbyGalleryActivity.class));
//		startActivity(new Intent(MainActivity.this, GroupbuyGalleryActivity.class));
//		startActivity(new Intent(MainActivity.this, CourseGalleryActivity.class));
//		startActivity(new Intent(MainActivity.this, PetGalleryActivity.class));
		
		finish();
	}
}
