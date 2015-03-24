package com.example.pekinguniverdemo;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.R.string;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
/*
@���ߣ�֣���� http://blog.csdn.net/nuptboyzhb
�ο���Google Android����������ʵս
*/
public class FSExplorer extends Activity implements OnItemClickListener {
	private static final String TAG = "FSExplorer";
	private static final int IM_PARENT = Menu.FIRST + 1;
	private static final int IM_BACK = IM_PARENT + 1;
	private static final String DYNAMICACTION = "njupt.zhb.sendpath";
	ListView itemlist = null;
	String path;
	List<Map<String, Object>> list;
	public void sendPathToActivity(String path){
		Intent intent = new Intent();
		intent.setAction(DYNAMICACTION);
		intent.putExtra("path", path);
		sendBroadcast(intent);
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.files);
		setTitle("�ļ������");
		itemlist = (ListView) findViewById(R.id.itemlist);
		
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){  
			path = Environment.getExternalStorageDirectory().getAbsolutePath();
		} 
		
		refreshListItems(path);
	}
    /*����path����·���б�*/
	private void refreshListItems(String path) {
		setTitle("�ļ������ > "+path);
		list = buildListForSimpleAdapter(path);
		SimpleAdapter notes = new SimpleAdapter(this, list, R.layout.file_row,
				new String[] { "name", "path" ,"img"}, new int[] { R.id.name,
						R.id.desc ,R.id.img});
		itemlist.setAdapter(notes);
		itemlist.setOnItemClickListener(this);
		itemlist.setSelection(0);
	}
    /*����·������һ������·�����б�*/
	private List<Map<String, Object>> buildListForSimpleAdapter(String path) {
		File[] files = new File(path).listFiles();
		List<Map<String, Object>> list;
		if (files == null)
			list = new ArrayList<Map<String, Object>>(2);
		else
			list = new ArrayList<Map<String, Object>>(files.length + 2);
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("name", "/");
		root.put("img", R.drawable.file_root);
		root.put("path", "���ظ�Ŀ¼");
		list.add(root);
		Map<String, Object> pmap = new HashMap<String, Object>();
		pmap.put("name", "..");
		pmap.put("img", R.drawable.file_paranet);
		pmap.put("path", "�����ϲ�Ŀ¼");
		list.add(pmap);
		if (files != null) {
			for (File file : files) {
				Map<String, Object> map = new HashMap<String, Object>();
				if (file.isDirectory()) {
					map.put("img", R.drawable.directory);
				} else {
					map.put("img", R.drawable.file_doc);
				}
				map.put("name", file.getName());
				map.put("path", file.getAbsolutePath());
				list.add(map);
			}
		}
		return list;
	}
	/*��ת����һ��*/
	private void goToParent() {
		if (path.equals(Environment.getExternalStorageDirectory().getAbsolutePath()))
			Toast.makeText(FSExplorer.this, "�Ѿ��Ǹ�Ŀ¼", Toast.LENGTH_SHORT)
					.show();
		else {
			File file = new File(path);
			File str_pa = file.getParentFile();
			path = str_pa.getAbsolutePath();
			refreshListItems(path);

		}
	}
    /*ʵ��OnItemClickListener�ӿ�*/
	@Override
	public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
		Log.i(TAG, "item clicked! [" + position + "]");
		if (position == 0) {
			path = Environment.getExternalStorageDirectory().getAbsolutePath();
			refreshListItems(path);
		}else if(position == 1){
			goToParent();
		} else {
			path = (String) list.get(position).get("path");
			File file = new File(path);
			if (file.isDirectory())
				refreshListItems(path);
			else
			{
				Intent intent = new Intent();
				intent.putExtra("path", path);
				setResult(RESULT_OK, intent);
				finish();
			}
			
		}

	}

}

