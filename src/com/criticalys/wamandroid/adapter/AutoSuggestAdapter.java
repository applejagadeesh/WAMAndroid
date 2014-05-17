package com.criticalys.wamandroid.adapter;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import com.criticalys.wamandroid.DownloadImageTask;
import com.criticalys.wamandroid.R;

public class AutoSuggestAdapter extends ArrayAdapter<String> {

	private ArrayList<String> suggestionTextList;
	private ArrayList<String> suggestionIconUrlList;

	public AutoSuggestAdapter(Context context, int textViewId) {
		super(context, textViewId);		
	}

	@Override
	public int getCount() {
		return suggestionTextList.size();
	}

	@Override
	public String getItem(int index) {
		return suggestionTextList.get(index);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(getContext());
			convertView = inflater.inflate(R.layout.suggestion_list_item, null);
			TextView suggestionText = (TextView) convertView
					.findViewById(R.id.suggestion_text);
			suggestionText.setText(suggestionTextList.get(position));
			ImageView suggestionIcon = (ImageView) convertView
					.findViewById(R.id.suggestion_icon);
			new DownloadImageTask(suggestionIcon).execute(suggestionIconUrlList
					.get(position));
		}
		return convertView;
	}

	@Override
	public Filter getFilter() {
		Filter filter = new Filter() {

			@Override
			protected FilterResults performFiltering(CharSequence filterText) {
				FilterResults results = new FilterResults();
				suggestionTextList = setResults(filterText);
				results.count = suggestionTextList.size();
				results.values = suggestionTextList;
				return results;
			}

			@Override
			protected void publishResults(CharSequence cs, FilterResults results) {

				if (results != null && results.count > 0) {
					notifyDataSetChanged();
				} else
					notifyDataSetInvalidated();
			}
		};
		return filter;
	}

	public ArrayList<String> setResults(CharSequence filterText) {
		ArrayList<String> names = new ArrayList<String>();		
		ArrayList<String> iconUrls = new ArrayList<String>();
		try {
			String serverURL = "http://www.imdb.com/xml/find?json=1&nr=1&nm=on&q="
					+ URLEncoder.encode(filterText.toString(), "UTF-8");
			StringBuilder builder = new StringBuilder();
			HttpClient client = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(serverURL);
			HttpResponse response = client.execute(httpGet);
			StatusLine statusLine = response.getStatusLine();
			int statusCode = statusLine.getStatusCode();
			if (statusCode == 200) {
				HttpEntity entity = response.getEntity();
				InputStream content = entity.getContent();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(content));
				String line;

				while ((line = reader.readLine()) != null) {
					builder.append(line);
				}
				JSONObject jsonObject = new JSONObject(builder.toString());
				if (jsonObject != null) {
					JSONArray subs = (JSONArray) jsonObject
							.getJSONArray("name_exact");
					if (subs != null) {
						for (int i = 0; i < subs.length(); i++) {
							JSONObject sub = (JSONObject) subs.get(i);
							String suggestionText = sub.getString("name");
							names.add(suggestionText);
							String url = "http://fakeimg.pl/100x100/?text=";
							iconUrls.add(url + suggestionText);
							System.out.println(" names "
									+ sub.getString("name"));
						}
					}
				}

			} else {
				System.out.println("Not 200");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		suggestionIconUrlList = iconUrls;
		return names;
	}
}
