package com.openxu.tjjh.utils;

public class Constant {

		public static final String IP = "192.168.1.103";
		public static final int PORT = 8899;
//public static final String IP = "192.168.1.106";
//		public static final int PORT = 11113;

	public static final int reConTime = 3000;   //重连尝试时间间隔
	public static final int heartTime = 10000;   //发送心跳包时间间隔
	
	public static final int HANDMSG_CONNECTED = 1;
	public static final int HANDMSG_CONNECT_FAIL = 2;
	
	public static final int HANDMSG_SENDDING = 4;   //正在发送
	public static final int HANDMSG_NOTFOND_COMMAND = 5;
	public static final int HANDMSG_SEND_SEC = 6;
	public static final int HANDMSG_SEND_NOFK = 7;   //无反馈
	public static final int HANDMSG_DO_FAIL = 8;   //执行失败
	public static final int HANDMSG_SEND_FAIL =9;
	public static final int HANDMSG_SEND_NOCONNECT = 10;
	public static final int HANDMSG_SEND_COMMAND = 11;    //命令
	
	public static final int SCREEN_LAND = 1; // 横屏
	public static final int SCREEN_PORT = 2; // 竖屏
	
}
