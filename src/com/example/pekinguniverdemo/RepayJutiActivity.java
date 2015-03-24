package com.example.pekinguniverdemo;

import com.example.pekinguniverdemo.constantdata.ConstantData;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RepayJutiActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_repay_juti);
		
		Button btn=(Button)findViewById(R.id.btn_repayjuti);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				EditText et=(EditText)findViewById(R.id.et_repayjuti);
				
				double amount=Double.parseDouble(et.getText().toString());
				
				ConstantData.RepayAmount[RepayActivity.position]+=amount;
				
				Toast.makeText(RepayJutiActivity.this, "还款成功", Toast.LENGTH_SHORT).show();
				finish();
			}
		});
		
	}
}
