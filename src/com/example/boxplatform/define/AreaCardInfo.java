package com.example.boxplatform.define;

import java.util.ArrayList;

import android.util.Log;

public class AreaCardInfo {
	public int AreaID;
	public ArrayList<CardInfo> CardInfoList;
	public String strRecvInfo;
	public String getStrRecvInfo() {
		return strRecvInfo;
	}
	public void setStrRecvInfo(String strRecvInfo) {
		this.strRecvInfo = strRecvInfo;
		CardInfoList = new ArrayList<CardInfo>();
		/*
		 * ����ͷ+��Ϣ����:ID,CardNO,UnitName,UserName;ID,CardNO,UnitName,UserName;����
			�磺100102:1,12345678,��λһ,�û�һ;����

		 * */
    	String strBody = strRecvInfo.substring(6 + 8 + 1);
    	char cSpliter = ';';
    	String[] strInfoArray = strBody.split("" + cSpliter);
		//��Ϊ����ַ��������Ԫ���ֳ��Ĳ�һ�£�������Ҫ��ȥ���һ��Ԫ��
		int iLen = strInfoArray.length - 1;
		System.out.println("strRecvInfo = " + strRecvInfo +
					"strBody = " + strBody + "iLen = " + iLen);
		
		//strRecvInfo.
		for(int i = 0; i < iLen; i++) {
			CardInfo cardInfo = new CardInfo();
			System.out.println("strInfoArray[i] = " + strInfoArray[i]);
			String[] strElements = strInfoArray[i].split("" + ',');
			/*
			System.out.println("strElements[0] = " + strElements[0] + 
					"strElements[1] =" + strElements[1] +"strElements[2]= " + strElements[2] +
					"strElements[3]= " + strElements[3] );
					*/
			cardInfo.ID = Integer.parseInt(strElements[0]) ;
			cardInfo.CardNO = strElements[1];
			cardInfo.UnitName = strElements[2];
			cardInfo.UserName = strElements[3];
			cardInfo.CompanyID = Integer.parseInt(strElements[4]) ;
			cardInfo.RoldId = Integer.parseInt(strElements[5]) ;
			Log.i(Constants.Log_tag," cardInfo.ID =  " + cardInfo.ID
										+ " cardInfo.CardNO = " + cardInfo.CardNO
										+ " cardInfo.UnitName = " + cardInfo.UnitName
										+ " cardInfo.UserName = " + cardInfo.UserName
										+ " cardInfo.CompanyID = " + cardInfo.CompanyID
										+ " cardInfo.RoldId = " + cardInfo.RoldId);
			CardInfoList.add(cardInfo);
		}
	}
	public int getAreaID() {
		return AreaID;
	}
	public void setAreaID(int areaID) {
		AreaID = areaID;
	}
	public ArrayList<CardInfo> getCardInfoList() {
		return CardInfoList;
	}
	public void setCardInfoList(ArrayList<CardInfo> cardInfoList) {
		CardInfoList = cardInfoList;
	}
	
}
