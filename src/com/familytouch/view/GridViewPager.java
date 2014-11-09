/**
 * @author Kaffa
 */
package com.familytouch.view;

import java.util.ArrayList;
import java.util.HashMap;

import android.annotation.SuppressLint;
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
import com.familytouch.control.GridOnClickListener;
import com.familytouch.data.Constant;
import com.familytouch.entity.GridEntity;

public class GridViewPager {
	private static View mMainPager;
	private static ViewPager viewPager;
	private static ViewGroup pointViewGroup;
	private static ImageView[] mIndicator;
	private static int pageNum;
	
	private final static int ICONNUMPERPAGE = 8;
	
	@SuppressLint("InflateParams")
	public static View getGridViewPager(Context context, ArrayList<GridEntity> arrayList){
		mMainPager = LayoutInflater.from(context).inflate(
				R.layout.layout_viewpager, null);
		ArrayList<View> pageList = new ArrayList<View>();
		ArrayList<GridView> gridViewList = new ArrayList<GridView>();
		
		pageNum = (arrayList.size() % ICONNUMPERPAGE > 0) ? arrayList
				.size() / ICONNUMPERPAGE + 1 : arrayList.size()
				/ ICONNUMPERPAGE; 
		
		for (int i = 0; i < pageNum; i++) {
			View page = LayoutInflater.from(context).inflate(R.layout.layout_gridview, null);
			GridView gridView = (GridView) page.findViewById(R.id.gridview);
			pageList.add(page);
			gridViewList.add(gridView);
		}

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
				map.put(Constant.ICOKEY, beanLst.get(j).getGridIcon());
				map.put(Constant.LABKEY, context.getString(beanLst.get(j).getGridLabel()));
				lstItem.add(map);
			}
			SimpleAdapter itemAdapter = new SimpleAdapter(context, lstItem,
					R.layout.grid_item, new String[] { Constant.ICOKEY, Constant.LABKEY },
					new int[] { R.id.grid_item_icon, R.id.grid_item_label });
			gridViewList.get(i).setAdapter(itemAdapter);
			gridViewList.get(i).setOnItemClickListener(GridOnClickListener.getInstance(context));

		}

		if (modPart != 0) {
			ArrayList<GridEntity> beanLst = lstlst.get(lstlst.size() - 1);
			for (int i = 0; i < modPart; i++) {
				beanLst.add(arrayList.get(intPart * ICONNUMPERPAGE + i));
			}
			ArrayList<HashMap<String, Object>> lstItem = new ArrayList<HashMap<String, Object>>();
			for (int j = 0; j < beanLst.size(); j++) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put(Constant.ICOKEY, beanLst.get(j).getGridIcon());
				map.put(Constant.LABKEY, context.getString(beanLst.get(j).getGridLabel()));
				lstItem.add(map);
			}
			SimpleAdapter itemAdapter = new SimpleAdapter(context, lstItem,
					R.layout.grid_item, new String[] { Constant.ICOKEY, Constant.LABKEY },
					new int[] { R.id.grid_item_icon, R.id.grid_item_label });
			gridViewList.get(gridViewList.size() - 1).setAdapter(itemAdapter);
			gridViewList.get(gridViewList.size() - 1).setOnItemClickListener(
					GridOnClickListener.getInstance(context));
		}
		
		pointViewGroup = (ViewGroup) mMainPager.findViewById(R.id.indicators);
		viewPager = (ViewPager) mMainPager.findViewById(R.id.viewpager);
		mIndicator = new ImageView[pageNum];

		for (int i = 0; i < mIndicator.length; i++) {
			ImageView pointImageView = new ImageView(context);
			pointImageView.setLayoutParams(new LayoutParams(20, 10));//TODO
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

		ViewPagerAdapter adapter = new ViewPagerAdapter(pageList);
		viewPager.setAdapter(adapter);
		ViewPagerPageChangeListener listener = new ViewPagerPageChangeListener();
		viewPager.setOnPageChangeListener(listener);
		viewPager.setCurrentItem(0);
		return mMainPager;
	}
	
	public static class ViewPagerAdapter extends PagerAdapter {
		ArrayList<View> list;
		public ViewPagerAdapter(ArrayList<View> list){
			this.list = list;
		}
		
		@Override
		public int getCount() {
			return list.size();
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
				container.addView(list.get(position));
			} catch (Exception e) {
			}

			return list.get(position);
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
