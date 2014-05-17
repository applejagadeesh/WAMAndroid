package com.criticalys.wamandroid;

import com.criticalys.wamandroid.util.WAMUtil;

import android.os.Bundle;

public class NewsTextDetailActivity extends WAMBaseActivity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentViewWithSidebar(R.layout.fragment_news_text_detail);
		WAMUtil.sendDataToAnalytics(this, WamConstants.APP_VIEW_ACTION, WamConstants.NEWS_TEXT_DETAIL_SCREEN);
	}

}
