package com.openxu.tjjh.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.view.Display;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.openxu.tjjh.R;
import com.openxu.tjjh.utils.Command;

public class DevicesActivity extends BaseActivity {

	private TextView tv_open, tv_close,
			tv_play,tv_stop,
			tv_vadd,tv_vjian;

	@Override
	protected void initView() {
		setContentView(R.layout.activity_devices_ctrl);
		tv_open = (TextView)findViewById(R.id.tv_open);
		tv_close = (TextView)findViewById(R.id.tv_close);
		tv_play = (TextView)findViewById(R.id.tv_play);
		tv_stop = (TextView)findViewById(R.id.tv_stop);
		tv_vadd = (TextView)findViewById(R.id.tv_vadd);
		tv_vjian = (TextView)findViewById(R.id.tv_vjian);
		tv_close.setOnClickListener(this);
		tv_open.setOnClickListener(this);
		tv_play.setOnClickListener(this);
		tv_stop.setOnClickListener(this);
		tv_vadd.setOnClickListener(this);
		tv_vjian.setOnClickListener(this);
		onConfigurationChanged(getResources().getConfiguration());
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.tv_open:
			startCommand(Command.DEVS_OPEN);
			break;
		case R.id.tv_close:
			startCommand(Command.DEVS_CLOSE);
			break;

		case R.id.tv_play:
			startCommand(Command.DEVS_PLAY);
			break;
		case R.id.tv_stop:
			startCommand(Command.DEVS_STOP);
			break;
		case R.id.tv_vadd:
			startCommand(Command.DEVS_V_A);
			break;
		case R.id.tv_vjian:
			startCommand(Command.DEVS_V_J);
			break;
		}
	}
	/*private void showSure(final int witch){
		AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
		String title = witch == 1?"是否要开馆?":"是否要闭馆?";
		builder.setTitle(title)
				.setPositiveButton("是", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						if(witch == 1){
							startSendData(Command.OPEN);
						}else{
							startSendData(Command.CLOSE);
						}
					}
				})
				.setNegativeButton("否", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						arg0.dismiss();
					}
				});
		builder.create().show();
	}*/
	
}
