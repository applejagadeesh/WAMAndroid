package com.criticalys.wamandroid.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.criticalys.wamandroid.fragment.MoviesNewsFragment;
import com.criticalys.wamandroid.fragment.MoviesNowRunningFragment;
import com.criticalys.wamandroid.fragment.MoviesPhotosFragment;
import com.criticalys.wamandroid.fragment.MoviesVideosFragment;

public class MoviesPagePagerAdapter extends FragmentPagerAdapter {

	private String[] pageTitles = { "NowRunning", "Upcoming", "Videos", "News",
			"Photos" };
	private Class[] fragmentClasses = { MoviesNowRunningFragment.class,
			MoviesNowRunningFragment.class, MoviesVideosFragment.class,
			MoviesNewsFragment.class, MoviesPhotosFragment.class };

	public MoviesPagePagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {
		Fragment fragment = null;
		try {
			fragment = (Fragment) fragmentClasses[position].newInstance();
		} catch (InstantiationException e) {
			System.out.println("Only Fragment classes are allowed");
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return fragment;

	}

	@Override
	public int getCount() {
		return pageTitles.length;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return pageTitles[position];
	}

}
