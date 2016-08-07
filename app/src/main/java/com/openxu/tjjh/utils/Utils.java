package com.openxu.tjjh.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Utils {

	private static boolean DEBUG = true;

	public static String getTime(){
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//可以方便地修改日期格式

		String time = dateFormat.format(now); 
		return time;
	}
	public static void TOAST(Context context, String msg) {
		if (DEBUG)
			Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
	}

	public static void TOAST(Context context, int strid) {
		Toast.makeText(context, strid, Toast.LENGTH_SHORT).show();
	}

	public static void LOG_V(String TAG, String msg) {
		if (DEBUG)
			Log.v(TAG, msg);
	}

	public static void LOG_I(String TAG, String msg) {
		if (DEBUG)
			Log.i(TAG, msg);
	}

	public static void LOG_D(String TAG, String msg) {
		if (DEBUG)
			Log.d(TAG, msg);
	}

	public static void LOG_W(String TAG, String msg) {
		if (DEBUG)
			Log.w(TAG, msg);
	}

	public static void LOG_E(String TAG, String msg) {
		if (DEBUG)
			Log.e(TAG, msg);
	}

	public static void sysout(String clazz, String text) {
		if (DEBUG)
			System.out.println(clazz + " " + text);
	}

	// 字节数组转换16进制字符串
	public static String byte2Hex(byte[] in) {
		if(in == null)
			return null;
		String out = "";
		for (int i = 0; i < in.length; i++) {
			out += byteToHexString(in[i]);
		}
		return out;
	}

	// 字节数组转换字符�?
	public static String byte2HexSplit(byte[] in) {
		String out = "";
		for (int i = 0; i < in.length; i++) {
			out += byteToHexString(in[i]) + ",";
		}
		out = out.substring(0, out.lastIndexOf(","));
		return out;
	}

	public static String byteToHexString(byte ib) {
		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
				'B', 'C', 'D', 'E', 'F' };
		char[] ob = new char[2];
		ob[0] = Digit[(ib >>> 4) & 0X0F];
		ob[1] = Digit[ib & 0X0F];
		String s = new String(ob);
		return s;
	}

	/**
	 * 十六进制字符串转换成字节数组
	 * 010203040506--->byte[]{0x01,0x02,0x03,0x04,0x05,0x06}
	 * @param s
	 * @return
	 */
	public static byte[] fromHex(String s) {
		s = s.toLowerCase(Locale.ENGLISH);
		int bytLength =(s.length()) / 2;
		if((s.length()) % 2!=0)
			bytLength++;
		byte[] bs = new byte[bytLength];
		for(int i = 0; i<s.length(); i+=2){
			int length =i+2;
			if((i+2)>s.length())
					length=s.length();
			String one = s.substring(i, length);
			bs[i/2]= (byte) Integer.parseInt(one,16);    
		}
		return bs;
	}
}
