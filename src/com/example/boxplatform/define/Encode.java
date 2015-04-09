package com.example.boxplatform.define;


import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Encode {
	
	//����ԭ��ʹ���û���SSID����Ϊ����seed�� �û�������Ϣ�л�Я���Լ���SSID�ţ�������
	//�յ���ʹ�ø�SSID��Ϊseed���н���
	private static String sKey_16 = "01234567jkieosdf";
	public static String decryptMessage(String sKey, String sSrc) throws Exception {
			try {
			//�ж�Key�Ƿ���ȷ
			if (sKey == null) {
			System.out.print("KeyΪ��null");
			return null;
			}
			//�ж�Key�Ƿ�Ϊ16λ
			if (sKey.length() != 16) {
				String strTemp = sKey;
				int length = strTemp.length();
				String subString = sKey_16.substring(0, 16-length);
				sKey = strTemp + subString;
			}
			byte[] raw = sKey.getBytes("ASCII");
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec);
			byte[] encrypted1 = hex2byte(sSrc);
			try {
			byte[] original = cipher.doFinal(encrypted1);
			String originalString = new String(original);
			return originalString;
			} catch (Exception e) {
			System.out.println(e.toString());
			return null;
			}
			} catch (Exception ex) {
			System.out.println(ex.toString());
			return null;
			}
		}
	
	public static String encryptMessage(String sKey, String sSrc) throws Exception {
			if (sKey == null) {
			System.out.print("KeyΪ��null");
			return null;
			}
			//�ж�Key�Ƿ�Ϊ16λ
			if (sKey.length() != 16) {
				String strTemp = sKey;
				int length = strTemp.length();
				String subString = sKey_16.substring(0, 16-length);
				sKey = strTemp + subString;
			}
			byte[] raw = sKey.getBytes("ASCII");
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
			byte[] encrypted = cipher.doFinal(sSrc.getBytes());
			return byte2hex(encrypted).toLowerCase();
		}
	
	public static byte[] decryptPic(String sKey, byte[] b) throws Exception {
		try {
		//�ж�Key�Ƿ���ȷ
		if (sKey == null) {
		System.out.print("KeyΪ��null");
		return null;
		}
		//�ж�Key�Ƿ�Ϊ16λ
		if (sKey.length() != 16) {
			String strTemp = sKey;
			int length = strTemp.length();
			String subString = sKey_16.substring(0, 16-length);
			sKey = strTemp + subString;
		}
		byte[] raw = sKey.getBytes("ASCII");
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, skeySpec);
		
		try {
			byte[] encryptedbytes = cipher.doFinal(b);
			return encryptedbytes;
			} catch (Exception e) {
				System.out.println(e.toString());
				return null;
			}
		} catch (Exception ex) {
			System.out.println(ex.toString());
			return null;
		}
	}

public static byte[] encryptPic(String sKey, byte[] b) throws Exception {
		if (sKey == null) {
		System.out.print("KeyΪ��null");
		return null;
		}
		//�ж�Key�Ƿ�Ϊ16λ
		if (sKey.length() != 16) {
			String strTemp = sKey;
			int length = strTemp.length();
			String subString = sKey_16.substring(0, 16-length);
			sKey = strTemp + subString;
		}
		byte[] raw = sKey.getBytes("ASCII");
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
		byte[] encryptedbytes = cipher.doFinal(b);
		return encryptedbytes;
	}
		public static byte[] hex2byte(String strhex) {
			if (strhex == null) {
			return null;
			}
			int l = strhex.length();
			if (l % 2 == 1) {
			return null;
			}
			byte[] b = new byte[l / 2];
			for (int i = 0; i != l / 2; i++) {
			b[i] = (byte) Integer.parseInt(strhex.substring(i * 2, i * 2 + 2), 16);
			}
			return b;
		}
		public static String byte2hex(byte[] b) {
			String hs = "";
			String stmp = "";
			for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			//System.out.println("byte2hex----"+stmp);
			if (stmp.length() == 1) {
			hs = hs + "0" + stmp;
			} else {
			hs = hs + stmp;
			}
			}
			return hs.toUpperCase();
		}
}
