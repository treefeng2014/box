package com.example.boxplatform;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.example.boxplatform.define.Constants;
    
/**  
 * UncaughtException������,��������Uncaught�쳣��ʱ��,�и������ӹܳ���,����¼���ʹ��󱨸�. 
 * 
 *  ��Ҫ��Application��ע�ᣬΪ��Ҫ�ڳ����������ͼ����������
 */    
public class CrashHandler implements UncaughtExceptionHandler {    
	private MyApplication application;
    public static final String TAG = "CrashHandler";    
        
    //ϵͳĬ�ϵ�UncaughtException������     
    private Thread.UncaughtExceptionHandler mDefaultHandler;    
    //CrashHandlerʵ��    
    private static CrashHandler instance;
   //�����Context����    
    private Context mContext;    
    //�����洢�豸��Ϣ���쳣��Ϣ    
    private Map<String, String> infos = new HashMap<String, String>();    
    
    //���ڸ�ʽ������,��Ϊ��־�ļ�����һ����    
    private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");    
    
    /** ��ֻ֤��һ��CrashHandlerʵ�� */    
    private CrashHandler() {}    
    
    /** ��ȡCrashHandlerʵ�� ,����ģʽ */    
    public static CrashHandler getInstance() {    
    	if(instance == null)
    		instance = new CrashHandler();   
        return instance;    
    }    
    
    /**  
     * ��ʼ��  
     */    
    public void init(Context context) {    
        mContext = context;    
        application = (MyApplication)mContext;
        //��ȡϵͳĬ�ϵ�UncaughtException������    
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();    
        //���ø�CrashHandlerΪ�����Ĭ�ϴ�����    
        Thread.setDefaultUncaughtExceptionHandler(this);    
    }    
    
