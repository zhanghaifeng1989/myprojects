package com.example.administrator.mytest.DoubleService;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

import com.example.administrator.mytest.LogcatHelper;

/**
 * Created by Administrator on 2019/1/21.
 */

public class Service2 extends Service {
    public int anHour; //记录间隔时间
    AlarmManager manager;//控制定时
    PendingIntent pi;
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 1:
                    startService1();
                    break;

                default:
                    break;
            }

        };
    };

    /**
     * 使用aidl 启动Service1
     */
    private StrongService startS1 = new StrongService.Stub() {

        @Override
        public void stopService() throws RemoteException {
            Intent i = new Intent(getBaseContext(), Service1.class);
            getBaseContext().stopService(i);
        }

        @Override
        public void startService() throws RemoteException {
            Log.d(LogcatHelper.TAG, "service2--startService1");

            Intent i = new Intent(getBaseContext(), Service1.class);
            getBaseContext().startService(i);

        }
    };

    /**
     * 在内存紧张的时候，系统回收内存时，会回调OnTrimMemory， 重写onTrimMemory当系统清理内存时从新启动Service1
     */
    @Override
    public void onTrimMemory(int level) {
        Log.d(LogcatHelper.TAG, "service2--onTrimMemory");

        startService1();
    }

    @Override
    public void onCreate() {
//        LogcatHelper.getInstance(this).start();
        Log.d(LogcatHelper.TAG, "service2--onCreate");

        Toast.makeText(Service2.this, "Service2 启动中...", Toast.LENGTH_SHORT)
                .show();
        startService1();
		/*
		 * 此线程用监听Service2的状态
		 */
        new Thread() {
            public void run() {
                while (true) {
                    boolean isRun = ServiceUtil.isServiceWork(Service2.this,
                            "com.example.administrator.mytest.DoubleService.Service1");
                    if (!isRun) {
                        Message msg = Message.obtain();
                        msg.what = 1;
                        handler.sendMessage(msg);
                    }
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
        }.start();
    }

    /**
     * 判断Service1是否还在运行，如果不是则启动Service1
     */
    private void startService1() {
        boolean isRun = ServiceUtil.isServiceWork(Service2.this,
                "com.example.administrator.mytest.DoubleService.Service1");
        if (isRun == false) {
            try {
                startS1.startService();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.d(LogcatHelper.TAG, "service2--onDestroy");

        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return (IBinder) startS1;
    }


}
