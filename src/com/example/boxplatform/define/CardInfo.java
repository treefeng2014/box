package com.example.boxplatform.define;

import java.util.ArrayList;

public class CardInfo {
	public int ID;      //证卡编号。
	public String CardNO;   //投件人的证卡号码
	public String UnitName;   //所属单位名称
	public String UserName;   //所属用户名称
	public int CompanyID; //公司对应的ID
	public int RoldId;//1：管理员，显示设置按钮。2：普通投件人，不显示设置按钮。
	public CardInfo()
    {
        ID = 0;
        CardNO = "";
		UnitName = "";
		UserName = "";
		CompanyID = 0;
}
	
    
   
}
