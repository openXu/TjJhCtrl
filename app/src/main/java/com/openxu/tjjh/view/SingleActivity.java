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

public class SingleActivity extends BaseActivity {

	private TextView tv_open_1,tv_close_1,
			tv_open_2,tv_close_2,
			tv_open_3,tv_close_3,
			tv_open_4,tv_close_4,
			tv_s_open,tv_s_close;

	@Override
	protected void initView() {
		setContentView(R.layout.activity_single);

		tv_open_1 = (TextView)findViewById(R.id.tv_open_1);
		tv_close_1 = (TextView)findViewById(R.id.tv_close_1);
		tv_open_2 = (TextView)findViewById(R.id.tv_open_2);
		tv_close_2 = (TextView)findViewById(R.id.tv_close_2);
		tv_open_3 = (TextView)findViewById(R.id.tv_open_3);
		tv_close_3 = (TextView)findViewById(R.id.tv_close_3);
		tv_open_4 = (TextView)findViewById(R.id.tv_open_4);
		tv_close_4 = (TextView)findViewById(R.id.tv_close_4);
		tv_s_open = (TextView)findViewById(R.id.tv_s_open);
		tv_s_close = (TextView)findViewById(R.id.tv_s_close);
		tv_open_1.setOnClickListener(this);
		tv_close_1.setOnClickListener(this);
		tv_open_2.setOnClickListener(this);
		tv_close_2.setOnClickListener(this);
		tv_open_3.setOnClickListener(this);
		tv_close_3.setOnClickListener(this);
		tv_open_4.setOnClickListener(this);
		tv_close_4.setOnClickListener(this);
		tv_s_open.setOnClickListener(this);
		tv_s_close.setOnClickListener(this);
		onConfigurationChanged(getResources().getConfiguration());
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
			case R.id.tv_open_1:
				startCommand(Command.SINGLE_1_OPEN);
				break;
			case R.id.tv_close_1:
				startCommand(Command.SINGLE_1_CLOSE);
				break;
			case R.id.tv_open_2:
				startCommand(Command.SINGLE_2_OPEN);
				break;
			case R.id.tv_close_2:
				startCommand(Command.SINGLE_2_CLOSE);
				break;
			case R.id.tv_open_3:
				startCommand(Command.SINGLE_3_OPEN);
				break;
			case R.id.tv_close_3:
				startCommand(Command.SINGLE_3_CLOSE);
				break;
			case R.id.tv_open_4:
				startCommand(Command.SINGLE_4_OPEN);
				break;
			case R.id.tv_close_4:
				startCommand(Command.SINGLE_4_CLOSE);
				break;

			case R.id.tv_s_open:
				startCommand(Command.SINGLE_S_OPEN);
				break;
			case R.id.tv_s_close:
				startCommand(Command.SINGLE_S_CLOSE);
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
