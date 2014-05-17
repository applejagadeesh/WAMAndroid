package com.criticalys.wamandroid.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.criticalys.wamandroid.fragment.MovieDetailOverviewFragment;

public class MovieDetailPagePagerAdapter extends FragmentPagerAdapter {

	private String[] pageTitles = { "Overview ", "Photos", "Videos", "Reviews",
			"News" };
	private Class[] fragmentClasses = { MovieDetailOverviewFragment.class,
			MovieDetailOverviewFragment.class,
			MovieDetailOverviewFragment.class,
			MovieDetailOverviewFragment.class,
			MovieDetailOverviewFragment.class };

	public MovieDetailPagePagerAdapter(FragmentManager fm) {
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
