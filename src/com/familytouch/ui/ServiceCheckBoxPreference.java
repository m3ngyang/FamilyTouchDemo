package com.familytouch.ui;

import java.util.ArrayList;
import java.util.List;

import com.familytouch.R;
import com.familytouch.R.xml;
import com.familytouch.data.ServiceJsonObject;
import com.familytouch.util.JsonUtil;

import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

public class ServiceCheckBoxPreference extends PreferenceActivity {
	
	private static final String FILE_NAME = "test.json";
	
	PreferenceManager manager;
	List<CheckBoxPreference> prefList;
	JsonUtil jsonUtil;
	List<ServiceJsonObject> serviceList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.pref_checkbox);
		
		//从文件或者网络读取json文件
		jsonUtil = new JsonUtil(getApplicationContext());
		if(!jsonUtil.isJsonExist(FILE_NAME)){
			jsonUtil.connnectAndWriteJson(
					"http://202.120.40.111:8080/smartcity-server/rest/service/list", FILE_NAME);
		}
		serviceList = jsonUtil.readJson(FILE_NAME);
		
		//TODO: Dialog list
		manager = getPreferenceManager();
		prefList = new ArrayList<CheckBoxPreference>();
		prefList.add((CheckBoxPreference) manager.findPreference("box0"));
		prefList.add((CheckBoxPreference) manager.findPreference("box1"));
		prefList.add((CheckBoxPreference) manager.findPreference("box2"));
		
		
//		Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();
	}

}
