/**
 * @author Kaffa
 */
package com.familytouch.view;

import java.util.ArrayList;
import java.util.HashMap;
import com.familytouch.R;
import com.familytouch.data.Constant;
import com.familytouch.entity.GridEntity;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.SimpleAdapter;

public class SquarePage {
	private static View view;
	private static GridView gridView;
	@SuppressLint("InflateParams")
	public static View generateSquarePage(Context context,ArrayList<GridEntity> list) {
		view = LayoutInflater.from(context).inflate(R.layout.layout_squaregridview, null);
		gridView = (GridView) view.findViewById(R.id.squaregridview);
		
		ArrayList<HashMap<String, Object>> lstItem = new ArrayList<HashMap<String, Object>>();
		for (int j = 0; j < list.size(); j++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put(Constant.ICOKEY, list.get(j).getGridIcon());
			map.put(Constant.LABKEY, context.getString(list.get(j).getGridLabel()));
			lstItem.add(map);
		}
		SimpleAdapter itemAdapter = new SimpleAdapter(context, lstItem,
				R.layout.grid_item, new String[] { Constant.ICOKEY, Constant.LABKEY },
				new int[] { R.id.grid_item_icon, R.id.grid_item_label });
		gridView.setAdapter(itemAdapter);
		gridView.setOnItemClickListener(null);
		return view;
	}
}
