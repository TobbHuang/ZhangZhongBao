package com.example.pekinguniverdemo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import com.example.pekinguniverdemo.PopMenu1.OnItemClickListener;
import com.example.pekinguniverdemo.constantdata.ConstantData;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener,
		OnItemClickListener {

	Button btn_btn1_mainpage, btn_btn2_mainpage, btn_btn3_mainpage,
			btn_btn4_mainpage;
	TextView tv_mainpage_login, tv_mainpage_register;
	TextView tv_mainpage_introduction, tv_mainpage_communication,
			tv_mainpage_help;

	private PopMenu1 popMenu1, popMenu2, popMenu3, popMenu4;

	private ViewPager adViewPager;
	private LinearLayout pagerLayout;
	private List<View> pageViews;
	private ImageView[] imageViews;
	private ImageView imageView;
	private AdPageAdapter adapter;
	private AtomicInteger atomicInteger = new AtomicInteger(0);
	private boolean isContinue = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		btn_btn1_mainpage = (Button) findViewById(R.id.btn_btn1_mainpage);
		btn_btn1_mainpage.setOnClickListener(this);
		btn_btn2_mainpage = (Button) findViewById(R.id.btn_btn2_mainpage);
		btn_btn2_mainpage.setOnClickListener(this);
		btn_btn3_mainpage = (Button) findViewById(R.id.btn_btn3_mainpage);
		btn_btn3_mainpage.setOnClickListener(this);
		btn_btn4_mainpage = (Button) findViewById(R.id.btn_btn4_mainpage);
		btn_btn4_mainpage.setOnClickListener(this);

		tv_mainpage_login = (TextView) findViewById(R.id.tv_mainpage_login);
		tv_mainpage_login.setOnClickListener(this);

		tv_mainpage_register = (TextView) findViewById(R.id.tv_mainpage_register);
		tv_mainpage_register.setOnClickListener(this);

		tv_mainpage_introduction = (TextView) findViewById(R.id.tv_mainpage_introduction);
		tv_mainpage_introduction.setOnClickListener(this);

		tv_mainpage_communication = (TextView) findViewById(R.id.tv_mainpage_communication);
		tv_mainpage_communication.setOnClickListener(this);

		tv_mainpage_help = (TextView) findViewById(R.id.tv_mainpage_help);
		tv_mainpage_help.setOnClickListener(this);

		popMenu1 = new PopMenu1(this);
		popMenu1.addItems(new String[] { "借款", "还款", "选择期率/费率" });
		popMenu1.setID(0);
		popMenu1.setOnItemClickListener(this);

		popMenu2 = new PopMenu1(this);
		popMenu2.addItems(new String[] { "信用卡信息", "额度/周期/费率" });
		popMenu2.setID(1);
		popMenu2.setOnItemClickListener(this);

		popMenu3 = new PopMenu1(this);
		popMenu3.addItems(new String[] { "实用计算器", "记账管家" });
		popMenu3.setID(2);
		popMenu3.setOnItemClickListener(this);

		popMenu4 = new PopMenu1(this);
		popMenu4.addItems(new String[] { "登录情况", "借贷情况", "退出" });
		popMenu4.setID(3);
		popMenu4.setOnItemClickListener(this);

		initViewPager();

		// 加入测试帐号数据
		ConstantData.IDCard[0] = "123456";
		ConstantData.pwd[0] = "123456";
		ConstantData.isUserBorrow[0]=true;
		ConstantData.isFirstLogin[0]=true;
		ConstantData.UserNum++;
		
		ConstantData.IDCard[1] = "654321";
		ConstantData.pwd[1] = "654321";
		ConstantData.isUserBorrow[1]=false;
		ConstantData.isFirstLogin[1]=true;
		ConstantData.UserNum++;

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_btn1_mainpage:
			popMenu1.showAsDropDown(v);
			break;
		case R.id.btn_btn2_mainpage:
			popMenu2.showAsDropDown(v);
			break;
		case R.id.btn_btn3_mainpage:
			popMenu3.showAsDropDown(v);
			break;
		case R.id.btn_btn4_mainpage:
			popMenu4.showAsDropDown(v);
			break;
		case R.id.tv_mainpage_login:
			startActivity(new Intent(MainActivity.this, LoginActivity.class));
			break;
		case R.id.tv_mainpage_register:
			startActivity(new Intent(MainActivity.this, Register1Activity.class));
			break;
		case R.id.tv_mainpage_introduction:
			startActivity(new Intent(MainActivity.this,
					IntroductionActivity.class));
			break;
		case R.id.tv_mainpage_communication:
			startActivity(new Intent(MainActivity.this,
					CommunicationActivity.class));
			break;
		case R.id.tv_mainpage_help:
			startActivity(new Intent(MainActivity.this, HelpActivity.class));
			break;
		}
	}

	@Override
	public void onItemClick(int ID, int index) {
		// TODO Auto-generated method stub
		if (ID == 0) {
			switch (index) {
			case 0:
				if(!ConstantData.isLogin)
					startActivity(new Intent(MainActivity.this,
							LoginActivity.class));
				else if(!ConstantData.isUserBorrow[ConstantData.currentUserID]){
					Toast.makeText(MainActivity.this, "请登录借款帐号", Toast.LENGTH_SHORT).show();
				}
				else
					startActivity(new Intent(MainActivity.this,
							BorrowActivity.class));
				break;
			case 1:
				if(!ConstantData.isLogin)
					startActivity(new Intent(MainActivity.this,
							LoginActivity.class));
				else if(!ConstantData.isUserBorrow[ConstantData.currentUserID]){
					Toast.makeText(MainActivity.this, "请登录借款帐号", Toast.LENGTH_SHORT).show();
				}
				else
					startActivity(new Intent(MainActivity.this,
							RepayActivity.class));
				break;
			case 2:
				if (!ConstantData.isLogin)
					startActivity(new Intent(MainActivity.this,
							LoginActivity.class));
				else if(!ConstantData.isUserBorrow[ConstantData.currentUserID]){
					Toast.makeText(MainActivity.this, "请登录借款帐号", Toast.LENGTH_SHORT).show();
				}
				else
					startActivity(new Intent(MainActivity.this,
							Select1Activity.class));
				break;
			}
		} else if (ID == 1) {
			switch (index) {
			case 0:
				if (!ConstantData.isLogin)
					startActivity(new Intent(MainActivity.this,
							LoginActivity.class));
				else if(ConstantData.isUserBorrow[ConstantData.currentUserID]){
					Toast.makeText(MainActivity.this, "请登录出借帐号", Toast.LENGTH_SHORT).show();
				}
				else
					startActivity(new Intent(MainActivity.this,
							CreditInfoActivity.class));
				break;
			case 1:
				if (!ConstantData.isLogin)
					startActivity(new Intent(MainActivity.this,
							LoginActivity.class));
				else if(ConstantData.isUserBorrow[ConstantData.currentUserID]){
					Toast.makeText(MainActivity.this, "请登录出借帐号", Toast.LENGTH_SHORT).show();
				}
				else
					startActivity(new Intent(MainActivity.this,
							Select2Activity.class));
				break;
			}

		} else if (ID == 2) {
			switch (index) {
			case 0:
			case 1:
			}
		} else if (ID == 3) {
			switch (index) {
			case 0:
				if (!ConstantData.isLogin)
					startActivity(new Intent(MainActivity.this,
							LoginActivity.class));
				else
					startActivity(new Intent(MainActivity.this,
							LoginInfoActivity.class));
				break;
			case 1:
				if (!ConstantData.isLogin)
					startActivity(new Intent(MainActivity.this,
							LoginActivity.class));
				else
					startActivity(new Intent(MainActivity.this,
							BorrowSituationActivity.class));
				break;
			case 2:
				finish();
				break;
			}
		}
	}
	

	private void initViewPager() {

		pagerLayout = (LinearLayout) findViewById(R.id.view_pager_content);
		adViewPager = new ViewPager(this);

		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);

		adViewPager.setLayoutParams(new LayoutParams(dm.widthPixels,
				(int) (dm.heightPixels * 0.3)));

		pagerLayout.addView(adViewPager);

		initPageAdapter();

		initCirclePoint();

		adViewPager.setAdapter(adapter);
		adViewPager.setOnPageChangeListener(new AdPageChangeListener());

		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					if (isContinue) {
						viewHandler.sendEmptyMessage(atomicInteger.get());
						atomicOption();
					}
				}
			}
		}).start();
	}

	private void atomicOption() {
		atomicInteger.incrementAndGet();
		if (atomicInteger.get() > imageViews.length - 1) {
			atomicInteger.getAndAdd(-5);
		}
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {

		}
	}

	@SuppressLint("HandlerLeak")
	private final Handler viewHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			adViewPager.setCurrentItem(msg.what);
			super.handleMessage(msg);
		}

	};

	private void initPageAdapter() {
		pageViews = new ArrayList<View>();

		ImageView img1 = new ImageView(this);
		img1.setBackgroundResource(R.drawable.view_add_1);
		pageViews.add(img1);

		ImageView img2 = new ImageView(this);
		img2.setBackgroundResource(R.drawable.view_add_2);
		pageViews.add(img2);

		ImageView img3 = new ImageView(this);
		img3.setBackgroundResource(R.drawable.view_add_3);
		pageViews.add(img3);

		ImageView img4 = new ImageView(this);
		img4.setBackgroundResource(R.drawable.view_add_4);
		pageViews.add(img4);

		ImageView img5 = new ImageView(this);
		img5.setBackgroundResource(R.drawable.view_add_5);
		pageViews.add(img5);

		ImageView img6 = new ImageView(this);
		img6.setBackgroundResource(R.drawable.view_add_6);
		pageViews.add(img6);

		adapter = new AdPageAdapter(pageViews);
	}

	private void initCirclePoint() {
		ViewGroup group = (ViewGroup) findViewById(R.id.viewGroup);
		imageViews = new ImageView[pageViews.size()];
		for (int i = 0; i < pageViews.size(); i++) {
			imageView = new ImageView(this);
			imageView.setLayoutParams(new LayoutParams(10, 10));
			imageViews[i] = imageView;

			if (i == 0) {
				imageViews[i].setBackgroundResource(R.drawable.point_focused);
			} else {
				imageViews[i].setBackgroundResource(R.drawable.point_unfocused);
			}
			group.addView(imageViews[i]);
		}
	}

	private final class AdPageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageSelected(int arg0) {
			atomicInteger.getAndSet(arg0);
			for (int i = 0; i < imageViews.length; i++) {
				imageViews[arg0]
						.setBackgroundResource(R.drawable.point_focused);
				if (arg0 != i) {
					imageViews[i]
							.setBackgroundResource(R.drawable.point_unfocused);
				}
			}
		}
	}

	private final class AdPageAdapter extends PagerAdapter {
		private List<View> views = null;

		public AdPageAdapter(List<View> views) {
			this.views = views;
		}

		@Override
		public void destroyItem(View container, int position, Object object) {
			((ViewPager) container).removeView(views.get(position));
		}

		@Override
		public int getCount() {
			return views.size();
		}

		@Override
		public Object instantiateItem(View container, int position) {
			((ViewPager) container).addView(views.get(position), 0);
			return views.get(position);
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}
	}

}
