package com.criticalys.wamandroid;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;

import com.criticalys.wamandroid.adapter.MoviesPagePagerAdapter;
import com.criticalys.wamandroid.util.WAMUtil;
import com.viewpagerindicator.TabPageIndicator;

public class MoviesActivity extends WAMBaseActivity {

	private ViewPager mViewPager;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentViewWithSidebar(R.layout.activity_movies);
		mViewPager = (ViewPager) findViewById(R.id.movies_pager);
		PagerAdapter viewPagerAdapter = new MoviesPagePagerAdapter(
				getSupportFragmentManager());
		mViewPager.setAdapter(viewPagerAdapter);
		//this page can be navigated from side bar navigation		
		int sideBarSelectionIndex = getIntent().getIntExtra(
				WamConstants.SIDE_BAR_SELECTED_ITEM, 0);		
		final ActionBar actionBar = getSupportActionBar();
		// this is required to open sidebar when we click on home button
		// base activity takes care of this
		actionBar.setDisplayHomeAsUpEnabled(true);
		TabPageIndicator pageIndicator = (TabPageIndicator) findViewById(R.id.movies_tab_indicator);
		pageIndicator.setViewPager(mViewPager);
		//mViewPager.setCurrentItem(sideBarSelectionIndex);
		pageIndicator.setBackgroundColor(Color.BLACK);
		pageIndicator.setCurrentItem(sideBarSelectionIndex);
		// For Google Analytics
		WAMUtil.sendDataToAnalytics(this, WamConstants.APP_VIEW_ACTION,
				WamConstants.MOVIES_SCREEN);

	}

	public void openNewsVideoDetailPage(View view) {
		Intent intent = new Intent(this, NewsVideoDetailActivity.class);
		startActivity(intent);
	}

	public void openNewsPhotoDetailPage(View view) {
		Intent intent = new Intent(this, NewsPhotoDetailActivity.class);
		startActivity(intent);
	}

	public void openNewsTextDetailPage(View view) {
		Intent intent = new Intent(this, NewsTextDetailActivity.class);
		startActivity(intent);
	}

	public void showPopupMenu(View view) {
		PopupMenu popup = new PopupMenu(this, view);
		popup.getMenuInflater().inflate(R.menu.movies_video_tile_more,
				popup.getMenu());
		// registering popup with OnMenuItemClickListener
		popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
			public boolean onMenuItemClick(MenuItem item) {
				// Toast.makeText(MoviesActivity.this,"You Clicked : " +
				// item.getTitle(),Toast.LENGTH_SHORT).show();
				return true;
			}
		});
		popup.show();
	}

	public void openPhotoCategoriesPopUp(View view) {
		PopupMenu popup = new PopupMenu(this, view);
		popup.getMenuInflater().inflate(R.menu.movies_photos_categories_more,
				popup.getMenu());
		// registering popup with OnMenuItemClickListener
		popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
			public boolean onMenuItemClick(MenuItem item) {
				// Toast.makeText(MoviesActivity.this,"You Clicked : " +
				// item.getTitle(),Toast.LENGTH_SHORT).show();
				return true;
			}
		});
		popup.show();
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		overridePendingTransition(R.anim.slide_down, R.anim.close_page);
	}
}
