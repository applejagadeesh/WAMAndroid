package com.criticalys.wamandroid.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnTouchListener;

import com.criticalys.wamandroid.MoviesActivity;
import com.criticalys.wamandroid.R;
import com.criticalys.wamandroid.util.CustomVerticalScrollView;
import com.criticalys.wamandroid.util.CustomVerticalScrollView.onScrollStopListner;

public class MoviesNewsFragment extends Fragment {

	float mColumn1Height = 0;
	float mColumn2Height = 0;
	LayoutInflater mLayoutInflater;
	private View mParentLayout;
	private ViewGroup mColumn1ViewGroup;
	private ViewGroup mColumn2ViewGroup;
	private float mImageTileHeight;
	private float mTextTileHeight;
	private float mVideoTileHeight;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mParentLayout = inflater.inflate(R.layout.layout_grid_two_column,
				container, false);
		mLayoutInflater = inflater;
		mImageTileHeight = getResources().getDimension(
				R.dimen.image_with_text_tile_height);
		mTextTileHeight = getResources().getDimension(R.dimen.text_tile_height);
		mVideoTileHeight = getResources().getDimension(
				R.dimen.video_tile_height);
		mColumn1ViewGroup = (ViewGroup) mParentLayout
				.findViewById(R.id.grid_two_column1);
		mColumn2ViewGroup = (ViewGroup) mParentLayout
				.findViewById(R.id.grid_two_column2);
		loadNewsTiles();
		//setInfiniteScrollView();
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
				inflateAndAddImageTile();
				inflateAndAddImageTile();
				Log.d(CustomVerticalScrollView.TAG, " " + sv1.getScrollX());
			}

		});

	}

	/*
	 * */
	private void loadNewsTiles() {
		View tileView11 = inflateAndAddVideoTile();
		View tileView12 = inflateAndAddImageTile();
		View tileView13 = inflateAndAddTextTile();
		View tileView21 = inflateAndAddImageTile();
		View tileView23 = inflateAndAddVideoTile();
		View tileView22 = inflateAndAddTextTile();
	}

	private View inflateAndAddImageTile() {
		View tile;
		if (mColumn1Height <= mColumn2Height) {
			tile = mLayoutInflater.inflate(
					R.layout.layout_image_with_text_tile, mColumn1ViewGroup,
					false);
			mColumn1ViewGroup.addView(tile);
			mColumn1Height += mImageTileHeight;

		} else {
			tile = mLayoutInflater.inflate(
					R.layout.layout_image_with_text_tile, mColumn2ViewGroup,
					false);
			mColumn2ViewGroup.addView(tile);
			mColumn2Height += mImageTileHeight;
		}
		View clickView = tile.findViewById(R.id.image_with_text_tile_clickview);
		clickView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				((MoviesActivity) getActivity()).openNewsPhotoDetailPage(v);
			}
		});
		return tile;

	}

	private View inflateAndAddVideoTile() {
		if (mColumn1Height <= mColumn2Height) {
			View tile = mLayoutInflater.inflate(R.layout.layout_video_tile,
					mColumn1ViewGroup, false);
			mColumn1ViewGroup.addView(tile);
			mColumn1Height += mVideoTileHeight;
			return tile;
		} else {
			View tile = mLayoutInflater.inflate(R.layout.layout_video_tile,
					mColumn2ViewGroup, false);
			mColumn2ViewGroup.addView(tile);
			mColumn2Height += mVideoTileHeight;
			return tile;
		}
	}

	private View inflateAndAddTextTile() {
		if (mColumn1Height <= mColumn2Height) {
			View tile = mLayoutInflater.inflate(R.layout.layout_text_tile,
					mColumn1ViewGroup, false);
			mColumn1ViewGroup.addView(tile);
			mColumn1Height += mTextTileHeight;
			return tile;
		} else {
			View tile = mLayoutInflater.inflate(R.layout.layout_text_tile,
					mColumn2ViewGroup, false);
			mColumn2ViewGroup.addView(tile);
			mColumn2Height += mTextTileHeight;
			return tile;
		}
	}

}
