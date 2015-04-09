package com.example.boxplatform.define;


public class Constants {
	public static final String SERVER_IP = "10.0.2.2";// server ip
	public static final int SERVER_PORT = 8080;// server port
	
	public static final int REGISTER_FAIL = 0;//regiseter fial flag
	public static final String ACTION = "app.message";//service 将收到的消息广播给所以的activity
	public static final String MSGKEY = "message";//message key
	
	public static final String IP_PORT = "ipPort";//保存IP和port的XML文件名 
	public static final String SAVE_USER = "saveUser";//保存用户信息的XML文件名
	public static final String BACKKEY_ACTION="app.backKey";//返回键发送的广播消息
	public static final int NOTIFY_ID = 0x1011;//通知ID
	
	public static final int BOX_PORT = 4001;
	public static final int INNERSEVER_PORT = 4002;
	public static final int OUTERSERVER_PORT = 8080;
	
	
	public static final int ANDROID_PLATFORM_BOX_INFO    = 1;
	public static final int ANDROID_PLATFORM_DILIVERY_INFO   =2;
	public static final int ANDROID_PLATFORM_GOODS_INFO      =3;
	public static final int ANDROID_PLATFORM_DILIVERY_SUCCESS    = 4;
	public static final int ANDROID_PLATFORM_GOODS_UNREGISTER   = 5;
	public static final int ANDROID_PLATFORM_OVERTIME   = 6;
	public static final int ANDROID_PLATFORM_KEEPALIVE   = 7;
	public static final int ANDROID_PLATFORM_UNEXCETPTION   = 98;
	
	public static final int BOX_SMALL = 1;
	public static final int BOX_MIDDLE = 2;
	public static final int BOX_LARGE = 3;
	
	public static final String ANDROID_PLATFORM = "1001";
	public static final String ANDROID_COMMAND_INIT = "00000000";
	public static final int INIT_OK = 1;
	public static final int DELIVERY_CHECK_OK = 2;
	public static final int DELIVERY_CHECK_FAILED = 3;
	public static final int GOODS_CHECK_OK = 4;
	public static final int GOODS_CHECK_FAILED = 5;
	public static final int SHOW_BARCODE = 6;
	public static final int SHOW_USERCODE = 7;
	//ListView
	public static final int SHOW_WRONG_LISTVIEW = 9;
	public static final int UPDATE_WRONG_LISTVIEW = 10;
	public static final int UPDATE_OVERTIME_LISTVIEW = 11;
	//keycode
	public static final int SHOW_KEYCODE_PASSOWRD = 21;
	public static final int SHOW_KEYCODE_RECEIVERTELEPHONE = 22;
	public static final int SHOW_KEYCODE_AREAID = 23;
	public static final int SHOW_KEYCODE_PASSOWRD_WRONG = 24;
	//system info
	public static final int AREABOXINFOUPDATE = 91;
	public static final int AREACardINFOUPDATE = 92;
	public static final int AREAYUFACLASSINFOUPDATE = 93;
	public static final int KEEPALIVESENDPACKETS= 96;
	public static final int KEEPALIVEMONITOR = 97;
	public static final int MSG2INNSERUPDATE = 98;
	public static final int ANDORID_APPLICATION_EXIT = 99;
	public static final int BOXINFOLENGTH = 26;
	
	public static final String STRBOXOPENHEADER = "88044";
	public static final int MsgTimerValue = 1;
	public static final int KeepaliveTimerValue = 1;
	public static final String tablename = "boxsimple";
	
	//故障提示
	
	public static final int ERROR_INFO2USER = 71;
	
	
	//日志表示
		public  static final String Log_tag="App--Info"; 
}
