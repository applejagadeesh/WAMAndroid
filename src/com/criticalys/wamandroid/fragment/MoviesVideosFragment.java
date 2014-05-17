package com.criticalys.wamandroid.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.criticalys.wamandroid.R;

public class MoviesVideosFragment extends Fragment {

	LayoutInflater mLayoutInflater;
	private ViewGroup mParentLayout;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_movies_videos,
				container, false);
		mLayoutInflater = inflater;
		mParentLayout = (ViewGroup) view.findViewById(R.id.movies_videos_parent_layout);
		loadVideos();
		return view;
	}

	private void loadVideos() {
		View view1 = inflateAndAddVideoTile();
		View view2 = inflateAndAddVideoTile();
		View view3 = inflateAndAddVideoTile();
		View view4 = inflateAndAddVideoTile();
		View view5 = inflateAndAddVideoTile();

	}

	private View inflateAndAddVideoTile() {
		View tile = mLayoutInflater.inflate(
				R.layout.layout_movies_video_tile, mParentLayout, false);
		mParentLayout.addView(tile);
		return tile;
	}
}
