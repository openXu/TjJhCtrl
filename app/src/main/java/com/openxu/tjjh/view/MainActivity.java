package com.openxu.tjjh.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.openxu.tjjh.R;
import com.openxu.tjjh.utils.Command;

public class MainActivity extends BaseActivity {


	private TextView tv_devs, tv_single;

	@Override
	protected void initView() {
		setContentView(R.layout.activity_main);
		tv_devs = (TextView)findViewById(R.id.tv_devs);
		tv_single = (TextView)findViewById(R.id.tv_single);
		tv_devs.setOnClickListener(this);
		tv_single.setOnClickListener(this);
		onConfigurationChanged(getResources().getConfiguration());
	}


	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.tv_devs:
		startActivity(new Intent(this,DevicesActivity.class ));
			break;
		case R.id.tv_single:
			startActivity(new Intent(this,SingleActivity.class ));
			break;
		default:
			break;
		}
	}





	@Override
	protected void onDestroy() {
		try{
			binder.disConnect();
			unbindService(serviceCon);
			dismissPopupWindow_sending();
			popupWindow_sending= null;
			dismissPopupWindow_command();
			popupWindow_command= null;
		}catch (Exception e){
			e.printStackTrace();
		}

		super.onDestroy();
	}




}
