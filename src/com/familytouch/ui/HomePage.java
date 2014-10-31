package com.familytouch.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.R.integer;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.familytouch.R;
import com.familytouch.R.drawable;
import com.familytouch.R.id;
import com.familytouch.R.layout;
import com.familytouch.control.GridOnClickListener;
import com.familytouch.control.TitleArrayAdapter;
import com.familytouch.data.ServiceJsonObject;

public class HomePage extends Activity {

	private final static String INSTANCESTATE = "curChoice";
	private final static String BUNDLEINDEX = "bundleIndex";
	private final static String LABKEY = "labelKey";
	public final static String ICOKEY = "iconKey";
	private final static int MAINBODY1PAGENUM = 3; // TODO: 设置页面数量

	private static List<Integer> titleImgIDList;

	protected final static String[] TITLES = { "remind", "fond", "my",
			"community", "latest" };

	protected static View tempView;
	protected ViewPager viewPager;
	protected ViewGroup pointViewGroup;
	private ImageView[] mIndicator;

	// 左边title对应的fragment
	private static View mTitle0ViewPager, mTitle1ViewPager, mTitle2ViewPager,
			mTitle3ViewPager;

	private static ArrayList<View> mMainBody0Array;
	// whole layout
	private static View mTitle0ViewPagerGrid0, mTitle0ViewPagerGrid1,
			mTitle0ViewPagerGrid2, mTitle0ViewPagerGrid3;
	// gridview
	protected static GridView mMainBody0GridView0, mMainBody0GridView1,
			mMainBody0GridView2, mMainBody0GridView3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_homepage);
		
		if (savedInstanceState==null) {
			init();
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		outState.putAll(outState);
		super.onSaveInstanceState(outState);
	}

	public static class TitleFragment extends ListFragment {
		boolean mDualPane;
		int mCurClickPostion = 0;

		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			return super.onCreateView(inflater, container, savedInstanceState);
		}

		@Override
		public void onActivityCreated(Bundle savedInstanceState) {
			super.onActivityCreated(savedInstanceState);
			// setListAdapter(new ArrayAdapter<String>(getActivity(),
			// android.R.layout.simple_list_item_1, TITLES));
			setListAdapter(new TitleArrayAdapter(getActivity(),
					R.layout.title_list_item, titleImgIDList));
			View mainView = getActivity().findViewById(R.id.details);
			mDualPane = (mainView != null)
					&& (mainView.getVisibility() == View.VISIBLE);
			if (savedInstanceState != null) {
				mCurClickPostion = savedInstanceState.getInt(INSTANCESTATE, 0);
			}
			if (mDualPane) {
				getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
				showMainViewAt(mCurClickPostion);
			}
		}

		@Override
		public void onSaveInstanceState(Bundle outState) {
			super.onSaveInstanceState(outState);
			outState.putInt(INSTANCESTATE, mCurClickPostion);
		}

		@Override
		public void onListItemClick(ListView l, View v, int position, long id) {
			super.onListItemClick(l, v, position, id);
			showMainViewAt(position);
		}

		public void showMainViewAt(int index) {
			mCurClickPostion = index;
			if (mDualPane) {
				getListView().setItemChecked(index, true);
				MainViewFragment mainView = (MainViewFragment) getFragmentManager()
						.findFragmentById(R.id.details);
				if (mainView == null || mainView.getShownIndex() != index) {
					mainView = MainViewFragment.getMainView(index);
					FragmentTransaction ft = getFragmentManager()
							.beginTransaction();
					ft.replace(R.id.details, mainView);
					ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
					ft.commit();
				}
			} else {
				new AlertDialog.Builder(getActivity())
						.setTitle(android.R.string.dialog_alert_title)
						.setMessage(TITLES[index])
						.setPositiveButton(android.R.string.ok, null).show();
			}
		}

	}

	public static class MainViewFragment extends Fragment {

		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setHasOptionsMenu(false);
		}

		public static MainViewFragment getMainView(int index) {
			MainViewFragment mainView = new MainViewFragment();
			Bundle args = new Bundle();
			args.putInt(BUNDLEINDEX, index);
			mainView.setArguments(args);
			return mainView;
		}

		public int getShownIndex() {
			return getArguments().getInt(BUNDLEINDEX, 0);
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			if (container == null)
				return null;

			ScrollView scroller = new ScrollView(getActivity());
			View view1;
			switch (getShownIndex()) {
			case 0:
				view1 = mTitle0ViewPager;
				return view1;
			default:
				// 其他选项，未做，先用文字代替
				TextView text = new TextView(getActivity());
				int padding = (int) TypedValue.applyDimension(
						TypedValue.COMPLEX_UNIT_DIP, 4, getActivity()
								.getResources().getDisplayMetrics());
				text.setPadding(padding, padding, padding, padding);
				scroller.addView(text);
				text.setText(TITLES[getShownIndex()]);
				return scroller;
			}
		}
	}

	@SuppressLint("InflateParams")
	public void init() {
		titleImgIDList = new ArrayList<Integer>();
		titleImgIDList.add(R.drawable.remind);
		titleImgIDList.add(R.drawable.fond);
		titleImgIDList.add(R.drawable.my);
		titleImgIDList.add(R.drawable.community);
		titleImgIDList.add(R.drawable.latest);
		
		getLayoutInflater();
		// tempView = (View) findViewById(R.layout.layout_mainbody1); return
		// null why????
		mTitle0ViewPager = LayoutInflater.from(this).inflate(
				R.layout.title0_viewpager, null);
		mTitle0ViewPagerGrid0 = LayoutInflater.from(this).inflate(
				R.layout.title0_viewpager_grid0, null);
		mTitle0ViewPagerGrid1 = LayoutInflater.from(this).inflate(
				R.layout.title0_viewpager_grid1, null);
		mTitle0ViewPagerGrid2 = LayoutInflater.from(this).inflate(
				R.layout.title0_viewpager_grid2, null);
		mTitle0ViewPagerGrid3 = LayoutInflater.from(this).inflate(
				R.layout.title0_viewpager_grid3, null);

		mMainBody0GridView0 = (GridView) mTitle0ViewPagerGrid0
				.findViewById(R.id.mainbody0_page0);
		mMainBody0GridView1 = (GridView) mTitle0ViewPagerGrid1
				.findViewById(R.id.mainbody0_page1);
		mMainBody0GridView2 = (GridView) mTitle0ViewPagerGrid2
				.findViewById(R.id.mainbody0_page2);
		mMainBody0GridView3 = (GridView) mTitle0ViewPagerGrid3
				.findViewById(R.id.mainbody0_page3);

		// TODO: test data, 直接依照该方法填充gridview即可
		// MainBody0Grid0
		String[] labels0 = new String[] { "送   餐", "快   递", "叫   车",
				"订   票", "社区团购", "自助银行", "叫干洗", "缴   费" };
		int[] imgs0 = new int[] { R.drawable.fastfood, R.drawable.express,
				R.drawable.taxi, R.drawable.tickets, R.drawable.groupbuy,
				R.drawable.bank, R.drawable.drylundry, R.drawable.recharge };

		ArrayList<HashMap<String, Object>> grid0LstItem = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < labels0.length; i++) {
			HashMap<String, Object> grip0map = new HashMap<String, Object>();
			grip0map.put(ICOKEY, imgs0[i]);
			grip0map.put(LABKEY, labels0[i]);
			grid0LstItem.add(grip0map);
		}
		SimpleAdapter grid0ItemAdapter = new SimpleAdapter(this, grid0LstItem,
				R.layout.grid_item, new String[] { ICOKEY, LABKEY },
				new int[] { R.id.grid_item_icon, R.id.grid_item_label });
		mMainBody0GridView0.setAdapter(grid0ItemAdapter);

		// MainBody0Grid1
		String[] labels1 = new String[] { "邻里活动", "亲   子", "老人服务",
				"医   疗", "买彩票", "理   财", "宠物服务", "智能家居" };
		int[] imgs1 = new int[] { R.drawable.nearbyactivity, R.drawable.parentchildren,
				R.drawable.theoldservice, R.drawable.medicalcare, R.drawable.lottery,
				R.drawable.accounting, R.drawable.pets, R.drawable.smarthouse };
		ArrayList<HashMap<String, Object>> grid1LstItem = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < labels1.length; i++) {
			HashMap<String, Object> grid1map = new HashMap<String, Object>();
			grid1map.put(LABKEY, labels1[i]);
			grid1map.put(ICOKEY, imgs1[i]);
			grid1LstItem.add(grid1map);
		}
		SimpleAdapter grid1ItemAdapter = new SimpleAdapter(this, grid1LstItem,
				R.layout.grid_item, new String[] { ICOKEY, LABKEY },
				new int[] { R.id.grid_item_icon, R.id.grid_item_label });
		mMainBody0GridView1.setAdapter(grid1ItemAdapter);
		
		// MainBody0Grid1
		String[] labels2 = new String[] { "添   加" };
		int[] imgs2 = new int[] { R.drawable.ic_addnewservice };
		ArrayList<HashMap<String, Object>> grid2LstItem = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < labels2.length; i++) {
			HashMap<String, Object> grid2map = new HashMap<String, Object>();
			grid2map.put(LABKEY, labels2[i]);
			grid2map.put(ICOKEY, imgs2[i]);
			grid2LstItem.add(grid2map);
		}
		SimpleAdapter grid2ItemAdapter = new SimpleAdapter(this, grid2LstItem,
				R.layout.grid_item, new String[] { ICOKEY, LABKEY },
				new int[] { R.id.grid_item_icon, R.id.grid_item_label });
		mMainBody0GridView2.setAdapter(grid2ItemAdapter);

		//OnClickListener on grid
		GridOnClickListener gridOnClickListener = new GridOnClickListener(this);
		mMainBody0GridView0.setOnItemClickListener(gridOnClickListener);
		mMainBody0GridView1.setOnItemClickListener(gridOnClickListener);
		
		mMainBody0Array = new ArrayList<View>();
		mMainBody0Array.add(mTitle0ViewPagerGrid0);
		mMainBody0Array.add(mTitle0ViewPagerGrid1);
		mMainBody0Array.add(mTitle0ViewPagerGrid2);
		mMainBody0Array.add(mTitle0ViewPagerGrid3);

		pointViewGroup = (ViewGroup) mTitle0ViewPager
				.findViewById(R.id.indicators);
		viewPager = (ViewPager) mTitle0ViewPager.findViewById(R.id.mainView);
		mIndicator = new ImageView[MAINBODY1PAGENUM];

		for (int i = 0; i < mIndicator.length; i++) {
			ImageView pointImageView = new ImageView(this);
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
		viewPager.setCurrentItem(MAINBODY1PAGENUM * 100);
	}

	public class ViewPagerAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return Integer.MAX_VALUE;
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
				container.addView(mMainBody0Array.get(position
						% MAINBODY1PAGENUM));
			} catch (Exception e) {
			}

			return mMainBody0Array.get(position % MAINBODY1PAGENUM);
		}

	}

	public class ViewPagerPageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageSelected(int arg0) {
			updateIndicatorState(arg0 % MAINBODY1PAGENUM);
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

	public void updateView(ArrayList<ServiceJsonObject> array) {
		String[] updateLab = new String[array.size()+1];
		int[] updateImg = new int[array.size()+1];
		for (int i = 0; i < array.size(); i++) {
			updateLab[i] = array.get(i).getName();
			String imgUrl = "ic_addservice"+array.get(i).getId();
			updateImg[i] = getResources().getIdentifier(imgUrl, "drawable", getPackageName());
		}
		updateLab[array.size()] = "add";
		updateImg[array.size()] = R.drawable.ic_addnewservice;
		ArrayList<HashMap<String, Object>> updateList = new ArrayList<HashMap<String,Object>>();
		
		for (int i = 0; i < updateLab.length; i++) {
			HashMap<String, Object> updateMap = new HashMap<String, Object>();
			updateMap.put(LABKEY, updateLab[i]);
			updateMap.put(ICOKEY, updateImg[i]);
			updateList.add(updateMap);
		}
		SimpleAdapter grid1UpdateItemAdapter = new SimpleAdapter(this, updateList,
				R.layout.grid_item, new String[] { ICOKEY, LABKEY },
				new int[] { R.id.grid_item_icon, R.id.grid_item_label });
		mMainBody0GridView1.setAdapter(grid1UpdateItemAdapter);
	}
}
