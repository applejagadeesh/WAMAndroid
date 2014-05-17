package com.criticalys.wamandroid.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.criticalys.wamandroid.R;

/**
 * A {@link android.support.v4.app.FragmentStatePagerAdapter} that returns a
 * fragment representing an object in the collection.
 */
public class LaunchPageCarouselPagerAdapter extends FragmentStatePagerAdapter {

	public LaunchPageCarouselPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int i) {
		Fragment fragment = new DemoObjectFragment();
		Bundle args = new Bundle();
		args.putInt(DemoObjectFragment.ARG_OBJECT, i + 1);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public int getCount() {			
		return 5;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return "Movie" + (position + 1);
	}
	
	/**
	 * A dummy fragment representing a section of the app, but that simply
	 * displays dummy text.
	 */
	public static class DemoObjectFragment extends Fragment {

		public static final String ARG_OBJECT = "object";

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(
					R.layout.fragment_main_page_carousel, container, false);
			Bundle args = getArguments();
			/*((TextView) rootView.findViewById(android.R.id.text1))
					.setText(Integer.toString(args.getInt(ARG_OBJECT)));*/
			return rootView;
		}
	}
	
	public static class MainPageCarouselPageFragment extends Fragment {

		public static final String ARG_OBJECT = "object";

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(
					R.layout.fragment_main_page_carousel, container, false);
			Bundle args = getArguments();
			/*((TextView) rootView.findViewById(android.R.id.text1))
					.setText(Integer.toString(args.getInt(ARG_OBJECT)));*/
			return rootView;
		}
	}	
}
