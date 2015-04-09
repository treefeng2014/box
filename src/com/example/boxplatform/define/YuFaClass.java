package com.example.boxplatform.define;

import java.util.ArrayList;

public class YuFaClass {
	public String YFBizoZhi; //预发标识。Y开头的为计划的信息，如：Y00000001, W开头的为非计划的信息（直投或普投）如：W00000001
    public String Barcode;  //条码编号（快递等物品上的条码内容）
    public String ReceiveUserPassword;//取件人取件时候需要输入的密码

    public boolean isDelete;	//是否删除

    public boolean IsTouxiang; //投箱状态，是否已经投箱
	public int BoxNo;		//物品放入的箱号，未投入前为0，投箱时候投箱人指定箱格投入。
    public String SendTime; //投箱时间 格式：2013-04-05 17:38:27
	public String SendUserCardId; //投箱人证卡编号

    public boolean IsTuiJian;    //是否超时没人取走，退回了。
    public String TuiJianTime;//投件人取回退件的时间 格式：2013-04-05 17:38:27
	public String TJUserCardId; //取回退件物品的投件人证卡编号

    public boolean   IsQuJian; //取件状态，是否已经取件
    public String ReceiveTime;  //取件时间 格式：2013-04-05 17:38:27

	public boolean IsYanHuo; //取件人是否验货了
	public String YanHuoTime;  //取件人验货时间 格式：2013-04-05 17:38:27

	public boolean IsJuShou; //取件人是否拒收了
	public String JuShouTime;  //拒收时间 格式：2013-04-05 17:38:27

	public boolean IsTuiHui; //投件人是否已经取出拒收物品
	public String TuiHuiTime;  //投件人取出拒收物品时间 格式：2013-04-05 17:38:27
	public String THUserCardId; //取回拒收物品的投件人证卡编号
	public boolean IsKanWu; //勘误取件标识字段
	public String KanWuTime;  //投件人取出勘误物品时间格式：-04-05 17:38:27
	public String KWUserCardId; //取回勘误物品的投件人证卡编号

	public int CompanyID; //公司对应的ID
	public boolean IsFuFei;//用户是否已经付费，如果没有付费提示取件人先付费。
	public YuFaClass()
    {
    }
    
}
