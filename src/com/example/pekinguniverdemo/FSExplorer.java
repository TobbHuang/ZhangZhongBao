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
@作者：郑海波 http://blog.csdn.net/nuptboyzhb
参考：Google Android开发入门与实战
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
		setTitle("文件浏览器");
		itemlist = (ListView) findViewById(R.id.itemlist);
		
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){  
			path = Environment.getExternalStorageDirectory().getAbsolutePath();
		} 
		
		refreshListItems(path);
	}
    /*根据path更新路径列表*/
	private void refreshListItems(String path) {
		setTitle("文件浏览器 > "+path);
		list = buildListForSimpleAdapter(path);
		SimpleAdapter notes = new SimpleAdapter(this, list, R.layout.file_row,
				new String[] { "name", "path" ,"img"}, new int[] { R.id.name,
						R.id.desc ,R.id.img});
		itemlist.setAdapter(notes);
		itemlist.setOnItemClickListener(this);
		itemlist.setSelection(0);
	}
    /*根据路径生成一个包含路径的列表*/
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
		root.put("path", "返回根目录");
		list.add(root);
		Map<String, Object> pmap = new HashMap<String, Object>();
		pmap.put("name", "..");
		pmap.put("img", R.drawable.file_paranet);
		pmap.put("path", "返回上层目录");
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
	/*跳转到上一层*/
	private void goToParent() {
		if (path.equals(Environment.getExternalStorageDirectory().getAbsolutePath()))
			Toast.makeText(FSExplorer.this, "已经是根目录", Toast.LENGTH_SHORT)
					.show();
		else {
			File file = new File(path);
			File str_pa = file.getParentFile();
			path = str_pa.getAbsolutePath();
			refreshListItems(path);

		}
	}
    /*实现OnItemClickListener接口*/
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

