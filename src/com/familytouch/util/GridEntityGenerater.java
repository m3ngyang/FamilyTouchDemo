/**
 * @author Kaffa
 */
package com.familytouch.util;

import java.util.ArrayList;

import com.familytouch.entity.GridEntity;

public class GridEntityGenerater {

	public static ArrayList<GridEntity> generate(int[] imgs,int[] labs){
		ArrayList<GridEntity> list = new ArrayList<GridEntity>();
		if (labs.length!=imgs.length) {
			list = null;
		}else {
			for (int i = 0; i < imgs.length; i++) {
				GridEntity entity = new GridEntity();
				entity.setGridIcon(imgs[i]);
				entity.setGridLabel(labs[i]);
				list.add(entity);
			}
		}
		return list;
	}
}
