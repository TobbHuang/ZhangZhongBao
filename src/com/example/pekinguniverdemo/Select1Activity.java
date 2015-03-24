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

public class Select1Activity extends Activity {
	
	EditText et1,et2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_select1);
		
		et1=(EditText)findViewById(R.id.et_select1_1);
		et2=(EditText)findViewById(R.id.et_select1_2);
		
		Button btn=(Button)findViewById(R.id.btn_select1_ok);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ConstantData.Type1 = et1.getText().toString()
						+ et2.getText().toString();
				Toast.makeText(Select1Activity.this, "…Ë÷√≥…π¶", Toast.LENGTH_SHORT).show();
				finish();
			}
		});
		
	}
}
