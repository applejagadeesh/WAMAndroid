package com.criticalys.wamandroid.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.criticalys.wamandroid.DownloadImageTask;
import com.criticalys.wamandroid.R;

public class MoviesNowRunningFragment extends Fragment {

	int mColumn1TilesCount = 0;
	int mColumn2TilesCount = 0;
	LayoutInflater mLayoutInflater;
	private View mParentLayout;
	private ViewGroup mColumn1ViewGroup;
	private ViewGroup mColumn2ViewGroup;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mParentLayout = inflater.inflate(R.layout.layout_grid_two_column,
				container, false);		
		mLayoutInflater = inflater;
		mColumn1ViewGroup = (ViewGroup) mParentLayout
				.findViewById(R.id.grid_two_column1);
		mColumn2ViewGroup = (ViewGroup) mParentLayout
				.findViewById(R.id.grid_two_column2);
		loadMovieTiles();
		return mParentLayout;
	}

	private void loadMovieTiles() {
		// this should be json array from server
		String movies[] = { "Epic", "IceAge", "ToyStory", "300", "Cars",
				"Shreck" };
		for (int i = 0; i < movies.length; i++) {
			View movieTile = inflateAndAddMovieTile();
			initializeTile(movieTile, movies[i]);
		}
	}

	private View inflateAndAddMovieTile() {
		View tile;
		if (mColumn1TilesCount <= mColumn2TilesCount) {
			tile = mLayoutInflater.inflate(R.layout.movie_tile,
					mColumn1ViewGroup, false);
			mColumn1ViewGroup.addView(tile);
			mColumn1TilesCount += 1;
		} else {
			tile = mLayoutInflater.inflate(R.layout.movie_tile,
					mColumn2ViewGroup, false);
			mColumn2ViewGroup.addView(tile);
			mColumn2TilesCount += 1;
		}
		return tile;
	}

	private void initializeTile(View movieTile, String movieId) {
		TextView movieNameTextView = (TextView) movieTile
				.findViewById(R.id.movie_name);
		movieNameTextView.setText(movieId);
		ImageView imageView = (ImageView) movieTile
				.findViewById(R.id.movie_img);
		DownloadImageTask task = new DownloadImageTask(imageView);
		task.execute("http://fakeimg.pl/175x120/?text=" + movieId);

	}

}