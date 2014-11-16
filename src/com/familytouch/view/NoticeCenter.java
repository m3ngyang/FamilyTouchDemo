/**
 * @author Kaffa
 */
package com.familytouch.view;

import java.util.ArrayList;
import java.util.HashMap;

import com.familytouch.R;
import com.familytouch.control.NoticeItemAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class NoticeCenter {
	private static View view;
	private static final String ICON = "noticeIcon";
	private static final String TITL = "noticeTitl";
	private static final String CONT = "noticeCont";

	@SuppressLint("InflateParams")
	public static View getNoticeCenter(Context c) {
		view = LayoutInflater.from(c).inflate(R.layout.layout_notice_center,
				null);
		
		TabHost tabHost = (TabHost) view.findViewById(R.id.NoticeCenter);
		tabHost.setup();

		TabSpec today = tabHost.newTabSpec("今天");
		today.setContent(R.id.tabToday);
		today.setIndicator("今天");
		TabSpec notice = tabHost.newTabSpec("通知");
		notice.setContent(R.id.NoticeList);
		notice.setIndicator("通知");
		
		tabHost.addTab(today);
		tabHost.addTab(notice);

		ListView listView = (ListView) view.findViewById(R.id.NoticeList);
		ArrayList<HashMap<String, Object>> itemList = new ArrayList<HashMap<String, Object>>();
		
		int[] icons = new int[]{R.drawable.jingdong,R.drawable.pay,R.drawable.express,R.drawable.jiuxian};
		String[] titls = new String[]{"京东","缴费","快递","酒仙网"};
		String[] conts = new String[]{"您购买的[器材]已经发货了。","您9月份的缴纳的税费已经到账","您邮寄的快递已经发往目的地","亲爱的会员，优惠多多"};
 		
		for (int i = 0; i < icons.length; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put(ICON, icons[i]);
			map.put(TITL, titls[i]);
			map.put(CONT, conts[i]);
			itemList.add(map);
		}
		SimpleAdapter adapter = new NoticeItemAdapter(c, itemList,
				R.layout.notice_listitem, new String[] { ICON, TITL, CONT },
				new int[] { R.id.listIcon, R.id.listTitl, R.id.listCont });
		listView.setAdapter(adapter);
		return view;
	}
}
