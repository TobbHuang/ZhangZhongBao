package com.example.pekinguniverdemo;

import com.example.pekinguniverdemo.constantdata.ConstantData;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BorrowActivity extends Activity {
	
	EditText et1,et2,et3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_borrow);
		
		et1=(EditText)findViewById(R.id.et_borrow_title);
		et2=(EditText)findViewById(R.id.et_borrow_amount);
		et3=(EditText)findViewById(R.id.et_borrow_dowhat);
		
		
		Button btn=(Button)findViewById(R.id.btn_borrow_ok);
		btn.setOnClickListener(onClickListener);
		
		TextView tv=(TextView)findViewById(R.id.tv_borrow_back);
		tv.setOnClickListener(onClickListener);
		
		
	}
	
	OnClickListener onClickListener=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch(v.getId()){
			case R.id.btn_borrow_ok:
				if (et1.getText().toString().equals("")
						|| et2.getText().toString().equals("")
						|| et3.getText().toString().equals("")) {
					new AlertDialog.Builder(BorrowActivity.this)
							.setMessage("请输入完整信息")
							.setPositiveButton("确定", null).show();
				}
				else{
					ConstantData.BorrowTitle[ConstantData.BorrowNum]=et1.getText().toString();
					ConstantData.BorrowAmount[ConstantData.BorrowNum]=Double.parseDouble(et2.getText().toString());
					ConstantData.BorrowDoWhat[ConstantData.BorrowNum]=et3.getText().toString();
					ConstantData.BorrowType[ConstantData.BorrowNum]=ConstantData.Type1;
					ConstantData.BorrowNum++;
					Toast.makeText(BorrowActivity.this, "借款信息发布成功", Toast.LENGTH_SHORT).show();
					finish();
				}
				break;
			case R.id.tv_borrow_back:
				finish();
				break;
			}
			
		}
	};
	
	
}
