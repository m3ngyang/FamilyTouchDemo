package com.familytouch.ui;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.familytouch.R;
import com.familytouch.control.TitleArrayAdapter;
import com.familytouch.data.Constant;
import com.familytouch.entity.GridEntity;
import com.familytouch.util.GridEntityGenerater;
import com.familytouch.view.GridViewPager;

public class HomePage extends Activity {

	private final static String INSTANCESTATE = "curChoice";
	private final static String BUNDLEINDEX = "bundleIndex";
	private static View mMainPager;

	private static List<Integer> titleImgIDList;

	protected final static String[] TITLES = { "remind", "rank", "mine",
			"recent", "around" };

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
		// TODO Auto-generated method stub
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
				view1 = mMainPager;
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

		titleImgIDList = new ArrayList<Integer>();
		titleImgIDList.add(R.drawable.remind);
		titleImgIDList.add(R.drawable.rank);
		titleImgIDList.add(R.drawable.mine);
		titleImgIDList.add(R.drawable.recent);
		titleImgIDList.add(R.drawable.blank1);
		titleImgIDList.add(R.drawable.blank2);
		titleImgIDList.add(R.drawable.around);

		ArrayList<GridEntity> arrayList = GridEntityGenerater.generate(Constant.menuImg,
				Constant.menuLab);
		getLayoutInflater();
		mMainPager = GridViewPager.getGridViewPager(getApplicationContext(),
				arrayList);
	}
}