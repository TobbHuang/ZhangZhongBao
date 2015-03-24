package com.example.pekinguniverdemo;

import com.example.pekinguniverdemo.constantdata.ConstantData;
import com.example.pekinguniverdemo.view.BorrowSituationView;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BorrowSituationActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_borrow_situation);
		
		LinearLayout ll=(LinearLayout)findViewById(R.id.ll_borrowsituation);
		BorrowSituationView temp=new BorrowSituationView();
		for(int i=0;i<ConstantData.BorrowNum;i++){
			ll.addView(temp.CreateView(this, i));
		}
		
		TextView tv=(TextView)findViewById(R.id.tv_borrowsituation_back);
		tv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
	}
}
