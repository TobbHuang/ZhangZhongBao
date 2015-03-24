package com.example.pekinguniverdemo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Register2Activity extends Activity {
	
	TextView tv_register2_back;
	ImageView iv_register2_folder1,iv_register2_folder2,iv_register2_msg;
	EditText et_register2_idcard, et_register2_creditcard, et_register2_name,
			et_register2_phonenumber, et_register2_checknumber,
			et_register2_psw, et_register2_pswAG;
	ImageView iv_register2_idcard,iv_register2_creditcard;
	Button btn_ok;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_register2);
		
		iv_register2_folder1=(ImageView)findViewById(R.id.iv_register2_floder1);
		iv_register2_folder1.setOnClickListener(onClickListener);
		iv_register2_folder2=(ImageView)findViewById(R.id.iv_register2_floder2);
		iv_register2_folder2.setOnClickListener(onClickListener);
		
		tv_register2_back=(TextView)findViewById(R.id.tv_register2_back);
		tv_register2_back.setOnClickListener(onClickListener);
		
		et_register2_idcard=(EditText)findViewById(R.id.et_register2_idcard);
		et_register2_creditcard=(EditText)findViewById(R.id.et_register2_creditcard);
		et_register2_phonenumber=(EditText)findViewById(R.id.et_register2_phonenumber);
		
		iv_register2_idcard=(ImageView)findViewById(R.id.iv_register2_idcard);
		iv_register2_creditcard=(ImageView)findViewById(R.id.iv_register2_creditcard);
		
		iv_register2_msg=(ImageView)findViewById(R.id.iv_register2_msg);
		iv_register2_msg.setOnClickListener(onClickListener);
		
		et_register2_name=(EditText)findViewById(R.id.et_register2_name);
		et_register2_psw=(EditText)findViewById(R.id.et_register2_psw);
		et_register2_pswAG=(EditText)findViewById(R.id.et_register2_pswAG);
		
		
		btn_ok=(Button)findViewById(R.id.btn_register2_ok);
		btn_ok.setOnClickListener(onClickListener);
		
	}
	
	OnClickListener onClickListener=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch(v.getId()){
			case R.id.iv_register2_floder1:
				startActivityForResult(new Intent(Register2Activity.this,
						FSExplorer.class), 100);
				break;
			case R.id.iv_register2_floder2:
				startActivityForResult(new Intent(Register2Activity.this,
						FSExplorer.class), 200);
				break;
			case R.id.tv_register2_back:
				finish();
				break;
			case R.id.iv_register2_msg:
				String content="注册验证码：123456";
				String phone=et_register2_phonenumber.getText().toString();
				if (phone.length() != 11)
					new AlertDialog.Builder(Register2Activity.this)
							.setMessage("请输入正确的手机号")
							.setPositiveButton("确定", null).show();
				else {
					SmsManager smsManager = SmsManager.getDefault();
					smsManager
							.sendTextMessage(phone, null, content, null, null);
					new AlertDialog.Builder(Register2Activity.this)
							.setMessage("短信已发送，请注意查收")
							.setPositiveButton("确定", null).show();
				}
				break;
			case R.id.btn_register2_ok:
				String name=et_register2_name.getText().toString();
				String idcard=et_register2_idcard.getText().toString();
				String pw1=et_register2_psw.getText().toString();
				String pw2=et_register2_pswAG.getText().toString();
				if (name.equals("") || idcard.equals("") || pw1.equals("")
						|| pw2.equals("") ) {
					new AlertDialog.Builder(Register2Activity.this)
					.setMessage("请正确填写信息！")
					.setPositiveButton("确定", null).show();
				}
				else if(!pw1.equals(pw2)){
					new AlertDialog.Builder(Register2Activity.this)
					.setMessage("两次输入密码不一致")
					.setPositiveButton("确定", null).show();
				}
				else {
					new AlertDialog.Builder(Register2Activity.this)
							.setMessage("注册成功，审核需要两天时间")
							.setPositiveButton("确定", null).show();
					finish();
				}
				break;
				
			}
		}
	};
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 100 && resultCode == Activity.RESULT_OK) {
			String path = data.getExtras().getString("path");
			Bitmap bm = BitmapFactory.decodeFile(path);
			iv_register2_idcard.setImageBitmap(bm);
			iv_register2_idcard.setVisibility(View.VISIBLE);
		}
		else if (requestCode == 200 && resultCode == Activity.RESULT_OK) {
			String path = data.getExtras().getString("path");
			Bitmap bm = BitmapFactory.decodeFile(path);
			iv_register2_creditcard.setImageBitmap(bm);
			iv_register2_creditcard.setVisibility(View.VISIBLE);
			Toast.makeText(Register2Activity.this, path, Toast.LENGTH_SHORT).show();
		}
	}
	
}
