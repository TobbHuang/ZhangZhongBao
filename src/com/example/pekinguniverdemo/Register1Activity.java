package com.example.pekinguniverdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class Register1Activity extends Activity {
	
	TextView tv_register1_button1,tv_register1_button2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_register1);
		
		tv_register1_button1=(TextView)findViewById(R.id.tv_register1_button1);
		tv_register1_button2=(TextView)findViewById(R.id.tv_register1_button2);
		tv_register1_button1.setOnClickListener(onClickListener);
		tv_register1_button2.setOnClickListener(onClickListener);
		
	}
	
	OnClickListener onClickListener=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch(v.getId()){
			case R.id.tv_register1_button1:
				startActivity(new Intent(Register1Activity.this,Register2Activity.class));
				finish();
				break;
			case R.id.tv_register1_button2:
				startActivity(new Intent(Register1Activity.this,Register2Activity.class));
				finish();
				break;
			}
		}
	};
	
}
