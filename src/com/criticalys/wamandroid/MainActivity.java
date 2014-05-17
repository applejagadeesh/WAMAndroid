package com.criticalys.wamandroid;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.criticalys.wamandroid.adapter.LaunchPageCarouselPagerAdapter;
import com.criticalys.wamandroid.util.OnSwipeTouchListener;
import com.criticalys.wamandroid.util.WAMUtil;
import com.google.analytics.tracking.android.EasyTracker;
import com.viewpagerindicator.CirclePageIndicator;

public class MainActivity extends WAMBaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentViewWithSidebar(R.layout.activity_launch_page);
		setActionBarContent();
		setPageContent();
		// For User voice
		WAMUtil.initializeUserVoice(this);
		// For Google Analytics
		WAMUtil.sendDataToAnalytics(this, WamConstants.APP_VIEW_ACTION,
				WamConstants.HOME_SCREEN);
	}

	private void setPageContent() {
		ViewPager viewPager = (ViewPager) findViewById(R.id.launch_page_carousel_pager);
		// swipe up gives slide up transition and opens WAM home page
		viewPager
				.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this) {
					public void onSwipeTop() {
						slideUpAndOpenMoviesPage(findViewById(R.id.launch_page_slide_up_btn));
					}
				});
		// adapter to populate carousel images
		viewPager.setAdapter(new LaunchPageCarouselPagerAdapter(
				getSupportFragmentManager()));
		// circular view pager indicator to indicate current page in carousel
		CirclePageIndicator indicator = (CirclePageIndicator) findViewById(R.id.launch_page_carousel_indicator);
		indicator.setViewPager(viewPager);
	}

	private void setActionBarContent() {
		ActionBar actionBar = getSupportActionBar();
		// disable title in landing page
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setDisplayHomeAsUpEnabled(true);
		// Make action bar transparent since we are using overlay theme in
		// launch page
		actionBar.setBackgroundDrawable(new ColorDrawable(getResources()
				.getColor(R.color.action_bar)));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.sample_menu, menu);

		// this statement is necessary since base class creates menu items
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return super.onOptionsItemSelected(item);
	}

	public void slideUpAndOpenMoviesPage(View view) {
		Intent intent = new Intent(this, MoviesActivity.class);
		startActivity(intent);
		overridePendingTransition(R.anim.open_page, R.anim.slide_up);

	}

	public void openMovieDetailPage(View view) {
		Intent intent = new Intent(this, MovieDetailActivity.class);
		startActivity(intent);
	}

	@Override
	public void onStart() {
		super.onStart();
		EasyTracker.getInstance(this).activityStart(this);
	}

	@Override
	public void onStop() {
		super.onStop();
		EasyTracker.getInstance(this).activityStop(this);
	}

}
