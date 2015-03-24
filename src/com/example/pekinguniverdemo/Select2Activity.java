package com.example.pekinguniverdemo;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class Select2Activity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_select2);
		
		TextView tv=(TextView)findViewById(R.id.tv_select2_back);
		tv.setOnClickListener(onClickListener);
		
		Button btn=(Button)findViewById(R.id.btn_select2_ok);
		btn.setOnClickListener(onClickListener);
		
	}
	
	OnClickListener onClickListener=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch(v.getId()){
			case R.id.tv_select2_back:
				finish();
				break;
			case R.id.btn_select2_ok:
				new AlertDialog.Builder(Select2Activity.this)
						.setMessage("设置成功！").setPositiveButton("确定", null)
						.show();
				finish();
				break;
			}
			
		}
	};
	
}
