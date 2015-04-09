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
 * ��ȡ��Ϣ����
 * 
 * @author way
 * 
 */
public class GetMsgService extends Service {
	private static final int MSG = 0x001;
	private MyApplication application;
	private Client client;
	private NotificationManager mNotificationManager;
	private boolean isStart = false;// �Ƿ��������������
	private Notification mNotification;
	private Context mContext = this;
	private SharePreferenceUtil util;

	// �յ��û������ؼ������Ĺ㲥������ʾ֪ͨ��
	private BroadcastReceiver backKeyReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			Toast.makeText(context, "�����̨����", 0).show();
			//setMsgNotification();
		}
	};
	// ��������֪ͨ����Ϣ��handler
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
	public void onCreate() {// ��onCreate��������ע��㲥������
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
						System.out.println("Getmsgserverice-----�㲥----test");
						sendBroadcast(broadCast);// ���յ�����Ϣ�ѹ㲥����ʽ���ͳ�ȥ
					
				}
			});
		}
		
	}

	@Override
	// �ڷ��񱻴ݻ�ʱ����һЩ����
	public void onDestroy() {
		super.onDestroy();
		unregisterReceiver(backKeyReceiver);
		util = new SharePreferenceUtil(getApplicationContext(),
				Constants.SAVE_USER);
		mNotificationManager.cancel(Constants.NOTIFY_ID);
		// ������������������Ϣ
		if (isStart) {
			ClientOutputThread out = client.getClientOutputThread();
			TranObject<User> o = new TranObject<User>(TranObjectType.LOGOUT);
			User u = new User();
			u.setId(Integer.parseInt(util.getId()));
			o.setObject(u);
			out.setMsg(o);
			// ������֮�󣬹ر�client
			out.setStart(false);
			client.getClientInputThread().setStart(false);
		}
		
	}

	/**
	 * ����֪ͨ
	 */
	private void setMsgNotification() {
		/*
		int icon = R.drawable.ic_launcher;
		CharSequence tickerText = "";
		long when = System.currentTimeMillis();
		mNotification = new Notification(icon, tickerText, when);

		// ������"��������"��Ŀ��
		mNotification.flags = Notification.FLAG_ONGOING_EVENT;

		RemoteViews contentView = new RemoteViews(mContext.getPackageName(),
				R.layout.notify);
		contentView.setTextViewText(R.id.notify_name, util.getName());
		contentView.setTextViewText(R.id.notify_msg, "�ֻ��������ں�̨����");
		contentView.setTextViewText(R.id.notify_time, MyDate.getDate());
		// ָ�����Ի���ͼ
		mNotification.contentView = contentView;
		Intent intent = new Intent(this, MainActivity.class);
		PendingIntent contentIntent = PendingIntent.getActivity(mContext, 0,
				intent, PendingIntent.FLAG_UPDATE_CURRENT);
		// ָ��������ͼ
		mNotification.contentIntent = contentIntent;
		mNotificationManager.notify(Constants.NOTIFY_ID, mNotification);
	*/
	}
	
	
}
