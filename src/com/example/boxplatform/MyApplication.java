package com.example.boxplatform;

import android.app.Application;
import android.util.Log;

import com.example.boxplatform.define.Constants;
import com.example.boxplatform.define.SharePreferenceUtil;
import com.example.boxplatform.network.Client;

public class MyApplication extends Application {
	private SocketTcpClient Client2Box;// 连接工作箱
	private SocketTcpClient Client2InnerServer;// 连接内部Server
	private Client Client2OuterServer;//连接外部Server
	private String strBoxIpAddress;
	private String strInnerIpAddress;
	private String strOuterIpAddress;
	private int iBoxPort;
	private int iInnerPort;
	private int iOuterPort;
	private boolean bClient2Box;
	private boolean bClient2InnerServer;
	private boolean bClient2OuterServer;
	
	
	@Override
	public void onCreate() {
		Log.i(Constants.Log_tag,"--enter box application--1");
		//strBoxIpAddress = "192.9.100.14";
		SharePreferenceUtil util = new SharePreferenceUtil(this,
				Constants.SAVE_USER);
		
		//strBoxIpAddress = "192.168.1.102";
		iBoxPort = Constants.BOX_PORT;
		strBoxIpAddress = util.getBoxIp();
		//strBoxIpAddress = "192.168.1.102";
		
		//strInnerIpAddress = "192.168.1.102";
		iInnerPort = Constants.INNERSEVER_PORT;
		strInnerIpAddress = util.getInnserServerIp();
		//strInnerIpAddress = "192.168.1.102";
		Log.i(Constants.Log_tag,"app" + "strBoxIpAddress =" + strBoxIpAddress + 
									"strInnerIpAddress = " + strInnerIpAddress );
		strOuterIpAddress = "";
		iOuterPort = Constants.SERVER_PORT;
		ThreadClient2Box ThreadClient2Box = new ThreadClient2Box();
		new Thread(ThreadClient2Box).start();
		ThreadClient2InnerServer ThreadClient2InnerServer = new ThreadClient2InnerServer();
		new Thread(ThreadClient2InnerServer).start();
		Log.i(Constants.Log_tag,"--enter box application--2");
		CrashHandler catchHandler = CrashHandler.getInstance();  
		catchHandler.init(getApplicationContext());

		super.onCreate();
	}

	public SocketTcpClient getClient2Box() {
		return Client2Box;
	}

	public void setClient2Box(SocketTcpClient client2Box) {
		Client2Box = client2Box;
	}

	public SocketTcpClient getClient2InnerServer() {
		return Client2InnerServer;
	}

	public void setClient2InnerServer(SocketTcpClient client2InnerServer) {
		Client2InnerServer = client2InnerServer;
	}

	public Client getClient2OuterServer() {
		return Client2OuterServer;
	}

	public void setClient2OuterServer(Client client2OuterServer) {
		Client2OuterServer = client2OuterServer;
	}

	public boolean isbClient2Box() {
		return bClient2Box;
	}
	
	public void setbClient2Box(boolean bClient2Box) {
		this.bClient2Box = bClient2Box;
	}

	public boolean isbClient2InnerServer() {
		return bClient2InnerServer;
	}

	public void setbClient2InnerServer(boolean bClient2InnerServer) {
		this.bClient2InnerServer = bClient2InnerServer;
	}

	public boolean isbClient2OuterServer() {
		return bClient2OuterServer;
	}

	public void setbClient2OuterServer(boolean bClient2OuterServer) {
		this.bClient2OuterServer = bClient2OuterServer;
	}

	public class ThreadClient2Box implements Runnable {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				//Client2Box = new SocketTcpClient("10.0.2.2", 4001);// 从配置文件中读ip和地址
				Client2Box = new SocketTcpClient(strBoxIpAddress, Constants.BOX_PORT);
				Log.i(Constants.Log_tag,"--enter box application--connect boxip = " + strBoxIpAddress);
				setbClient2Box(Client2Box.isConnect());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	public class ThreadClient2InnerServer implements Runnable {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				//Client2InnerServer = new SocketTcpClient("10.0.2.2", 4002);// 从配置文件中读ip和地址
				Client2InnerServer = new SocketTcpClient(strInnerIpAddress, Constants.INNERSEVER_PORT);
				Log.i(Constants.Log_tag,"--enter box application--connect innserver ip =" + strInnerIpAddress);
				setbClient2InnerServer(Client2InnerServer.isConnect());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	public class ThreadClient2OuterServer implements Runnable {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				Client2OuterServer = new Client("10.0.2.2", 8080);// 从配置文件中读ip和地址
				bClient2OuterServer = Client2OuterServer.start();
				setbClient2OuterServer(bClient2OuterServer);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	@Override
	public void onTerminate() {
		// TODO Auto-generated method stub
		super.onTerminate();
		
	}
	
}
