package com.familytouch.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;

import com.familytouch.data.ServiceJsonObject;

public class JsonUtil{
	
	private Context context;
	
	public JsonUtil(Context context) {
		this.context = context;
	}
	
	public List<ServiceJsonObject> readJson(String fileName){
		List<ServiceJsonObject> list = new ArrayList<ServiceJsonObject>();
		try {
			BufferedReader reader = new BufferedReader(
					new FileReader(new File(context.getFilesDir(), fileName)));
			
			String line;
			StringBuilder sBuilder = new StringBuilder();
			while((line = reader.readLine()) != null){
				sBuilder.append(line);
			}
			reader.close();
			
			JSONArray root = new JSONArray(sBuilder.toString());
			for (int i = 0; i < root.length(); i++) {
				JSONObject service = root.getJSONObject(i);
				list.add(new ServiceJsonObject(service.getString("description"), 
						service.getString("image"), 
						service.getString("name"), 
						service.getString("url"), 
						service.getInt("id"), 
						service.getInt("user_id")));
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public void connnectAndWriteJson(String urlString, final String fileName){
		
		AsyncTask<String, Void, Void> asyncTask = new AsyncTask<String, Void, Void>(){

			@Override
			protected Void doInBackground(String... params) {
				try {
					URL url = new URL(params[0]);
					URLConnection urlConnection = url.openConnection();
					BufferedReader reader = new BufferedReader(
							new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
					
					//路径：/data/data/packagename/files/
					FileWriter fileWriter = new FileWriter(
							new File(context.getFilesDir(), fileName));
					
					String line;
					while((line = reader.readLine()) != null){
						fileWriter.append(line);
						System.out.println(line);
					}
					fileWriter.flush();
					fileWriter.close();
					reader.close();
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return null;
			}
			
		};
		asyncTask.execute(urlString);
	}
	
	public boolean isJsonExist(String fileName){
		return new File(context.getFilesDir(), fileName).exists();
	}
	
}
