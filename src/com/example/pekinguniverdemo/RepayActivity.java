package com.example.pekinguniverdemo;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.pekinguniverdemo.constantdata.ConstantData;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class RepayActivity extends Activity {
	
	static int position;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_repay);

		ListView listview = (ListView) findViewById(R.id.listview1);

		ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();

		for (int i = 0; i < ConstantData.BorrowNum; i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("title", ConstantData.BorrowTitle[i]);
			map.put("amount", ConstantData.BorrowAmount[i] + "");
			map.put("dowhat", ConstantData.BorrowDoWhat[i]);
			map.put("type", ConstantData.BorrowType[i]);
			map.put("repay", ConstantData.RepayAmount[i] + "");
			mylist.add(map);
		}
		// 生成适配器，数组===》ListItem
		SimpleAdapter mSchedule = new SimpleAdapter(this, // 没什么解释
				mylist,// 数据来源
				R.layout.item1,// ListItem的XML实现

				// 动态数组与ListItem对应的子项
				new String[] { "title", "amount", "dowhat", "type", "repay" },

				// ListItem的XML文件里面的两个TextView ID
				new int[] { R.id.tv_item1_1, R.id.tv_item1_2, R.id.tv_item1_3,
						R.id.tv_item1_4, R.id.tv_item1_5 });
		// 添加并且显示
		listview.setAdapter(mSchedule);
		listview.setOnItemClickListener(new onItemClickListener());
		
		TextView tv=(TextView)findViewById(R.id.tv_repay_back);
		tv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
	}
	
	class onItemClickListener implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			position=arg2;
			startActivity(new Intent(RepayActivity.this,RepayJutiActivity.class));
			finish();
		}
	}
}
