package com.familytouch.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.familytouch.R;
import com.familytouch.control.HomeBtnOnClickListener;
import com.familytouch.control.SearchBtnOnClickListener;
import com.familytouch.control.WholeViewOnTouchListener;
import com.familytouch.data.Constant;
import com.familytouch.entity.GridEntity;
import com.familytouch.util.GridEntityGenerater;
import com.familytouch.view.GridViewPager;

public class HomePage extends Activity {

	private final static String INSTANCESTATE = "curChoice";
	private final static String BUNDLEINDEX = "bundleIndex";
	private static View mMainPager;

	private static List<Map<String, Object>> titleList;

	private final static int[] TITLES = { R.drawable.blank0, R.drawable.remind,
			R.drawable.rank, R.drawable.mine, R.drawable.recent,
			R.drawable.blank1, R.drawable.blank2, R.drawable.around };

	public static View curView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aty_homepage);

		if (savedInstanceState == null) {
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
			setListAdapter(new SimpleAdapter(getActivity(), titleList,
					R.layout.title_list_item, new String[] { "img" },
					new int[] { R.id.btnTitleItem }));
			View mainView = getActivity().findViewById(R.id.details);
			mDualPane = (mainView != null)
					&& (mainView.getVisibility() == View.VISIBLE);
			if (savedInstanceState != null) {
				mCurClickPostion = savedInstanceState.getInt(INSTANCESTATE, 0);
			}
			if (mDualPane) {
				getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
				getListView().setDivider(null); // 去掉listView之间的横线
				getListView().setSelector(new ColorDrawable(Color.TRANSPARENT));// 去掉选中时的黄色背景
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

		@SuppressLint("InflateParams")
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			if (container == null)
				return null;

			ScrollView scroller = new ScrollView(getActivity());
			// View curView;
			switch (getShownIndex()) {
			case 0:
				curView = mMainPager;
				return curView;

			case 1:
				View noticeView = LayoutInflater.from(getActivity()).inflate(
						R.layout.layout_notice, null);
				curView = noticeView;
				return curView;

			default:
				// TODO: 其他选项，未做，先用文字代替
				TextView text = new TextView(getActivity());
				int padding = (int) TypedValue.applyDimension(
						TypedValue.COMPLEX_UNIT_DIP, 4, getActivity()
								.getResources().getDisplayMetrics());
				text.setPadding(padding, padding, padding, padding);
				scroller.addView(text);
				text.setText("developing ...");
				return scroller;
			}
		}
	}

	@SuppressLint("InflateParams")
	public void init() {
		titleList = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < TITLES.length; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("img", TITLES[i]);
			titleList.add(map);
		}

		ArrayList<GridEntity> arrayList = GridEntityGenerater.generate(
				Constant.menuImg, Constant.menuLab);
		getLayoutInflater();

		// mMainPager = GridViewPager.getGridViewPager(getApplicationContext(),
		// arrayList);
		mMainPager = GridViewPager.getGridViewPager(HomePage.this, arrayList);
		ImageButton searchBtn = (ImageButton) findViewById(R.id.btn_search);
		searchBtn.setOnClickListener(new SearchBtnOnClickListener(this));
		ImageButton homeBtn = (ImageButton) findViewById(R.id.btn_home);
		homeBtn.setOnClickListener(new HomeBtnOnClickListener(this));
		
		findViewById(R.id.homepage).setOnTouchListener(new WholeViewOnTouchListener(HomePage.this));
	}
}