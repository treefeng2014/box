package com.example.boxplatform.define;

import java.util.ArrayList;

public class YuFaClass {
	public String YFBizoZhi; //Ԥ����ʶ��Y��ͷ��Ϊ�ƻ�����Ϣ���磺Y00000001, W��ͷ��Ϊ�Ǽƻ�����Ϣ��ֱͶ����Ͷ���磺W00000001
    public String Barcode;  //�����ţ���ݵ���Ʒ�ϵ��������ݣ�
    public String ReceiveUserPassword;//ȡ����ȡ��ʱ����Ҫ���������

    public boolean isDelete;	//�Ƿ�ɾ��

    public boolean IsTouxiang; //Ͷ��״̬���Ƿ��Ѿ�Ͷ��
	public int BoxNo;		//��Ʒ�������ţ�δͶ��ǰΪ0��Ͷ��ʱ��Ͷ����ָ�����Ͷ�롣
    public String SendTime; //Ͷ��ʱ�� ��ʽ��2013-04-05 17:38:27
	public String SendUserCardId; //Ͷ����֤�����

    public boolean IsTuiJian;    //�Ƿ�ʱû��ȡ�ߣ��˻��ˡ�
    public String TuiJianTime;//Ͷ����ȡ���˼���ʱ�� ��ʽ��2013-04-05 17:38:27
	public String TJUserCardId; //ȡ���˼���Ʒ��Ͷ����֤�����

    public boolean   IsQuJian; //ȡ��״̬���Ƿ��Ѿ�ȡ��
    public String ReceiveTime;  //ȡ��ʱ�� ��ʽ��2013-04-05 17:38:27

	public boolean IsYanHuo; //ȡ�����Ƿ������
	public String YanHuoTime;  //ȡ�������ʱ�� ��ʽ��2013-04-05 17:38:27

	public boolean IsJuShou; //ȡ�����Ƿ������
	public String JuShouTime;  //����ʱ�� ��ʽ��2013-04-05 17:38:27

	public boolean IsTuiHui; //Ͷ�����Ƿ��Ѿ�ȡ��������Ʒ
	public String TuiHuiTime;  //Ͷ����ȡ��������Ʒʱ�� ��ʽ��2013-04-05 17:38:27
	public String THUserCardId; //ȡ�ؾ�����Ʒ��Ͷ����֤�����
	public boolean IsKanWu; //����ȡ����ʶ�ֶ�
	public String KanWuTime;  //Ͷ����ȡ��������Ʒʱ���ʽ��-04-05 17:38:27
	public String KWUserCardId; //ȡ�ؿ�����Ʒ��Ͷ����֤�����

	public int CompanyID; //��˾��Ӧ��ID
	public boolean IsFuFei;//�û��Ƿ��Ѿ����ѣ����û�и�����ʾȡ�����ȸ��ѡ�
	public YuFaClass()
    {
    }
    
}
