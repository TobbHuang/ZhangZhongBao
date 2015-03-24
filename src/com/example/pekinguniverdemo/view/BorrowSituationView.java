package com.example.pekinguniverdemo.view;

import com.example.pekinguniverdemo.constantdata.ConstantData;

import android.content.Context;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BorrowSituationView {
	public LinearLayout CreateView(Context context,int i){
		
		LinearLayout.LayoutParams p1 = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, 160);

		LinearLayout ll = new LinearLayout(context);
		ll.setLayoutParams(p1);
		ll.setOrientation(LinearLayout.HORIZONTAL);

		LinearLayout.LayoutParams p2 = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1);
		p2.gravity=Gravity.CENTER;

		TextView tv1 = new TextView(context);
		tv1.setLayoutParams(p2);
		tv1.setGravity(Gravity.CENTER);
		tv1.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
		tv1.setText(ConstantData.BorrowTitle[i]);
		ll.addView(tv1);
		
		TextView tv2 = new TextView(context);
		tv2.setLayoutParams(p2);
		tv2.setGravity(Gravity.CENTER);
		tv2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
		tv2.setText(ConstantData.BorrowAmount[i]+"");
		ll.addView(tv2);
		
		TextView tv3 = new TextView(context);
		tv3.setLayoutParams(p2);
		tv3.setGravity(Gravity.CENTER);
		tv3.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
		tv3.setText(ConstantData.BorrowDoWhat[i]);
		ll.addView(tv3);
		
		TextView tv4 = new TextView(context);
		tv4.setLayoutParams(p2);
		tv4.setGravity(Gravity.CENTER);
		tv4.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
		tv4.setText(ConstantData.BorrowType[i]);
		ll.addView(tv4);
		
		TextView tv5 = new TextView(context);
		tv5.setLayoutParams(p2);
		tv5.setGravity(Gravity.CENTER);
		tv5.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
		tv5.setText(ConstantData.RepayAmount[i]+"");
		ll.addView(tv5);

		return ll;
		
	}
}
