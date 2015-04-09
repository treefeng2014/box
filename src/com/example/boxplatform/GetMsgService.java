package com.example.boxplatform;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.widget.Toast;

import com.example.boxplatform.define.Constants;
import com.example.boxplatform.define.SharePreferenceUtil;
import com.example.boxplatform.define.User;
import com.example.boxplatform.network.Client;
import com.example.boxplatform.network.ClientInputThread;
import com.example.boxplatform.network.ClientOutputThread;
import com.example.boxplatform.network.MessageListener;
import com.example.boxplatform.network.TranObject;
import com.example.boxplatform.network.TranObjectType;

/**
 * 收取消息服务
 * 
 * @author way
 * 
 */
public class GetMsgService extends Service {
	private static final int MSG = 0x001;
	private MyApplication application;
	private Client client;
	private NotificationManager mNotificationManager;
	private boolean isStart = false;// 是否与服务器连接上
	private Notification mNotification;
	private Context mContext = this;
	private SharePreferenceUtil util;

	// 收到用户按返回键发出的广播，就显示通知栏
	private BroadcastReceiver backKeyReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			Toast.makeText(context, "进入后台运行", 0).show();
			//setMsgNotification();
		}
	};
	// 用来更新通知栏消息的handler
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case MSG:
				break;

			default:
				break;
			}
		}
	};

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {// 在onCreate方法里面注册广播接收者
		// TODO Auto-generated method stub
		super.onCreate();
		
		IntentFilter filter = new IntentFilter();
		filter.addAction(Constants.BACKKEY_ACTION);
		registerReceiver(backKeyReceiver, filter);
		mNotificationManager = (NotificationManager) getSystemService(android.content.Context.NOTIFICATION_SERVICE);
		application = (MyApplication) this.getApplicationContext();
		client = application.getClient2OuterServer();
		
	}

	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		
		System.out.println("client start:" + application.isbClient2OuterServer() + "wangfeng");
		
		if (application.isbClient2OuterServer()) {
			ClientInputThread in = client.getClientInputThread();
			in.setMessageListener(new MessageListener() {
				@Override
				public void Message(TranObject msg) {
					 	System.out.println("GetMsgService:" + msg);
					 	Intent broadCast = new Intent();
						broadCast.setAction(Constants.ACTION);
						broadCast.putExtra(Constants.MSGKEY, msg);
						System.out.println("Getmsgserverice-----广播----test");
						sendBroadcast(broadCast);// 把收到的消息已广播的形式发送出去
					
				}
			});
		}
		
	}

	@Override
	// 在服务被摧毁时，做一些事情
	public void onDestroy() {
		super.onDestroy();
		unregisterReceiver(backKeyReceiver);
		util = new SharePreferenceUtil(getApplicationContext(),
				Constants.SAVE_USER);
		mNotificationManager.cancel(Constants.NOTIFY_ID);
		// 给服务器发送下线消息
		if (isStart) {
			ClientOutputThread out = client.getClientOutputThread();
			TranObject<User> o = new TranObject<User>(TranObjectType.LOGOUT);
			User u = new User();
			u.setId(Integer.parseInt(util.getId()));
			o.setObject(u);
			out.setMsg(o);
			// 发送完之后，关闭client
			out.setStart(false);
			client.getClientInputThread().setStart(false);
		}
		
	}

	/**
	 * 创建通知
	 */
	private void setMsgNotification() {
		/*
		int icon = R.drawable.ic_launcher;
		CharSequence tickerText = "";
		long when = System.currentTimeMillis();
		mNotification = new Notification(icon, tickerText, when);

		// 放置在"正在运行"栏目中
		mNotification.flags = Notification.FLAG_ONGOING_EVENT;

		RemoteViews contentView = new RemoteViews(mContext.getPackageName(),
				R.layout.notify);
		contentView.setTextViewText(R.id.notify_name, util.getName());
		contentView.setTextViewText(R.id.notify_msg, "手机密聊正在后台运行");
		contentView.setTextViewText(R.id.notify_time, MyDate.getDate());
		// 指定个性化视图
		mNotification.contentView = contentView;
		Intent intent = new Intent(this, MainActivity.class);
		PendingIntent contentIntent = PendingIntent.getActivity(mContext, 0,
				intent, PendingIntent.FLAG_UPDATE_CURRENT);
		// 指定内容意图
		mNotification.contentIntent = contentIntent;
		mNotificationManager.notify(Constants.NOTIFY_ID, mNotification);
	*/
	}
	
	
}
