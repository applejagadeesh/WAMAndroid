package com.criticalys.wamandroid;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.TextView;

import com.criticalys.wamandroid.adapter.AutoSuggestAdapter;
import com.criticalys.wamandroid.adapter.SidebarNavigationAdapter;
import com.criticalys.wamandroid.util.WAMUtil;
import com.uservoice.uservoicesdk.UserVoice;

public class WAMBaseActivity extends ActionBarActivity {

	private String[] mSideBarItems;
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void setContentView(View view) {
		super.setContentView(view);
	}

	/*
	 * Use this method instead of setContentView if your activity needs a wam
	 * sidebar
	 */
	protected void setContentViewWithSidebar(int layoutResourceId) {
		setContentView(R.layout.layout_global_sidebar);
		// inflate page content here
		ViewGroup contentLayout = (ViewGroup) findViewById(R.id.content_frame);
		LayoutInflater inflater = (LayoutInflater) this
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(layoutResourceId, contentLayout, true);
		// these method to be called after inflating layout_global_sidebar
		setSidebarContent();
		setActionBarContent();
	}

	private void setActionBarContent() {
		ActionBar actionBar = getSupportActionBar();
		// this is necessary since we are using custom search
		actionBar.setDisplayShowCustomEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.global_menu, menu);
		// configure search menu icon
		MenuItem searchItem = menu.findItem(R.id.action_search);
		View searchView = MenuItemCompat.getActionView(searchItem);
		AutoCompleteTextView textView = (AutoCompleteTextView) searchView
				.findViewById(R.id.autocomplete_textview);
		// Create adapter
		AutoSuggestAdapter adapter = new AutoSuggestAdapter(this,
				R.id.suggestion_text);
		// Set adapter to AutoCompleteTextView
		textView.setAdapter(adapter);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// The action bar home/up action should open or close the drawer.
		// ActionBarDrawerToggle will take care of this.
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		// Handle action buttons
		return super.onOptionsItemSelected(item);
	}

	// configure left navigation
	private void setSidebarContent() {
		mSideBarItems = getResources().getStringArray(
				R.array.side_bar_items_array);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);
		// set a custom shadow that overlays the main content when the drawer
		// opens
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
				GravityCompat.START);
		// set up the drawer's list view with items and click listener
		SidebarNavigationAdapter listAdapter = new SidebarNavigationAdapter(
				this, R.layout.drawer_list_item, mSideBarItems);
		mDrawerList.setAdapter(listAdapter);
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

		// enable ActionBar app icon to behave as action to toggle nav drawer
		getSupportActionBar().setHomeButtonEnabled(true);

		// ActionBarDrawerToggle ties together the the proper interactions
		// between the sliding drawer and the action bar app icon
		mDrawerToggle = new ActionBarDrawerToggle(this, /* host Activity */
		mDrawerLayout, /* DrawerLayout object */
		R.drawable.ic_drawer, /* nav drawer image to replace 'Up' caret */
		R.string.drawer_open, /* "open drawer" description for accessibility */
		R.string.drawer_close /* "close drawer" description for accessibility */
		) {
			public void onDrawerClosed(View view) {
				// supportInvalidateOptionsMenu(); // creates call to
				// onPrepareOptionsMenu()
			}

			public void onDrawerOpened(View drawerView) {
				// supportInvalidateOptionsMenu(); // creates call to
				// onPrepareOptionsMenu()
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);
	}

	/* The click listner for ListView in the navigation drawer */
	private class DrawerItemClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			if (((TextView) view).getText().equals("Feedback")) {
				UserVoice.launchUserVoice(WAMBaseActivity.this);
			} else {
				Intent intent = new Intent(WAMBaseActivity.this,MoviesActivity.class);
				intent.putExtra(WamConstants.SIDE_BAR_SELECTED_ITEM, position);
				startActivity(intent);
			}
		}
	}

	/**
	 * When using the ActionBarDrawerToggle, you must call it during
	 * onPostCreate() and onConfigurationChanged()...
	 */

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		// this may b null if you use setContentView rather than
		// setContentViewWithSidebar
		if (mDrawerToggle != null) {
			mDrawerToggle.syncState();
		}
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		if (mDrawerToggle != null) {
			mDrawerToggle.onConfigurationChanged(newConfig);
		}
	}

	/* Called whenever we call invalidateOptionsMenu() */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// If the nav drawer is open, hide action items related to the content
		// view
		// configure other menu options based on user context
		// boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
		return super.onPrepareOptionsMenu(menu);
	}

}
