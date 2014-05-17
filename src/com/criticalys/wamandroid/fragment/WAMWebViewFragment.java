package com.criticalys.wamandroid.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.criticalys.wamandroid.R;

public class WAMWebViewFragment extends Fragment {

	private WebView mWebView;
	private LinearLayout mContentView;
	private FrameLayout mCustomViewContainer;
	private WebChromeClient.CustomViewCallback mCustomViewCallback;
	FrameLayout.LayoutParams COVER_SCREEN_GRAVITY_CENTER = new FrameLayout.LayoutParams(
			ViewGroup.LayoutParams.WRAP_CONTENT,
			ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER);

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.webview, container, false);
		mWebView = (WebView) view.findViewById(R.id.webView);
		mContentView = (LinearLayout) view.findViewById(R.id.linearlayout);
		mCustomViewContainer = (FrameLayout) view
				.findViewById(R.id.fullscreen_custom_content);
		updateSettings();
		mWebView.loadUrl("http://www.youtube.com/watch?v=AcB2BqM9MQk");
		return view;

	}

	private void updateSettings() {
		WebSettings webSettings = mWebView.getSettings();
		webSettings.setPluginState(WebSettings.PluginState.ON);
		webSettings.setJavaScriptEnabled(true);
		webSettings.setUseWideViewPort(true);
		webSettings.setLoadWithOverviewMode(true);
		webSettings.setBuiltInZoomControls(true);
		//webSettings.setUserAgentString("Mozilla/5.0 (iPhone; U; CPU like Mac OS X; en) AppleWebKit/420+ (KHTML, like Gecko) Version/3.0 Mobile/1A543a Safari/419.3");
		mWebView.setWebViewClient(new CustomWebViewClient());
	}

	private class CustomWebViewClient extends WebViewClient {
		@Override
		public boolean shouldOverrideUrlLoading(WebView webview, String url) {
			webview.setWebChromeClient(new WebChromeClient() {
				private View mCustomView;

				@Override
				public void onShowCustomView(View view,
						WebChromeClient.CustomViewCallback callback) {
					// if a view already exists then immediately terminate the
					// new one
					if (mCustomView != null) {
						callback.onCustomViewHidden();
						return;
					}

					// Add the custom view to its container.
					mCustomViewContainer.addView(view,
							COVER_SCREEN_GRAVITY_CENTER);
					mCustomView = view;
					mCustomViewCallback = callback;

					// hide main browser view
					mContentView.setVisibility(View.GONE);

					// Finally show the custom view container.
					mCustomViewContainer.setVisibility(View.VISIBLE);
					mCustomViewContainer.bringToFront();
				}

			});
			webview.loadUrl(url);
			return true;
		}
	}

}