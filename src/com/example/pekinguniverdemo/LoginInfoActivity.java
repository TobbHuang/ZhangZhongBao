package com.example.pekinguniverdemo;

import com.example.pekinguniverdemo.constantdata.ConstantData;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.TextView;

public class LoginInfoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login_info);
		
		TextView tv1=(TextView)findViewById(R.id.tv_logininfo1);
		tv1.setText(ConstantData.IDCard[ConstantData.currentUserID]);
		
		TextView tv2=(TextView)findViewById(R.id.tv_logininfo2);
		if (ConstantData.isUserBorrow[ConstantData.currentUserID])
			tv2.setText("½è¿î");
		else
			tv2.setText("³ö½è");
		
		TextView tv3=(TextView)findViewById(R.id.tv_logininfo_back);
		tv3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
	}
}
