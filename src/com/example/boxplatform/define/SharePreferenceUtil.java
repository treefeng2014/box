package com.example.boxplatform.define;

import android.content.Context;
import android.content.SharedPreferences;

public class SharePreferenceUtil {
	private SharedPreferences sp;
	private SharedPreferences.Editor editor;

	public SharePreferenceUtil(Context context, String file) {
		sp = context.getSharedPreferences(file, context.MODE_PRIVATE);
		editor = sp.edit();
	}


	public void setPasswd(String passwd) {
		editor.putString("passwd", passwd);
		editor.commit();
	}

	public String getPasswd() {
		return sp.getString("passwd", "");
	}


	public void setId(String id) {
		editor.putString("id", id);
		editor.commit();
	}

	public String getId() {
		return sp.getString("id", "");
	}
	public void setAreaId(int id) {
		editor.putInt("id", id);
		editor.commit();
	}

	public int getAreaId() {
		return sp.getInt("id", -1);
	}


	// ip
	public void setBoxIp(String ip) {
		System.out.println("util setBoxIp ip = " + ip);
		editor.putString("ipbox", ip);
		editor.commit();
	}

	public String getBoxIp() {
		return sp.getString("ipbox", "10.0.2.2");
	}

	
	public void setBoxPort(int port) {
		editor.putInt("portbox", port);
		editor.commit();
	}

	public int geBoxPort() {
		return sp.getInt("portbox", 4001);
	}

	public void setInnserServerIp(String ip) {
		System.out.println("util setBoxIp ip = " + ip);
		editor.putString("ipserver", ip);
		editor.commit();
	}

	public String getInnserServerIp() {
		return sp.getString("ipserver", "10.0.2.2");
	}
	
	public void setInnerServerPort(int port) {
		editor.putInt("portserver", port);
		editor.commit();
	}

	public int geInnerServerPort() {
		return sp.getInt("portserver", 4002);
	}
	public void setIsFirst(boolean isFirst) {
		editor.putBoolean("isFirst", isFirst);
		editor.commit();
	}

	public boolean getisFirst() {
		return sp.getBoolean("isFirst", true);
	}
	
}
