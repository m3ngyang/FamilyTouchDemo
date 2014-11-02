/**
 * @author Kaffa
 */
package com.familytouch.control;

import java.util.HashMap;

import com.familytouch.R;
import com.familytouch.data.Constant;
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

		default:
			break;
		}

	}
}
