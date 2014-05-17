package com.criticalys.wamandroid.util;

import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.criticalys.wamandroid.WamConstants;
import com.google.analytics.tracking.android.Fields;
import com.google.analytics.tracking.android.GoogleAnalytics;
import com.google.analytics.tracking.android.Tracker;
import com.uservoice.uservoicesdk.Config;
import com.uservoice.uservoicesdk.UserVoice;

public class WAMUtil {

	private static WAMUtil util = null;

	public static void showTestToast(Context context) {
		Toast.makeText(context, "Test Toast", Toast.LENGTH_SHORT).show();
	}

	public static void showToastShort(Context context, String message) {
		Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
	}

	public static void showToastLong(Context context, String message) {
		Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
	}

	public static WAMUtil getInstance() {
		if (util == null) {
			util = new WAMUtil();
		}
		return util;
	}

	public static void initializeUserVoice(Activity activity) {
		Config config = new Config(WamConstants.USER_VOICE_WEBSITE);
		UserVoice.init(config, activity);
	}

	public static void sendDataToAnalytics(Context context, String hitType,
			String screenName) {
		/*
		 * Send a screen view to Google Analytics by setting a map of parameter
		 * values on the tracker and calling send.
		 */
		Tracker tracker = GoogleAnalytics.getInstance(context).getTracker(
				WamConstants.ANALYTICS_APP_KEY);

		HashMap<String, String> hitParameters = new HashMap<String, String>();
		hitParameters.put(Fields.HIT_TYPE, hitType);
		hitParameters.put(Fields.SCREEN_NAME, screenName);

		tracker.send(hitParameters);
	}

}
