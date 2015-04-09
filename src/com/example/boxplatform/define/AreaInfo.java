package com.example.boxplatform.define;

import java.util.ArrayList;

import android.util.Log;

public class AreaInfo {
	public int AreaID;
	public ArrayList<BoxInfo> BoxList;
	public String strRecvAreaInfo;
	public String getStrAreaInfo() {
		return strRecvAreaInfo;
	}
	public void setStrAreaInfo(String strRecvAreaInfo) {
		this.strRecvAreaInfo = strRecvAreaInfo;
		BoxList = new ArrayList<BoxInfo>();
		/*
		 Զ�ⷢ��һ��BoxInfo�Ĳ���Ϊ��bn_m0 = 01��bn_m1=01, bn_m2 = 0; index = 07; itype = 1;
		��Ϣͷ��ͬ���յ�����Ϣ����Ϣͷ���磺��1001��
		��Ϣ��ʾ��01
		������BN+index+type���磺00101071
		��Ϣ��ʽ��10010100101071
		������Ͷ��BoxInfo���밴�����·��ͣ�100101001010710010108100101032
*/
		//operation
		String strBody = strRecvAreaInfo.substring(6 + 8 + 1);
		char cSpliter = ';';
    	String[] strInfoArray = strBody.split("" + cSpliter);
		
		int iLen = strInfoArray.length - 1;
		System.out.println("areainfo process" + "strRecvInfo = " + strRecvAreaInfo +
				"strBody = " + strBody + "iLen = " + iLen);
		
		for(int i = 0; i < iLen; i++) {
			BoxInfo boxInfo = new BoxInfo();
			strInfoArray[i] = strInfoArray[i] + "" + ',' +";" + ',';
			System.out.println("strInfoArray[i] = " + strInfoArray[i]);
			String[] strElements = strInfoArray[i].split("" + ',');
			/*
			System.out.println("strElements length = " + strElements.length);
			for(int j = 0; j < strElements.length; j++) {
				System.out.println("strElements[j] = " + strElements[j] + "j = " + j);
			}
		*/
			boxInfo.boxno =  Integer.parseInt(strElements[0]);
			boxInfo.bn_m0 = Integer.parseInt(strElements[1]);
			boxInfo.bn_m1 = Integer.parseInt(strElements[2]);
			boxInfo.bn_m2 = Integer.parseInt(strElements[3]);
			boxInfo.index = Integer.parseInt(strElements[4]);
			boxInfo.iType = Integer.parseInt(strElements[5]);
			Log.i(Constants.Log_tag,"boxInfo.boxno = " + boxInfo.boxno
										+ "\n\rboxInfo.bn_m0 =  " + boxInfo.bn_m0
										+ "\n\rboxInfo.bn_m1 = " + boxInfo.bn_m1
										+ "\n\rboxInfo.bn_m2 = " + boxInfo.bn_m2
										+ "\n\rboxInfo.index = " + boxInfo.index
										+ "\n\rboxInfo.iType = " +boxInfo.iType);
			BoxList.add(boxInfo);
			
		}
		
	}
	public ArrayList<BoxInfo> getBoxList() {
		return BoxList;
	}
	public void setBoxList(ArrayList<BoxInfo> boxList) {
		BoxList = boxList;
	}
	public int getAreaID() {
		return AreaID;
	}
	public void setAreaID(int areaID) {
		AreaID = areaID;
	}
	
	
	
}
