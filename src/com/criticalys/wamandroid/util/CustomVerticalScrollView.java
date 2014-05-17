package com.criticalys.wamandroid.util;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.ScrollView;

public class CustomVerticalScrollView extends ScrollView {
	private Runnable scrollerTask;
	private int newCheck = 100;
	public static final String TAG = "ScrollView";

	public interface onScrollStopListner {
		void onScrollStoped();
	}

	private onScrollStopListner onScrollstopListner;

	public CustomVerticalScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);

		DisplayMetrics displayMetrics = context.getResources()
				.getDisplayMetrics();
		final int screenheightInPix = displayMetrics.heightPixels;

		// TODO Auto-generated constructor stub
		scrollerTask = new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				if (getHeight() - getScrollY() - screenheightInPix <= 0) {
					if (onScrollstopListner != null) {
						onScrollstopListner.onScrollStoped();
						
					}
				} else {
					CustomVerticalScrollView.this.postDelayed(scrollerTask, newCheck);
				}
			}
		};
	}

	public void setOnScrollStopListner(
			CustomVerticalScrollView.onScrollStopListner listner) {
		onScrollstopListner = listner;
	}

	public void startScrollerTask() {
		CustomVerticalScrollView.this.postDelayed(scrollerTask, newCheck);
	}

}
