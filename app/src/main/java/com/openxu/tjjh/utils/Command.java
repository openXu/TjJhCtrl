package com.openxu.tjjh.utils;

public class Command {

	//设备控制（连续发送的代码，隔1000ms发送一个）
	//设备全开
	public static final String[] DEVS_OPEN = {
			"*B012FXX#",
			"*B022FXX#",
			"*B032FXX#",
			"*B042FXX#",
			"*H05XFXX#"
	};
	public static final String[] DEVS_CLOSE = {
			"*B012EXX#",
			"*B022EXX#",
			"*B032EXX#",
			"*B042EXX#",
			"*H05XEXX#"
	};
	//播放
	public static final String[] DEVS_PLAY = {
			"*F05XPXX#"

	};
	//停止
	public static final String[] DEVS_STOP = {
		"*F05XSXX#"
	};
	//音量-
	public static final String[] DEVS_V_J = {
			"*F05XV-X#"
	};
	//音量+
	public static final String[] DEVS_V_A = {
			"*F05XV+X#"
	};


	//单设备开关
	//投影机1开	*B012FXX#
	public static final String[] SINGLE_1_OPEN = {
			"*B012FXX#"
	};
	//投影机1关	*B012EXX#
	public static final String[] SINGLE_1_CLOSE = {
			"*B012EXX#"
	};
	//投影机2开
	public static final String[] SINGLE_2_OPEN = {
			"*B022FXX#"
	};
	//投影机2关
	public static final String[] SINGLE_2_CLOSE = {
			"B022EXX#"
	};
	//投影机3开
	public static final String[] SINGLE_3_OPEN = {
			"*B032FXX#"
	};
	//投影机3关
	public static final String[] SINGLE_3_CLOSE = {
			"*B032EXX#"
	};
	//投影机4开
	public static final String[] SINGLE_4_OPEN = {
			"*B042FXX#"
	};
	//投影机4关
	public static final String[] SINGLE_4_CLOSE = {
			"*B042EXX#"
	};
	//播放服务器开
	public static final String[] SINGLE_S_OPEN = {
			"*H05XFXX#"
	};
	//播放服务器关
	public static final String[] SINGLE_S_CLOSE = {
			"*H05XEXX#"
	};
}
