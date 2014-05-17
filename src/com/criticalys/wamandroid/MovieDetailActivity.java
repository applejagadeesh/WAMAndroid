package com.criticalys.wamandroid;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;

import com.criticalys.wamandroid.adapter.MovieDetailPagePagerAdapter;
import com.criticalys.wamandroid.util.WAMUtil;
import com.viewpagerindicator.TabPageIndicator;

public class MovieDetailActivity extends WAMBaseActivity {

	private ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentViewWithSidebar(R.layout.activity_movie_detail);
		mViewPager = (ViewPager) findViewById(R.id.movie_detail_view_pager);
		PagerAdapter viewPagerAdapter = new MovieDetailPagePagerAdapter(
				getSupportFragmentManager());
		mViewPager.setAdapter(viewPagerAdapter);
		TabPageIndicator pageIndicator = (TabPageIndicator) findViewById(R.id.movies_detail_tab_indicator);
		pageIndicator.setViewPager(mViewPager);
		pageIndicator.setBackgroundColor(Color.BLACK);
		final ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		WAMUtil.getInstance();
		// For Google Analytics
		WAMUtil.sendDataToAnalytics(this, WamConstants.APP_VIEW_ACTION,
				WamConstants.MOVIE_DETAILS_SCREEN);
	}
}
