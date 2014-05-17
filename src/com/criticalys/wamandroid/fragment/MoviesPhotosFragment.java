package com.criticalys.wamandroid.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.criticalys.wamandroid.R;
import com.criticalys.wamandroid.util.CustomVerticalScrollView;
import com.criticalys.wamandroid.util.CustomVerticalScrollView.onScrollStopListner;
import com.criticalys.wamandroid.util.WAMUtil;

public class MoviesPhotosFragment extends Fragment {

	int mColumn1TilesCount = 0;
	int mColumn2TilesCount = 0;
	LayoutInflater mLayoutInflater;
	private View mParentLayout;
	private ViewGroup mColumn1ViewGroup;
	private ViewGroup mColumn2ViewGroup;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mParentLayout = inflater.inflate(R.layout.fragment_movies_photos,
				container, false);
		mLayoutInflater = inflater;
		mColumn1ViewGroup = (ViewGroup) mParentLayout
				.findViewById(R.id.grid_two_column1);
		mColumn2ViewGroup = (ViewGroup) mParentLayout
				.findViewById(R.id.grid_two_column2);
		loadPhotoTiles();
		setInfiniteScrollView();
		return mParentLayout;
	}

	private void setInfiniteScrollView() {
		final CustomVerticalScrollView sv1 = (CustomVerticalScrollView) mParentLayout
				.findViewById(R.id.grid_two_scroll_view);
		sv1.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if (event.getAction() == MotionEvent.ACTION_UP) {
					sv1.startScrollerTask();
				}
				return false;
			}
		});

		sv1.setOnScrollStopListner(new onScrollStopListner() {
			@Override
			public void onScrollStoped() {
				WAMUtil.showToastShort(getActivity(), "Loading...");
				inflateAndAddImageTile();
				inflateAndAddImageTile();
				Log.d(CustomVerticalScrollView.TAG, " " + sv1.getScrollX());
			}

		});

	}

	private void loadPhotoTiles() {
		TextView textView;
		View view11 = inflateAndAddImageTile();
		ImageView imageView11 = (ImageView) view11
				.findViewById(R.id.image_with_text_tile_image);
		imageView11.setImageDrawable(getResources().getDrawable(
				R.drawable.test1));
		textView = (TextView) view11
				.findViewById(R.id.image_with_text_tile_text);
		textView.setText("Pawan Kalyan");

		View view12 = inflateAndAddImageTile();
		ImageView imageView12 = (ImageView) view12
				.findViewById(R.id.image_with_text_tile_image);
		imageView12.setImageDrawable(getResources().getDrawable(
				R.drawable.test2));
		textView = (TextView) view12
				.findViewById(R.id.image_with_text_tile_text);
		textView.setText("Mahesh Babu");

		View view21 = inflateAndAddImageTile();
		ImageView imageView21 = (ImageView) view21
				.findViewById(R.id.image_with_text_tile_image);
		imageView21.setImageDrawable(getResources().getDrawable(
				R.drawable.test1));
		textView = (TextView) view21
				.findViewById(R.id.image_with_text_tile_text);
		textView.setText("Nani");

		View view22 = inflateAndAddImageTile();
		ImageView imageView22 = (ImageView) view22
				.findViewById(R.id.image_with_text_tile_image);
		imageView22.setImageDrawable(getResources().getDrawable(
				R.drawable.test2));
		textView = (TextView) view22
				.findViewById(R.id.image_with_text_tile_text);
		textView.setText("Samantha");

		View view31 = inflateAndAddImageTile();
		ImageView imageView31 = (ImageView) view31
				.findViewById(R.id.image_with_text_tile_image);
		imageView31.setImageDrawable(getResources().getDrawable(
				R.drawable.test2));
		textView = (TextView) view31
				.findViewById(R.id.image_with_text_tile_text);
		textView.setText("Prabhas");

		View view32 = inflateAndAddImageTile();
		ImageView imageView32 = (ImageView) view32
				.findViewById(R.id.image_with_text_tile_image);
		imageView32.setImageDrawable(getResources().getDrawable(
				R.drawable.test1));
		textView = (TextView) view32
				.findViewById(R.id.image_with_text_tile_text);
		textView.setText("Kajal");	
	}

	private View inflateAndAddImageTile() {
		if (mColumn1TilesCount <= mColumn2TilesCount) {
			View tile = mLayoutInflater.inflate(
					R.layout.layout_image_with_text_tile, mColumn1ViewGroup,
					false);
			mColumn1ViewGroup.addView(tile);
			mColumn1TilesCount += 1;
			return tile;
		} else {
			View tile = mLayoutInflater.inflate(
					R.layout.layout_image_with_text_tile, mColumn2ViewGroup,
					false);
			mColumn2ViewGroup.addView(tile);
			mColumn2TilesCount += 1;
			return tile;
		}
	}
}
