package com.criticalys.wamandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.criticalys.wamandroid.R;

public class SidebarNavigationAdapter extends ArrayAdapter<String> {

	private Context context;
	private String[] values;
	private int resId;

	public SidebarNavigationAdapter(Context context, int textViewResourceId,
			String[] values) {
		super(context, textViewResourceId, values);
		this.context = context;
		this.values = values;
		this.resId = textViewResourceId;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View rowView;

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		if (values[position].equalsIgnoreCase("Divider")) {
			rowView = inflater.inflate(R.layout.drawer_list_item_separator,
					parent, false);
			return rowView;
		}

		rowView = inflater.inflate(this.resId, parent, false);
		TextView listItemView = (TextView) rowView
				.findViewById(R.id.drawer_list_item_label);
		String textValue = values[position];
		listItemView.setText(textValue);
		return rowView;
	}

}