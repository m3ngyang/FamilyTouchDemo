package com.familytouch.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.familytouch.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class NoticeItemAdapter extends SimpleAdapter {
	private static final String ICON = "noticeIcon";
	private static final String TITL = "noticeTitl";
	private static final String CONT = "noticeCont";
	Context context;
	ArrayList<HashMap<String, Object>> list;

	@SuppressWarnings("unchecked")
	public NoticeItemAdapter(Context context,
			List<? extends Map<String, ?>> data, int resource, String[] from,
			int[] to) {
		super(context, data, resource, from, to);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.list = (ArrayList<HashMap<String, Object>>) data;
	}

	@SuppressLint("ViewHolder")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		final View item = LayoutInflater.from(context).inflate(
				R.layout.notice_listitem, null);
		final ImageView icon = (ImageView) item.findViewById(R.id.listIcon);
		final TextView titl = (TextView) item.findViewById(R.id.listTitl);
		final TextView cont = (TextView) item.findViewById(R.id.listCont);
		final ImageButton bttn = (ImageButton) item
				.findViewById(R.id.deleteNoticeItem);

		icon.setBackgroundResource((Integer) list.get(position).get(ICON));
		titl.setText((CharSequence) list.get(position).get(TITL));
		cont.setText((CharSequence) list.get(position).get(CONT));
		bttn.setTag(position);
		bttn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int pos = Integer.parseInt(v.getTag().toString());
				list.remove(pos);
				Log.i("debug", "click item " + pos);
				NoticeItemAdapter.this.notifyDataSetChanged();
			}
		});
		return item;
	}
}
