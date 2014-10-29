package com.familytouch.control;

import java.util.List;

import android.R.integer;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;

import com.familytouch.R;

public class TitleArrayAdapter extends ArrayAdapter {
	
	private int resourceId;  
	
    public TitleArrayAdapter(Context context, int textViewResourceId, List<Integer> objects) {  
        super(context, textViewResourceId, objects);  
        this.resourceId = textViewResourceId;  
    }  
      
    @Override  
    public View getView(int position, View convertView, ViewGroup parent){ 
    	int imgId = (Integer) getItem(position);
        LinearLayout layout = new LinearLayout(getContext());  
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);   
        inflater.inflate(resourceId, layout, true);  
        Button button = (Button)layout.findViewById(R.id.btnTitleItem);
        button.setBackgroundResource(imgId);
        return layout;  
    }  
}
