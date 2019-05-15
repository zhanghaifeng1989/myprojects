package com.example.administrator.mytest.aidltest;

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
import android.widget.Toast;

import com.example.administrator.mytest.DoubleService.Service1;
import com.example.administrator.mytest.DoubleService.ServiceUtil;
import com.example.administrator.mytest.DoubleService.StrongService;
import com.example.administrator.mytest.R;

/**
 * Created by Administrator on 2019/1/21.
 */

public class AIDLService2 extends Service {
    public int anHour; //记录间隔时间
    AlarmManager manager;//控制定时
    PendingIntent pi;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
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
            Intent i = new Intent(getBaseContext(), Service1.class);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                getBaseContext().startForegroundService(i);
            } else {
                getBaseContext().startService(i);
            }


        }
    };

    /**
     * 在内存紧张的时候，系统回收内存时，会回调OnTrimMemory， 重写onTrimMemory当系统清理内存时从新启动Service1
     */
    @Override
    public void onTrimMemory(int level) {

        startService1();
    }

    @Override
    public void onCreate() {

        Toast.makeText(AIDLService2.this, "Service2 启动中...", Toast.LENGTH_SHORT)
                .show();
        startService1();
		/*
		 * 此线程用监听Service2的状态
		 */
        new Thread() {
            public void run() {
                while (true) {
                    boolean isRun = ServiceUtil.isServiceWork(AIDLService2.this,
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
        boolean isRun = ServiceUtil.isServiceWork(AIDLService2.this,
                "com.example.administrator.mytest.DoubleService.Service1");
        if (isRun == false) {
            try {
//                startS1.startService();
                Intent i = new Intent(getBaseContext(), Service1.class);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    getBaseContext().startForegroundService(i);
                } else {
                    getBaseContext().startService(i);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager notificationManager = (NotificationManager) AIDLService2.this.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannelGroup(new NotificationChannelGroup("a", "a"));

            NotificationChannel channel = new NotificationChannel("1",
                    "Channel1", NotificationManager.IMPORTANCE_DEFAULT);
            channel.enableLights(true);
            channel.setLightColor(Color.GREEN);
            channel.setShowBadge(true);
            notificationManager.createNotificationChannel(channel);
            int notificationId = 0x1234;
            Notification.Builder builder = new Notification.Builder(AIDLService2.this, "1");

            builder.setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setContentText("后台备份正在运行");
            startForeground(notificationId, builder.build());
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return (IBinder) startS1;
    }

//    @Nullable
//    @Override
//    public IBinder onBind(Intent intent) {
//        return null;
//    }
}
