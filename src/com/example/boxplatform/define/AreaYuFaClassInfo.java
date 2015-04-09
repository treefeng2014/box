package com.example.boxplatform.define;

import java.util.ArrayList;

import android.util.Log;

public class AreaYuFaClassInfo {
	public int AreaID;
	public ArrayList<YuFaClass> YuFaClassList;
	public String strRecvInfo;
	public int getAreaID() {
		return AreaID;
	}
	public void setAreaID(int areaID) {
		AreaID = areaID;
	}
	public ArrayList<YuFaClass> getYuFaClassList() {
		return YuFaClassList;
	}
	public void setYuFaClassList(ArrayList<YuFaClass> yuFaClassList) {
		YuFaClassList = yuFaClassList;
	}
	public String getStrRecvAreaInfo() {
		return strRecvInfo;
	}
	public void setStrRecvAreaInfo(String strRecvInfo) {
		this.strRecvInfo = strRecvInfo;
		
		YuFaClassList = new ArrayList<YuFaClass>();
		/*
		 * 消息格式：
	发送头+消息类型:YFBizoZhi,Barcode,ReceiveUserPassword,isDelete,IsTouxiang...;……
	如：100103:1,12345678,02546,False,False....;……

		 * */
		
		String strBody = strRecvInfo.substring(6 + 8 + 1);
    	char cSpliter = ';';
    	String[] strInfoArray = strBody.split("" + cSpliter);
		
		int iLen = strInfoArray.length - 1;
		System.out.println( "---AreaYuFaClassInfo--11111111-----iLen = " + iLen);
		System.out.println("strRecvInfo = " + strRecvInfo +
					"strBody = " + strBody + "iLen = " + iLen);
		
		for(int i = 0; i < iLen; i++) {
			YuFaClass YuFaClassInfo = new YuFaClass();
			//java split API认为如果后续的字符串都是空字符串，获得的结果只包含有值得部分
			strInfoArray[i] = strInfoArray[i] + "" + ',' +";" + ',';
			System.out.println("strInfoArray[i] = " + strInfoArray[i]);
			String[] strElements = strInfoArray[i].split("" + ',');
			
			/*
			System.out.println("strInfoArray[i] length = " + strElements.length);
			for(int j = 0; j < strElements.length; j++) {
				System.out.println("strElements[j] = " + strElements[j] + "j = " + j);
			}
			
			System.out.println("strElements[4] = " + strElements[4] + 
					"strElements[1] =" + strElements[1] +"strElements[2]= " + strElements[2] +
					"strElements[3]= " + strElements[3] );
					*/
			YuFaClassInfo.YFBizoZhi =strElements[0] ;
			YuFaClassInfo.Barcode = strElements[1];
			YuFaClassInfo.ReceiveUserPassword = strElements[2];
			if(	strElements[3].contentEquals("False") ) {
				YuFaClassInfo.isDelete = false;
			} else {
				YuFaClassInfo.isDelete = true;
			}
			if(	strElements[4].contentEquals("False")) {
				YuFaClassInfo.IsTouxiang = false;
			} else {
				YuFaClassInfo.IsTouxiang = true;
			}
			
			YuFaClassInfo.BoxNo = Integer.parseInt(strElements[5]);
			
			
			
			YuFaClassInfo.SendTime = strElements[6];
			YuFaClassInfo.SendUserCardId = strElements[7];
			
			if(	strElements[8].contentEquals("False") ) {
				YuFaClassInfo.IsTuiJian = false;
			} else {
				YuFaClassInfo.IsTuiJian = true;
			}
			YuFaClassInfo.TuiJianTime = strElements[9];
			YuFaClassInfo.TJUserCardId = strElements[10];
			if(	strElements[11].contentEquals("False")) {
				YuFaClassInfo.IsQuJian = false;
			} else  {
				YuFaClassInfo.IsQuJian = true;
			}
			YuFaClassInfo.ReceiveTime = strElements[12];
			if(	strElements[13].contentEquals("False")) {
				YuFaClassInfo.IsYanHuo = false;
			} else {
				YuFaClassInfo.IsYanHuo = true;
			}
			YuFaClassInfo.YanHuoTime = strElements[14];
			if(	strElements[15].contentEquals("False")) {
				YuFaClassInfo.IsJuShou = false;
			} else  {
				YuFaClassInfo.IsJuShou = true;
			}
			YuFaClassInfo.JuShouTime = strElements[16];
			if(	strElements[17].contentEquals("False")) {
				YuFaClassInfo.IsTuiHui = false;
			} else  {
				YuFaClassInfo.IsTuiHui = true;
			}
			
				YuFaClassInfo.TuiHuiTime = strElements[18];
				YuFaClassInfo.THUserCardId = strElements[19];
				
				if(	strElements[20].contentEquals("False")) {
					YuFaClassInfo.IsKanWu = false;
				} else  {
					YuFaClassInfo.IsKanWu = true;
				}
				
				YuFaClassInfo.KanWuTime = strElements[21];
				YuFaClassInfo.KWUserCardId = strElements[22];
				YuFaClassInfo.CompanyID = Integer.parseInt(strElements[23]);
				if(	strElements[24].contentEquals("False")) {
					YuFaClassInfo.IsFuFei =  false;
				} else  {
					YuFaClassInfo.IsFuFei =  true;
				}
				
				Log.i(Constants.Log_tag," YuFaClassInfo.YFBizoZhi=  " + YuFaClassInfo.YFBizoZhi
										+ " YuFaClassInfo.Barcode = " + YuFaClassInfo.Barcode
										+ " YuFaClassInfo.ReceiveUserPassword = " + YuFaClassInfo.ReceiveUserPassword
										+ " YuFaClassInfo.isDelete = " + YuFaClassInfo.isDelete
										+ " YuFaClassInfo.IsTouxiang = " + YuFaClassInfo.IsTouxiang
										+ " YuFaClassInfo.BoxNo = " + YuFaClassInfo.BoxNo
										+ " YuFaClassInfo.CompanyID = " + YuFaClassInfo.CompanyID);
			YuFaClassList.add(YuFaClassInfo);
		}
	}
}
