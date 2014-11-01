/**
 * @author Kaffa
 */
package com.familytouch.view;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

import com.familytouch.R;

public class GridViewPager {
	protected static ViewPager viewPager;
	protected static ViewGroup pointViewGroup;
	private static ImageView[] mIndicator;

	// 左边title对应的fragment
	private static View mMainPager;

	private static ArrayList<View> mMainBody0Array;
	
	private final static String LABKEY = "labelKey";
	public final static String ICOKEY = "iconKey";
	private final static int MAINBODY1PAGENUM = 4; // TODO: 设置页面数量
	
	public static View getGridViewPager(Context context){
		mMainPager = LayoutInflater.from(context).inflate(
				R.layout.layout_viewpager, null);
		ArrayList<View> pageList = new ArrayList<View>();
		ArrayList<GridView> gridViewList = new ArrayList<GridView>();
		for (int i = 0; i < 4; i++) {
			View page = LayoutInflater.from(context).inflate(R.layout.layout_gridview, null);
			GridView gridView = (GridView) page.findViewById(R.id.gridview);
			pageList.add(page);
			gridViewList.add(gridView);
		}

		String[] testLab = new String[] { "fastfood", "express", "taxi",
				"around", "groupbuy", "bank", "drylundry", "recharge" };
		int[] testImg = new int[] { R.drawable.fastfood, R.drawable.express,
				R.drawable.taxi, R.drawable.around, R.drawable.groupbuy,
				R.drawable.bank, R.drawable.drylundry, R.drawable.recharge };

		ArrayList<HashMap<String, Object>> lstItem = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < testLab.length; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put(ICOKEY, testImg[i]);
			map.put(LABKEY, testLab[i]);
			lstItem.add(map);
		}
		SimpleAdapter itemAdapter = new SimpleAdapter(context, lstItem,
				R.layout.grid_item, new String[] { ICOKEY, LABKEY },
				new int[] { R.id.grid_item_icon, R.id.grid_item_label });
		gridViewList.get(0).setAdapter(itemAdapter);

		String[] grid1Lab = new String[] { "Add" };
		int[] grid1Img = new int[] { R.drawable.ic_addnewservice };
		ArrayList<HashMap<String, Object>> grid1LstItem = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < grid1Lab.length; i++) {
			HashMap<String, Object> grid1map = new HashMap<String, Object>();
			grid1map.put(LABKEY, grid1Lab[i]);
			grid1map.put(ICOKEY, grid1Img[i]);
			grid1LstItem.add(grid1map);
		}
		SimpleAdapter grid1ItemAdapter = new SimpleAdapter(context, grid1LstItem,
				R.layout.grid_item, new String[] { ICOKEY, LABKEY },
				new int[] { R.id.grid_item_icon, R.id.grid_item_label });
		gridViewList.get(1).setAdapter(grid1ItemAdapter);

		gridViewList.get(0).setOnItemClickListener(null);
		gridViewList.get(1).setOnItemClickListener(null);
		
		mMainBody0Array = pageList;

		pointViewGroup = (ViewGroup) mMainPager.findViewById(R.id.indicators);
		viewPager = (ViewPager) mMainPager.findViewById(R.id.viewpager);
		mIndicator = new ImageView[MAINBODY1PAGENUM];

		for (int i = 0; i < mIndicator.length; i++) {
			ImageView pointImageView = new ImageView(context);
			pointImageView.setLayoutParams(new LayoutParams(12, 12));
			pointImageView.setPadding(10, 0, 10, 0);
			mIndicator[i] = pointImageView;
			if (i == 0) {
				mIndicator[i]
						.setBackgroundResource(R.drawable.page_indicator_focused);
			} else {
				mIndicator[i]
						.setBackgroundResource(R.drawable.page_indicator_unfocused);
			}
			pointViewGroup.addView(pointImageView);
		}

		ViewPagerAdapter adapter = new ViewPagerAdapter();
		viewPager.setAdapter(adapter);
		ViewPagerPageChangeListener listener = new ViewPagerPageChangeListener();
		viewPager.setOnPageChangeListener(listener);
		viewPager.setCurrentItem(0);
		return mMainPager;
	}
	
	public static class ViewPagerAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return mMainBody0Array.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public Parcelable saveState() {
			return super.saveState();
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// container.removeView(mMainBody0Array.get(position
			// % MAINBODY1PAGENUM));
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			try {
				container.addView(mMainBody0Array.get(position));
			} catch (Exception e) {
			}

			return mMainBody0Array.get(position);
		}

	}

	public static class ViewPagerPageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageSelected(int arg0) {
			updateIndicatorState(arg0);
		}

		private void updateIndicatorState(int index) {
			for (int i = 0; i < mIndicator.length; i++) {
				if (i == index) {
					mIndicator[i]
							.setBackgroundResource(R.drawable.page_indicator_focused);
				} else {
					mIndicator[i]
							.setBackgroundResource(R.drawable.page_indicator_unfocused);
				}
			}
		}
	}
}
