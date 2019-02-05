package com.example.administrator.mytest.DoubleService;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

import com.example.administrator.mytest.LogcatHelper;
import com.example.administrator.mytest.R;

/**
 * Created by Administrator on 2019/1/21.
 */

public class Service1 extends Service {

    public int anHour; //记录间隔时间
    AlarmManager manager;//控制定时
    PendingIntent pi;


    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 1:
                    startService2();
                    break;

                default:
                    break;
            }

        };
    };

    /**
     * 使用aidl 启动Service2
     */
    private StrongService startS2 = new StrongService.Stub() {
        @Override
        public void stopService() throws RemoteException {
            Intent i = new Intent(getBaseContext(), Service2.class);
            getBaseContext().stopService(i);
        }

        @Override
        public void startService() throws RemoteException {
            Log.d("servicetest","service1--startService2");

            Intent i = new Intent(getBaseContext(), Service2.class);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                getBaseContext().startForegroundService(i);
            } else {
                getBaseContext().startService(i);
            }

        }
    };
    /**
     * 在内存紧张的时候，系统回收内存时，会回调OnTrimMemory， 重写onTrimMemory当系统清理内存时从新启动Service2
     */
    @Override
    public void onTrimMemory(int level) {
        Log.d("servicetest", "service1--onTrimMemory");

		/*
		 * 启动service2
		 */
        startService2();

    }
    @Override
    public void onCreate() {
        Log.d("servicetest", "service1--onCreate");
        Toast.makeText(Service1.this, "Service1 正在启动...", Toast.LENGTH_SHORT)
                .show();
        startService2();
		/*
		 * 此线程用监听Service2的状态
		 */
        new Thread() {
            public void run() {
                while (true) {
                    boolean isRun = ServiceUtil.isServiceWork(Service1.this,
                            "com.example.administrator.mytest.DoubleService.Service2");
                    if (!isRun) {
                        Message msg = Message.obtain();
                        msg.what = 1;
                        handler.sendMessage(msg);
                    }
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            };
        }.start();
    }
    /**
     * 判断Service2是否还在运行，如果不是则启动Service2
     */
    private void startService2() {
        boolean isRun = ServiceUtil.isServiceWork(Service1.this,
                "com.example.administrator.mytest.DoubleService.Service2");
        if (isRun == false) {
            try {
                startS2.startService();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("servicetest", "service1--onStartCommand");

        Toast.makeText(Service1.this, "Service1----onStartCommand", Toast.LENGTH_SHORT)
                .show();


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager notificationManager = (NotificationManager) Service1.this.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannelGroup(new NotificationChannelGroup("a", "a"));

            NotificationChannel channel = new NotificationChannel("1",
                    "Channel1", NotificationManager.IMPORTANCE_DEFAULT);
            channel.enableLights(true);
            channel.setLightColor(Color.GREEN);
            channel.setShowBadge(true);
            notificationManager.createNotificationChannel(channel);
            int notificationId = 0x1234;
            Notification.Builder builder = new Notification.Builder(Service1.this, "1");

            builder.setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setContentText("后台备份正在运行");
            startForeground(notificationId, builder.build());
        }



        /****************用AlarmManager实现20秒一次循环启动service**************/
        manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        anHour =   20 * 1000;
        long triggerAtTime = SystemClock.elapsedRealtime() + (anHour);
        Intent i = new Intent(this, AlarmReceiver.class);//AlarmReceiver每20秒会接受到系统广播
        pi = PendingIntent.getBroadcast(this, 0, i, 0);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pi);
        /********************************************************************/


        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.d("servicetest", "service1--onDestroy");
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return (IBinder) startS2;
    }

}
