/**
 * @author Kaffa
 */
package com.familytouch.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.familytouch.R;
import com.familytouch.data.ServiceJsonObject;
import com.familytouch.ui.NewHomePage;
import com.familytouch.ui.WebBrowser;
import com.familytouch.util.JsonUtil;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class GridOnClickListener implements OnItemClickListener {
	private Activity activity;
	private Intent intent;
	
	JsonUtil jsonUtil;
	final String FILE_NAME = "test.json";
	List<ServiceJsonObject> serviceList;
	ArrayList<ServiceJsonObject> serviceAddList = new ArrayList<ServiceJsonObject>();
	AlertDialog.Builder dialogBuilder = null;

	
	public GridOnClickListener(Activity act) {
		activity = act;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long rowid) {
		// TODO Auto-generated method stub
	}

}
