package com.example.administrator.mytest.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;
import android.os.Handler;
import android.os.Message;

import com.example.administrator.mytest.MainActivity;
import com.example.administrator.mytest.R;

/**
 * Created by zhanghaifeng on 2019/1/12.
 */

public class OneService extends Service{

    Timer timer;

    Handler handler = new Handler(){
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    Toast.makeText(OneService.this,"service",Toast.LENGTH_SHORT).show();

                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
       throw new UnsupportedOperationException("Not yet implemented");

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
         timer = new Timer();
        timer.schedule(task,0,10000);

        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.O) {
            NotificationManager notificationManager = (NotificationManager) OneService.this.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannelGroup(new NotificationChannelGroup("a", "a"));

            NotificationChannel channel = new NotificationChannel("1",
                    "Channel1", NotificationManager.IMPORTANCE_DEFAULT);
            channel.enableLights(true);
            channel.setLightColor(Color.GREEN);
            channel.setShowBadge(true);
            notificationManager.createNotificationChannel(channel);


            int notificationId = 0x1234;
            Notification.Builder builder = new Notification.Builder(OneService.this, "1");

            builder.setSmallIcon(android.R.drawable.stat_notify_chat)
                    .setContentText("xxx");

            startForeground(notificationId, builder.build());
        }else{
            /**
             *  创建通知栏管理工具
             */

            NotificationManager notificationManager = (NotificationManager) getSystemService
                    (NOTIFICATION_SERVICE);

            /**
             *  实例化通知栏构造器
             */

            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);

            /**
             *  设置Builder
             */
            //设置标题
            mBuilder.setContentTitle("我是标题")
                    //设置内容
                    .setContentText("我是内容")
                    //设置大图标
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                    //设置小图标
                    .setSmallIcon(R.mipmap.ic_launcher_round)
                    //设置通知时间
                    .setWhen(System.currentTimeMillis())
                    //首次进入时显示效果
                    .setTicker("我是测试内容")
                    //设置通知方式，声音，震动，呼吸灯等效果，这里通知方式为声音
                    .setDefaults(Notification.DEFAULT_SOUND);
            //发送通知请求
            notificationManager.notify(10, mBuilder.build());
        }
    return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        System.out.println("====onDestroy");
 timer.cancel();
 stopForeground(true);// 停止前台服务--参数：表示是否移除之前的通知

        super.onDestroy();
    }
    TimerTask task = new TimerTask(){
        public void run() {
            System.out.println("====run");
            Message message = new Message();
            message.what = 1;
            handler.sendMessage(message);
        }

    };


    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void initNotificationChannel(Context context) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.createNotificationChannelGroup(new NotificationChannelGroup("a", "a"));

        NotificationChannel channel = new NotificationChannel("1",
                "Channel1", NotificationManager.IMPORTANCE_DEFAULT);
        channel.enableLights(true);
        channel.setLightColor(Color.GREEN);
        channel.setShowBadge(true);
        notificationManager.createNotificationChannel(channel);

    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void showChannel1Notification(Context context) {
        int notificationId = 0x1234;
        Notification.Builder builder = new Notification.Builder(context,"1");

        builder.setSmallIcon(android.R.drawable.stat_notify_chat)
                .setContentText("xxx");

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(notificationId, builder.build());

    }


}
