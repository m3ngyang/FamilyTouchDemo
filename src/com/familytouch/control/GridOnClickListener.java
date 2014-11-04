/**
 * @author Kaffa
 */
package com.familytouch.control;

import java.util.HashMap;

import com.familytouch.R;
import com.familytouch.data.Constant;
import com.familytouch.ui.GalleryActivity;
import com.familytouch.ui.WebBrowser;
import com.familytouch.view.SquareMenu;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class GridOnClickListener implements OnItemClickListener {
	private static GridOnClickListener instance = null;
	private Context context;
	private Intent intent;

	public GridOnClickListener(Context context) {
		this.context = context;
	}

	public static GridOnClickListener getInstance(Context context) {
		if (instance == null) {
			instance = new GridOnClickListener(context);
		}
		return instance;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long rowid) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		HashMap<String, Object> itemMap = (HashMap<String, Object>) parent
				.getItemAtPosition(position);
		int img = (Integer) itemMap.get(Constant.ICOKEY);
		switch (img) {
		case R.drawable.deliver:
			intent = new Intent(context, SquareMenu.class);
			intent.putExtra(Constant.ICOLISTKEY, Constant.deliveryImg);
			intent.putExtra(Constant.LABLISTKEY, Constant.deliveryLab);
			context.startActivity(intent);
			break;

		case R.drawable.callservice:
			intent = new Intent(context, SquareMenu.class);
			intent.putExtra(Constant.ICOLISTKEY, Constant.callServiceImg);
			intent.putExtra(Constant.LABLISTKEY, Constant.callServiceLab);
			context.startActivity(intent);
			break;

		case R.drawable.buy:
			intent = new Intent(context, SquareMenu.class);
			intent.putExtra(Constant.ICOLISTKEY, Constant.buyImg);
			intent.putExtra(Constant.LABLISTKEY, Constant.buyLab);
			context.startActivity(intent);
			break;

		case R.drawable.theoldservice:
			intent = new Intent(context, SquareMenu.class);
			intent.putExtra(Constant.ICOLISTKEY, Constant.oldserviceImg);
			intent.putExtra(Constant.LABLISTKEY, Constant.oldserviceLab);
			context.startActivity(intent);
			break;
		
		case R.drawable.parentchildren:
			intent = new Intent(context, GalleryActivity.class);
			intent.putExtra(Constant.GALLERYKEY, Constant.IMG_PARENTCHILDREN);
			context.startActivity(intent);
			break;
		
		case R.drawable.nearbyactivity:
			intent = new Intent(context,GalleryActivity.class);
			intent.putExtra(Constant.GALLERYKEY, Constant.IMG_NEARBYACTIVITY);
			context.startActivity(intent);
			break;
			
		case R.drawable.pet:
			intent = new Intent(context,GalleryActivity.class);
			intent.putExtra(Constant.GALLERYKEY, Constant.IMG_PET);
			context.startActivity(intent);
			break;

		case R.drawable.groupbuy:
			intent = new Intent(context,GalleryActivity.class);
			intent.putExtra(Constant.GALLERYKEY, Constant.IMG_GROUPBUY);
			context.startActivity(intent);
			break;
			
		case R.drawable.fruit:
			intent = new Intent(context,GalleryActivity.class);
			intent.putExtra(Constant.GALLERYKEY, Constant.IMG_FRUIT);
			context.startActivity(intent);
			break;
			
		case R.drawable.fastfood:
			intent = new Intent(context,WebBrowser.class);
			intent.putExtra(Constant.WEBURL, "http://v5.ele.me");
			context.startActivity(intent);
			break;
			
		case R.drawable.ticket:
			intent = new Intent(context,WebBrowser.class);
			intent.putExtra(Constant.WEBURL, "http://www.gewara.com/");
			context.startActivity(intent);
			break;
		
		default:
			break;
		}
	}
}