    /**  
     * ��UncaughtException����ʱ��ת��ú���������  
     */    
    @Override    
    public void uncaughtException(Thread thread, Throwable ex) {    
        if (!handleException(ex) && mDefaultHandler != null) {    
            //����û�û�д�������ϵͳĬ�ϵ��쳣������������    
            mDefaultHandler.uncaughtException(thread, ex);    
        } else {    
            try {    
                Thread.sleep(3000);    
            } catch (InterruptedException e) {    
                Log.e(TAG, "error : ", e);    
            }    
            Intent intent = new Intent(application.getApplicationContext(),StartActivity.class);  
            PendingIntent restartIntent = PendingIntent.getActivity(    
            application.getApplicationContext(), 0, intent,    
            Intent.FLAG_ACTIVITY_NEW_TASK);                                                 
                                                     
            AlarmManager mgr = (AlarmManager)application.getSystemService(Context.ALARM_SERVICE);    
            mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 1000,    
             restartIntent); // 1���Ӻ�����Ӧ��   
            //�˳�����    
            android.os.Process.killProcess(android.os.Process.myPid());    
            System.exit(1);    
        }    
    }    
    
    /**  
     * �Զ��������,�ռ�������Ϣ ���ʹ��󱨸�Ȳ������ڴ����.  
     *   
     * @param ex  
     * @return true:��������˸��쳣��Ϣ;���򷵻�false.  
     */    
    private boolean handleException(Throwable ex) {    
        if (ex == null) {    
            return false;    
        }    
        //�ռ��豸������Ϣ     
        //collectDeviceInfo(mContext);    
        
        //ʹ��Toast����ʾ�쳣��Ϣ    
        new Thread() {    
            @Override    
            public void run() {    
                Looper.prepare();    
                Toast.makeText(mContext, "�ܱ�Ǹ,��������쳣,��������.", Toast.LENGTH_SHORT).show();    
                Looper.loop();    
            }    
        }.start();    
        
        //�����쳣����Ҫ��������
        StartActivity.instance.SaveMsg2Innser();
        //������־�ļ�     
        
        sendCrashInfo2InnerServer(ex);
       
        return true;    
    }    
        
    /**  
     * �ռ��豸������Ϣ  
     * @param ctx  
     */    
    public void collectDeviceInfo(Context ctx) {    
        try {    
            PackageManager pm = ctx.getPackageManager();    
            PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(), PackageManager.GET_ACTIVITIES);    
            if (pi != null) {    
                String versionName = pi.versionName == null ? "null" : pi.versionName;    
                String versionCode = pi.versionCode + "";    
                infos.put("versionName", versionName);    
                infos.put("versionCode", versionCode);    
            }    
        } catch (NameNotFoundException e) {    
            Log.e(TAG, "an error occured when collect package info", e);    
        }    
        Field[] fields = Build.class.getDeclaredFields();    
        for (Field field : fields) {    
            try {    
                field.setAccessible(true);    
                infos.put(field.getName(), field.get(null).toString());    
                Log.d(TAG, field.getName() + " : " + field.get(null));    
            } catch (Exception e) {    
                Log.e(TAG, "an error occured when collect crash info", e);    
            }    
        }    
    }    
    private String sendCrashInfo2InnerServer(Throwable ex) {    
        
        StringBuffer sb = new StringBuffer();    
        for (Map.Entry<String, String> entry : infos.entrySet()) {    
            String key = entry.getKey();    
            String value = entry.getValue();    
            sb.append(key + "=" + value + "\n");    
        }    
            
        Writer writer = new StringWriter();    
        PrintWriter printWriter = new PrintWriter(writer);    
        ex.printStackTrace(printWriter);    
        Throwable cause = ex.getCause();    
        while (cause != null) {    
            cause.printStackTrace(printWriter);    
            cause = cause.getCause();    
        }    
        printWriter.close();    
        String result = writer.toString();    
        sb.append(result);  
        String strSend =Constants.ANDROID_PLATFORM 
				 + String.format("%02d", Constants.ANDROID_PLATFORM_UNEXCETPTION) 
				 + ':' + StartActivity.instance.iAreaId+ ',' + result;
        System.out.println("unexception---send info = -" + strSend);
        SocketTcpClient Client2InnerServer = application.getClient2InnerServer();
        if(Client2InnerServer.isConnect()) {
        	Client2InnerServer.SendData(strSend);
        } 
        return null;    
    }    
    /**  
     * ���������Ϣ���ļ���  
     *   
     * @param ex  
     * @return  �����ļ�����,���ڽ��ļ����͵�������  
     */    
    private String saveCatchInfo2File(Throwable ex) {    
            
        StringBuffer sb = new StringBuffer();    
        for (Map.Entry<String, String> entry : infos.entrySet()) {    
            String key = entry.getKey();    
            String value = entry.getValue();    
            sb.append(key + "=" + value + "\n");    
        }    
            
        Writer writer = new StringWriter();    
        PrintWriter printWriter = new PrintWriter(writer);    
        ex.printStackTrace(printWriter);    
        Throwable cause = ex.getCause();    
        while (cause != null) {    
            cause.printStackTrace(printWriter);    
            cause = cause.getCause();    
        }    
        printWriter.close();    
        String result = writer.toString();    
        sb.append(result);  
        String strSend =Constants.ANDROID_PLATFORM 
				 + String.format("%02d", Constants.ANDROID_PLATFORM_UNEXCETPTION) 
				 + ':' + result;
        System.out.println("unexception---send info = -" + strSend);
        SocketTcpClient Client2InnerServer = application.getClient2InnerServer();
        if(Client2InnerServer.isConnect()) {
        	//Client2InnerServer.SendData(strSend);
        }
        /*
        Intent sendIntent = new Intent(Intent.ACTION_SENDTO); 
        sendIntent.setData(Uri.parse("mailto:bigboy_1984@163.com"));//�����ʼ��쳣��csdn@csdn.com���� 
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, "��׿����ƽ̨bug report");//�ʼ����� 
        sendIntent.putExtra(Intent.EXTRA_TEXT, result);//��ջ��Ϣ 
        StartActivity.instance.startActivity(sendIntent);*/
       /*
        try {    
            long timestamp = System.currentTimeMillis();    
            String time = formatter.format(new Date());    
            String fileName = "crash-" + time + "-" + timestamp + ".log";    
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {    
                String path = "/mnt/sdcard/crash/";    
                File dir = new File(path);    
                if (!dir.exists()) {    
                    dir.mkdirs();    
                }    
                FileOutputStream fos = new FileOutputStream(path + fileName);    
                fos.write(sb.toString().getBytes());  
                //���͸�������Ա
                sendCrashLog2PM(path+fileName);
                fos.close();    
                System.out.println("path = " + path+fileName);
            }    
            return fileName;    
        } catch (Exception e) {    
            Log.e(TAG, "an error occured while writing file...", e);    
        }    
        */
        return null;    
    }    
    
    /**
     * ������ĵ��±����Ĵ�����Ϣ���͸�������Ա
     * 
     * Ŀǰֻ��log��־������sdcard �������LogCat�У���δ���͸���̨��
     */
    private void sendCrashLog2PM(String fileName){
    	if(!new File(fileName).exists()){
    		Toast.makeText(mContext, "��־�ļ������ڣ�", Toast.LENGTH_SHORT).show();
    		return;
    	}
    	FileInputStream fis = null;
    	BufferedReader reader = null;
    	String s = null;
    	String sInfo = "";
    	try {
			fis = new FileInputStream(fileName);
			reader = new BufferedReader(new InputStreamReader(fis, "GBK"));
			while(true){
				s = reader.readLine();
				if(s == null) break;
				//����Ŀǰ��δȷ���Ժ��ַ�ʽ���ͣ������ȴ��log��־��
				Log.i("info", s.toString());
				sInfo += s;
				//System.out.println("sendCrashLog2PM =  " + s);
			}
			/*
			Intent sendIntent = new Intent(Intent.ACTION_SENDTO); 
            sendIntent.setData(Uri.parse("mailto:bigboy_1984@163.com"));//�����ʼ��쳣��csdn@csdn.com���� 
            sendIntent.putExtra(Intent.EXTRA_SUBJECT, "��׿����ƽ̨bug report");//�ʼ����� 
            sendIntent.putExtra(Intent.EXTRA_TEXT, sInfo);//��ջ��Ϣ 
            StartActivity.instance.startActivity(sendIntent);
            */
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{	// �ر���
			try {
				reader.close();
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
    }
}    
