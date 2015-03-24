package com.example.pekinguniverdemo;

import com.example.pekinguniverdemo.constantdata.ConstantData;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends Activity {
	
	TextView tv_login_back,tv_login_register;
	EditText et_login_idcard,et_login_psw;
	Button btn_login_ok;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);
		
		tv_login_back=(TextView)findViewById(R.id.tv_login_back);
		et_login_idcard=(EditText)findViewById(R.id.et_login_idcard);
		et_login_psw=(EditText)findViewById(R.id.et_login_psw);
		btn_login_ok=(Button)findViewById(R.id.btn_login_ok);
		tv_login_register=(TextView)findViewById(R.id.tv_login_register);
		
		tv_login_back.setOnClickListener(onClickListener);
		btn_login_ok.setOnClickListener(onClickListener);
		tv_login_register.setOnClickListener(onClickListener);
		
	}
	
	OnClickListener onClickListener=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch(v.getId()){
			case R.id.tv_login_back:
				finish();
				break;
			case R.id.btn_login_ok:
				if (et_login_idcard.getText().toString().equals("")
						|| et_login_psw.getText().toString().equals(""))
					new AlertDialog.Builder(LoginActivity.this)
							.setMessage("请输入您的身份证号和密码")
							.setPositiveButton("确定", null).show();
				else{
					int i = CheckIDCardPsw(
							et_login_idcard.getText().toString(), et_login_psw
									.getText().toString());
					if(i==-2){
						new AlertDialog.Builder(LoginActivity.this)
						.setMessage("帐户不存在")
						.setPositiveButton("确定", null).show();
					}
					else if(i==-1){
						new AlertDialog.Builder(LoginActivity.this)
						.setMessage("密码错误")
						.setPositiveButton("确定", null).show();
					}
					else{
						ConstantData.isLogin=true;
						ConstantData.currentUserID=i;
						if(ConstantData.isFirstLogin[i]){
							startActivity(new Intent(LoginActivity.this,FirstLoginActivity.class));
							ConstantData.isFirstLogin[i]=false;
							finish();
						}
						else{
							finish();
						}
					}
				}
				break;
			case R.id.tv_login_register:
				startActivity(new Intent(LoginActivity.this,Register1Activity.class));
				break;
			}
		}
	};
	
	//返回0帐户不存在，1密码错误，2登录成功
	int CheckIDCardPsw(String IDCard,String psw){
		int i=0;
		for(;i<ConstantData.UserNum;i++){
			if(IDCard.equals(ConstantData.IDCard[i])){
				if(psw.equals(ConstantData.pwd[i]))
					return i;
				else
					return -1;
			}
		}
		return -2;
	}
	
}
