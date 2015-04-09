package com.example.boxplatform.network;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * 
 * @author way
 * 
 */
public class Client {

	private Socket client;
	private ClientThread clientThread;
	private String ip;
	private int port;

	public Client(String ip, int port) {
		this.ip = ip;
		this.ip = "10.0.2.2";
		this.port = port;
		this.port = 8080;
	}

	public boolean start() {
		try {
			client = new Socket();
			// client.connect(new InetSocketAddress(Constants.SERVER_IP,
			// Constants.SERVER_PORT), 3000);
			System.out.println(ip + port + "wangfeng");
			client.connect(new InetSocketAddress(ip, port), 10000);
			if (client.isConnected()) {
				 System.out.println("Connected.....successfully--wangfeng");
				clientThread = new ClientThread(client);
				clientThread.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// 直接通过client得到读线程
	public ClientInputThread getClientInputThread() {
		return clientThread.getIn();
	}

	// 直接通过client得到写线程
	public ClientOutputThread getClientOutputThread() {
		return clientThread.getOut();
	}

	// 直接通过client停止读写消息
	public void setIsStart(boolean isStart) {
		clientThread.getIn().setStart(isStart);
		clientThread.getOut().setStart(isStart);
	}
	
	public class ClientThread extends Thread {

		private ClientInputThread in;
		private ClientOutputThread out;

		public ClientThread(Socket socket) {
			in = new ClientInputThread(socket);
			out = new ClientOutputThread(socket);
		}

		public void run() {
			in.setStart(true);
			out.setStart(true);
			in.start();
			out.start();
		}

		// 得到读消息线程
		public ClientInputThread getIn() {
			return in;
		}

		// 得到写消息线程
		public ClientOutputThread getOut() {
			return out;
		}
	}
}
