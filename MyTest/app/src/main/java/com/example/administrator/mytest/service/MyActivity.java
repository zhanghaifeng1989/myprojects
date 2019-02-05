package com.example.administrator.mytest.service;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;

import com.example.administrator.mytest.R;

/**
 * Created by zhanghaifeng on 2019/1/12.
 */

public class MyActivity extends Activity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent startIntent = new Intent(this,OneService.class);
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.O) {
        startForegroundService(startIntent);
          } else {
        startService(startIntent);
        }


    }






    @Override
    protected void onDestroy() {
        System.out.println("====ActivityonDestroy");

        super.onDestroy();
    }
}
