package com.criticalys.wamandroid;

import com.criticalys.wamandroid.util.WAMUtil;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;


public class NewsFullScreenDetailActivity extends FragmentActivity {

	CustomYouTubePlayerSupportFragment myFragment = null;
	@Override
	  public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
	    setContentView(R.layout.fragment_news_video_fullscreen);
	    myFragment = CustomYouTubePlayerSupportFragment.newInstance("nCgQDjiotG0");
		myFragment.init();
		FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
		fragmentTransaction.add(R.id.youtube_fragment, myFragment);
		fragmentTransaction.commit();
		// For Google Analytics
		WAMUtil.sendDataToAnalytics(this, WamConstants.APP_VIEW_ACTION, WamConstants.NEWS_VIDEO_FULL_SCREEN);
	}
	
}
