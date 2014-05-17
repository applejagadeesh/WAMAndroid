package com.criticalys.wamandroid;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;

import com.criticalys.wamandroid.util.WAMUtil;

public class NewsVideoDetailActivity extends WAMBaseActivity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentViewWithSidebar(R.layout.fragment_news_video_detail);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		// For Google Analytics
		WAMUtil.sendDataToAnalytics(this, WamConstants.APP_VIEW_ACTION, WamConstants.NEWS_VIDEO_DETAIL_SCREEN);
	}

	public void openNewsVideo(View view) {
		Intent intent = new Intent(this, NewsFullScreenDetailActivity.class);
		startActivity(intent);
	}

}
