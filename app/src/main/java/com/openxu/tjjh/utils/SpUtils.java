package com.openxu.tjjh.utils;

import java.util.Calendar;
import java.util.Date;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.openxu.tjjh.utils.Constant;
import com.openxu.tjjh.utils.Utils;

public class SpUtils {

	private String TAG = "XKF";
	private static String SP_NAME = "CTRL_SP";
	private static Context mContext;
	private static SharedPreferences sp;
	private static SpUtils spUtil = new SpUtils();
	// KEY
	private final String KEY_IDENTITY = "identity";       //是否管理

	public static final String KEY_SLEEP = "SEND_SLEEP_NOREAD";
	public static final int SLEEP_DEF = 150;

	// VALUE
	public static int IDENTITY_NORMAL = 1; // 普通用户
	public static int IDENTITY_ADMIN = 2;  // 管理员
	public static int IDENTITY_SUPER = 3;  // 炒鸡管理员

	private SpUtils() {
	}

	public static SpUtils getInstance(Context context) {
		mContext = context;
		if (sp == null && mContext != null) {
			sp = mContext.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
		}
		return spUtil;
	}

	// 用户身份
	public void setIdentity(int identity) {
		saveSp(KEY_IDENTITY, identity);
	}

	public int getIdentity() {
		return sp.getInt(KEY_IDENTITY, IDENTITY_NORMAL);
	}
	// 用户身份
	public void setSleep(int sleep) {
		saveSp(KEY_SLEEP, sleep);
	}

	public int getSleep() {
		return sp.getInt(KEY_SLEEP, SLEEP_DEF);
	}

	private void saveSp(String key, Object value) {
		if (value instanceof String)
			sp.edit().putString(key, (String) value).commit();
		else if (value instanceof Integer)
			sp.edit().putInt(key, (Integer) value).commit();
		else if (value instanceof Boolean)
			sp.edit().putBoolean(key, (Boolean) value).commit();
	}
}
