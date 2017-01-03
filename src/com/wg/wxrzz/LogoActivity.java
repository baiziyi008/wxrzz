package com.wg.wxrzz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

public class LogoActivity extends Activity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(getWindow().getAttributes().width,
				getWindow().getAttributes().height);
		ImageView iv = new ImageView(this);
		iv.setLayoutParams(lp);
		iv.setScaleType(ScaleType.CENTER_CROP);
		iv.setImageResource(R.drawable.logo_healthy_land);

		setContentView(iv);

		final Handler handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				Intent intent = new Intent(LogoActivity.this, WGActivity.class);
				startActivity(intent);
				finish();
			}
		};
		handler.sendEmptyMessageDelayed(0, 1000 * 2);
	}

}