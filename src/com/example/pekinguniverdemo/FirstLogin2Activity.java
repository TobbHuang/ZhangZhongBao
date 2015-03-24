package com.example.pekinguniverdemo;

import com.example.pekinguniverdemo.constantdata.ConstantData;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.TextView;

public class FirstLogin2Activity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_first_login2);
		
		TextView tv1=(TextView)findViewById(R.id.tv_firstlogin);
		tv1.setText(ConstantData.IDCard[ConstantData.currentUserID]);
		
		TextView tv2=(TextView)findViewById(R.id.tv_firstlogin2_back);
		tv2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
	}
}
