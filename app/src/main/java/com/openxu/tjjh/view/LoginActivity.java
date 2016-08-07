package com.openxu.tjjh.view;

import android.app.Activity;
import android.app.ProgressDialog;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.openxu.tjjh.R;
import com.openxu.tjjh.service.TranService;
import com.openxu.tjjh.service.TranService.ServiceBinder;
import com.openxu.tjjh.utils.Constant;
import com.openxu.tjjh.utils.SpUtils;
import com.openxu.tjjh.utils.ToastUtil;
import com.openxu.tjjh.utils.Utils;

public class LoginActivity extends Activity{
	
	private String TAG = "LoginActivity";
	private Context mContext;
	private ProgressDialog dialog;
	
	private EditText et_name;
	private EditText et_pasw;

	private SpUtils spUtils;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		mContext = this;
		dialog = new ProgressDialog(this);
		dialog.setCanceledOnTouchOutside(false);
		et_name = (EditText) findViewById(R.id.et_name);
		et_pasw = (EditText) findViewById(R.id.et_pasw);

		spUtils = SpUtils.getInstance(this);
	}
	
	public void login(View v){
		String name = et_name.getText().toString().trim();
		String pasw = et_pasw.getText().toString().trim();
		
		Intent intent = new Intent(mContext, MainActivity.class);
		//bcsy2010
		if("admin".equalsIgnoreCase(name)&&"bcsy2010".equalsIgnoreCase(pasw))
			spUtils.setIdentity(SpUtils.IDENTITY_ADMIN);
		else
			spUtils.setIdentity(SpUtils.IDENTITY_NORMAL);

//		spUtils.setIdentity(SpUtils.IDENTITY_ADMIN);

		startActivity(intent);
		finish();
	}
	


}
