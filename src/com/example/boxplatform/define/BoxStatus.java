package com.example.boxplatform.define;

public class BoxStatus {
	public int AreaId;//区域编号
    public int BoxNo;//	Int	逻辑箱号。
	public int DoorStatus;//	Int	门状态，0： "Opened"，1："Closed"，2"OpenTimeOut"，4：“门锁关闭超时”,电机故障 = 5
	public boolean ChufaGD;//	Bool	触发光电是否报错。
	public boolean Connected;//	Bool	箱格是否连接。

    public BoxStatus()
    {
        //
        // TODO: 在此处添加构造函数逻辑
        //
    }

}
