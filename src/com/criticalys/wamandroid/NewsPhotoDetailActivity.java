package com.criticalys.wamandroid;

import com.criticalys.wamandroid.util.WAMUtil;

import android.os.Bundle;

public class NewsPhotoDetailActivity extends WAMBaseActivity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentViewWithSidebar(R.layout.fragment_news_photo_detail);
		WAMUtil.sendDataToAnalytics(this, WamConstants.APP_VIEW_ACTION, WamConstants.NEWS_PHOTO_DETAIL_SCREEN);
	}

}
