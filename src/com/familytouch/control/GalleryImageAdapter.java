package com.familytouch.control;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

public class GalleryImageAdapter extends BaseAdapter {
	private Context context;
	private int[] imgs;
	
	public GalleryImageAdapter(Context context, int[] imgs) {
		this.context = context;
		this.imgs = imgs;
	}

	public int getCount() { 
		return imgs.length;
	}

	public Object getItem(int position) {
		return position;
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView image = new ImageView(context);
		image.setImageResource(imgs[position]);
		image.setAdjustViewBounds(true);
		image.setLayoutParams(new Gallery.LayoutParams(320, 370));
		image.setPadding(40, 0, 40, 0);
		return image;
	}
}
