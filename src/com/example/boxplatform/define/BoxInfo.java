package com.example.boxplatform.define;

public class BoxInfo {
	public  int boxno;     //定义的箱号
	 public int bn_m0;	    //BN号的m0位，0-99,分箱号
     public int bn_m1;		//BN号的m1位，0-99,主箱号
     public int bn_m2;      //BN号的m2位，0-9,主箱号高位扩展
     public int  index;
     public int iType; // 小箱为1，中箱为2，大箱为3
     public int iOccupy;//1表示占用，0表示为未占用
}
