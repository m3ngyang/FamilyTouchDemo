/**
 * @author Kaffa
 */
package com.familytouch.control;

import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.familytouch.R;
import com.familytouch.data.Constant;
import com.familytouch.ui.DevicePage;
import com.familytouch.ui.GalleryActivity;
import com.familytouch.ui.StaticPage;
import com.familytouch.view.SquareMenu;

public class GridOnClickListener implements OnItemClickListener {
	private static GridOnClickListener instance = null;
	private Context context;
	private Intent intent;

	public GridOnClickListener(Context context) {
		this.context = context;
	}

	public static GridOnClickListener getInstance(Context context) {
		if (instance == null) {
			instance = new GridOnClickListener(context);
		}
		return instance;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long rowid) {
		@SuppressWarnings("unchecked")
		HashMap<String, Object> itemMap = (HashMap<String, Object>) parent
				.getItemAtPosition(position);
		int img = (Integer) itemMap.get(Constant.ICOKEY);
		switch (img) {
		case R.drawable.fastfood:// 送餐
			intent = new Intent(context, GalleryActivity.class);
			intent.putExtra(Constant.GALLERYKEY, Constant.IMG_FASTFOOD);
			context.startActivity(intent);
			break;

		case R.drawable.express:// 快递
			intent = new Intent(context, StaticPage.class);
			intent.putExtra(Constant.STATICPAGEKEY, R.drawable.shunfeng);
			context.startActivity(intent);
			break;

		case R.drawable.taxi:// 叫车
			intent = new Intent(context, StaticPage.class);
			intent.putExtra(Constant.STATICPAGEKEY, R.drawable.dididache);
			context.startActivity(intent);
			break;

		case R.drawable.ticket:// 票务
			intent = new Intent(context, StaticPage.class);
			intent.putExtra(Constant.STATICPAGEKEY, R.drawable.ticketpage);
			context.startActivity(intent);
			break;

		case R.drawable.travel:// 出行
			intent = new Intent(context, StaticPage.class);
			intent.putExtra(Constant.STATICPAGEKEY, R.drawable.qunaerpage);
			context.startActivity(intent);
			break;

		case R.drawable.pay:// 缴费
			intent = new Intent(context, StaticPage.class);
			intent.putExtra(Constant.STATICPAGEKEY, R.drawable.alipaypage);
			context.startActivity(intent);
			break;

		case R.drawable.nearbyactivity:// 邻里活动
			intent = new Intent(context, GalleryActivity.class);
			intent.putExtra(Constant.GALLERYKEY, Constant.IMG_NEARBYACTIVITY);
			context.startActivity(intent);
			break;

		case R.drawable.communitybuy:// 社区团购
			intent = new Intent(context, GalleryActivity.class);
			intent.putExtra(Constant.GALLERYKEY, Constant.IMG_COMMUNITYBUY);
			context.startActivity(intent);
			break;

		case R.drawable.callservice:// 叫服务
			intent = new Intent(context, SquareMenu.class);
			intent.putExtra(Constant.ICOLISTKEY, Constant.callServiceImg);
			intent.putExtra(Constant.LABLISTKEY, Constant.callServiceLab);
			context.startActivity(intent);
			break;

		case R.drawable.deliver:// 送上门
			intent = new Intent(context, SquareMenu.class);
			intent.putExtra(Constant.ICOLISTKEY, Constant.deliveryImg);
			intent.putExtra(Constant.LABLISTKEY, Constant.deliveryLab);
			context.startActivity(intent);
			break;

		case R.drawable.fruit:// 送上门：水果
			intent = new Intent(context, GalleryActivity.class);
			intent.putExtra(Constant.GALLERYKEY, Constant.IMG_FRUIT);
			context.startActivity(intent);
			break;

		case R.drawable.buy:// 买东西
			intent = new Intent(context, SquareMenu.class);
			intent.putExtra(Constant.ICOLISTKEY, Constant.buyImg);
			intent.putExtra(Constant.LABLISTKEY, Constant.buyLab);
			context.startActivity(intent);
			break;

		case R.drawable.theoldservice:// 老人服务
			intent = new Intent(context, SquareMenu.class);
			intent.putExtra(Constant.ICOLISTKEY, Constant.oldserviceImg);
			intent.putExtra(Constant.LABLISTKEY, Constant.oldserviceLab);
			context.startActivity(intent);
			break;

		case R.drawable.parentchildren:// 亲子
			intent = new Intent(context, GalleryActivity.class);
			intent.putExtra(Constant.GALLERYKEY, Constant.IMG_PARENTCHILDREN);
			context.startActivity(intent);
			break;

		case R.drawable.smartmedicare:// 智慧医疗
			intent = new Intent(context, StaticPage.class);
			intent.putExtra(Constant.STATICPAGEKEY, R.drawable.smartmedicarepage);
			context.startActivity(intent);
			break;

		case R.drawable.bank:// 自助银行
			intent = new Intent(context, StaticPage.class);
			intent.putExtra(Constant.STATICPAGEKEY, R.drawable.lakalapage);
			context.startActivity(intent);
			break;

		case R.drawable.pet:// 宠物
			intent = new Intent(context, GalleryActivity.class);
			intent.putExtra(Constant.GALLERYKEY, Constant.IMG_PET);
			context.startActivity(intent);
			break;

		case R.drawable.finance:// 理财
			intent = new Intent(context, StaticPage.class);
			intent.putExtra(Constant.STATICPAGEKEY, R.drawable.qianxianshengpage);
			context.startActivity(intent);
			break;
			
		case R.drawable.guangguang:// 逛逛
			intent = new Intent(context, StaticPage.class);
			intent.putExtra(Constant.STATICPAGEKEY, R.drawable.guangguangpage);
			context.startActivity(intent);
			break;

		case R.drawable.numberone:// 1号店
			intent = new Intent(context, StaticPage.class);
			intent.putExtra(Constant.STATICPAGEKEY, R.drawable.numberonepage);
			context.startActivity(intent);
			break;

		case R.drawable.amazon:// 亚马逊
			intent = new Intent(context, StaticPage.class);
			intent.putExtra(Constant.STATICPAGEKEY, R.drawable.amazonpage);
			context.startActivity(intent);
			break;

		case R.drawable.qiqi:// 美味七七
			intent = new Intent(context, StaticPage.class);
			intent.putExtra(Constant.STATICPAGEKEY, R.drawable.qiqipage);
			context.startActivity(intent);
			break;

		case R.drawable.mobiledevice:// 移动设备
			intent = new Intent(context, DevicePage.class);
			context.startActivity(intent);
			break;
			
		case R.drawable.setting:// 设置
			intent = new Intent(context, StaticPage.class);
			intent.putExtra(Constant.STATICPAGEKEY, R.drawable.settingpage);
			context.startActivity(intent);
			break;

		default:
			break;
		}
	}
}
