package com.example.pekinguniverdemo;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

/**
 * 锟睫革拷锟节ｏ拷2013-2-28 17:03:35
 * 	锟斤拷锟斤拷 ListView item 锟斤拷锟斤拷锟接κэ拷埽锟�
 * 
 * @author Yichou
 *
 */
public class PopMenu1 implements OnItemClickListener {
	public interface OnItemClickListener {
		public void onItemClick(int ID,int index);
	}
	
	private ArrayList<String> itemList;
	private Context context;
	private PopupWindow popupWindow;
	private ListView listView;
	private OnItemClickListener listener;
	private LayoutInflater inflater;
	int ID;

	
	public PopMenu1(Context context) {
		this.context = context;

		itemList = new ArrayList<String>(5);

		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.popmenu2, null);

		listView = (ListView) view.findViewById(R.id.listView);
		listView.setAdapter(new PopAdapter());
		listView.setOnItemClickListener(this);

		popupWindow = new PopupWindow(view, 
				context.getResources().getDimensionPixelSize(R.dimen.popmenu_width), 
				LayoutParams.WRAP_CONTENT);

		popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		if (listener != null) {
			listener.onItemClick(ID,position);
		}
		dismiss();
	}
	
	public void setOnItemClickListener(OnItemClickListener listener) {
		 this.listener = listener;
	}


	public void addItems(String[] items) {
		for (String s : items)
			itemList.add(s);
	}


	public void addItem(String item) {
		itemList.add(item);
	}


	public void showAsDropDown(View parent) {
		
		popupWindow.showAsDropDown(parent, 10,
		context.getResources().getDimensionPixelSize(R.dimen.popmenu_yoff));

		popupWindow.setFocusable(true);

		popupWindow.setOutsideTouchable(true);
		popupWindow.update();
	}


	public void dismiss() {
		popupWindow.dismiss();
	}
	
	public void setID(int ID){
		this.ID=ID;
	}

	private final class PopAdapter extends BaseAdapter {
		@Override
		public int getCount() {
			return itemList.size();
		}

		@Override
		public Object getItem(int position) {
			return itemList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			if (convertView == null) {
				convertView = inflater.inflate(R.layout.pomenu_item, null);
				holder = new ViewHolder();
				convertView.setTag(holder);
				holder.groupItem = (TextView) convertView.findViewById(R.id.textView);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			holder.groupItem.setText(itemList.get(position));

			return convertView;
		}

		private final class ViewHolder {
			TextView groupItem;
		}
	}
}
