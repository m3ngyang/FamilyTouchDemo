/**
 * @author Kaffa
 */
package com.familytouch.util;

import java.util.ArrayList;
import java.util.HashMap;

import com.familytouch.R;
import com.familytouch.data.GridEntity;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

public class GridViewPagerFactory {
	public static final int ICONNUMPERPAGE = 8;
	public static final String ICOKEY = "iconKey";
	public static final String LABKEY = "labelKey";

	private static View view = null;
	private static ViewPager viewPager = null;
	private static View page = null;
	private static ArrayList<View> pageList = null;
	private static GridView gridView = null;
	private static ArrayList<GridView> gridViewList = null;
	private static ViewGroup pointGroup = null;
	private static ImageView[] indicators;
	private static ViewPagerAdapter viewPagerAdapter = null;
	private static ViewPagerPageChangeListener viewPagerListener = null;

	public static View getViewPager(Context context,
			ArrayList<GridEntity> arrayList) {
		view = LayoutInflater.from(context).inflate(R.layout.layout_viewpager,
				null);
		viewPager = (ViewPager) view.findViewById(R.id.viewpager);
		pointGroup = (ViewGroup) view.findViewById(R.id.indicators);
		
		int pageNum = (arrayList.size() % ICONNUMPERPAGE > 0) ? arrayList
				.size() / ICONNUMPERPAGE + 1 : arrayList.size()
				/ ICONNUMPERPAGE;

		pageList = new ArrayList<View>();
		gridViewList = new ArrayList<GridView>();
		for (int i = 0; i < pageNum; i++) {
			page = LayoutInflater.from(context).inflate(
					R.layout.layout_gridview, null);
			pageList.add(page);
			gridView = (GridView) page.findViewById(R.id.gridview);
			gridViewList.add(gridView);
		}
		
		//test
//		boolean p0 = (gridViewList.get(0).getParent()== pageList.get(0));
//		boolean p1 = (gridViewList.get(1).getParent()== pageList.get(1));
//		boolean p2 = (gridViewList.get(2).getParent()== pageList.get(2));
//		
//		boolean b0 = (gridViewList.get(0).getParent().equals(pageList.get(0)));
//		boolean b1 = (gridViewList.get(1).getParent().equals(pageList.get(1)));
//		boolean b2 = (gridViewList.get(2).getParent().equals(pageList.get(2)));
		
		int intPart = arrayList.size() / ICONNUMPERPAGE;
		int modPart = arrayList.size() % ICONNUMPERPAGE;

		ArrayList<ArrayList<GridEntity>> lstlst = new ArrayList<ArrayList<GridEntity>>();
		for (int i = 0; i < pageNum; i++) {
			ArrayList<GridEntity> beanLst = new ArrayList<GridEntity>();
			lstlst.add(beanLst);
		}

		for (int i = 0; i < intPart; i++) {
			ArrayList<GridEntity> beanLst = lstlst.get(i);
			for (int j = 0; j < ICONNUMPERPAGE; j++) {
				beanLst.add(arrayList.get(i * ICONNUMPERPAGE + j));
			}
			ArrayList<HashMap<String, Object>> lstItem = new ArrayList<HashMap<String, Object>>();
			for (int j = 0; j < beanLst.size(); j++) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put(ICOKEY, beanLst.get(j).getGridIcon());
				map.put(LABKEY, beanLst.get(j).getGridLabel());
				lstItem.add(map);
			}
			SimpleAdapter itemAdapter = new SimpleAdapter(context, lstItem,
					R.layout.grid_item, new String[] { ICOKEY, LABKEY },
					new int[] { R.id.grid_item_icon, R.id.grid_item_label });
			gridViewList.get(i).setAdapter(itemAdapter);
			gridViewList.get(i).setOnItemClickListener(null);
		}

		if (modPart != 0) {
			ArrayList<GridEntity> beanLst = lstlst.get(lstlst.size() - 1);
			for (int i = 0; i < modPart; i++) {
				beanLst.add(arrayList.get(intPart * ICONNUMPERPAGE + i));
			}
			ArrayList<HashMap<String, Object>> lstItem = new ArrayList<HashMap<String, Object>>();
			for (int j = 0; j < beanLst.size(); j++) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put(ICOKEY, beanLst.get(j).getGridIcon());
				map.put(LABKEY, beanLst.get(j).getGridLabel());
				lstItem.add(map);
			}
			SimpleAdapter itemAdapter = new SimpleAdapter(context, lstItem,
					R.layout.grid_item, new String[] { ICOKEY, LABKEY },
					new int[] { R.id.grid_item_icon, R.id.grid_item_label });
			gridViewList.get(gridViewList.size() - 1).setAdapter(itemAdapter);
			gridViewList.get(gridViewList.size() - 1).setOnItemClickListener(
					null);
		}

		
		
		indicators = new ImageView[pageNum];
		for (int i = 0; i < indicators.length; i++) {
			ImageView point = new ImageView(context);
			point.setLayoutParams(new LayoutParams(12, 12));
			point.setPadding(10, 0, 10, 0);
			indicators[i] = point;
			if (i == 0) {
				indicators[i]
						.setBackgroundResource(R.drawable.page_indicator_focused);
			} else {
				indicators[i]
						.setBackgroundResource(R.drawable.page_indicator_unfocused);
			}
			pointGroup.addView(point);
		}

		viewPagerAdapter = new ViewPagerAdapter(pageList);
		viewPager.setAdapter(viewPagerAdapter);
		viewPagerListener = new ViewPagerPageChangeListener();
		viewPager.setOnPageChangeListener(viewPagerListener);

		return view;
	}

	public static class ViewPagerAdapter extends PagerAdapter {
		private ArrayList<View> list;
		
		public ViewPagerAdapter(ArrayList<View> list){
			this.list = list;
		}
		
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0 == (View) arg0;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub
			container.removeView(list.get(position));
			Log.i("debug", "container deletes page "+position);
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			// TODO Auto-generated method stub
			container.addView(list.get(position),0);
			Log.i("debug", "container adds page "+position);
			return list.get(position);
		}

		@Override
		public Parcelable saveState() {
			// TODO Auto-generated method stub
			return super.saveState();
		}

		@Override
		public void setPrimaryItem(ViewGroup container, int position,
				Object object) {
			// TODO Auto-generated method stub
			super.setPrimaryItem(container, 0, object);
		}
	}

	public static class ViewPagerPageChangeListener implements
			OnPageChangeListener {
		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPageSelected(int arg0) {
			// TODO Auto-generated method stub
			updateIndicatorState(arg0);
		}

		private void updateIndicatorState(int index) {
			Log.i("debug", "current indicator "+ index);
			for (int i = 0; i < indicators.length; i++) {
				if (i == index) {
					indicators[i]
							.setBackgroundResource(R.drawable.page_indicator_focused);
				} else {
					indicators[i]
							.setBackgroundResource(R.drawable.page_indicator_unfocused);
				}
			}
		}
	}
}
