package com.example.boxplatform.define;

public class DirectSend {
	public int AreaId;//区域编号
	public int BoxNo;//	Int	逻辑箱号
	public String BarCode; //物品的条码
	public String SendTime;//	投箱的时间格式：-04-05 17:38:27。
	public String SendUserCardId;//	投件人的证卡编号
	public String GetUserPhone;//	接收人的电话号码。
	
	//返回值 YuFaClass
	//同步间隔：每次过程控制数据有变动，会及时调用本接口，如果处理不成功，或者网络断开的情况下，每间隔5秒重新调一次。
}
