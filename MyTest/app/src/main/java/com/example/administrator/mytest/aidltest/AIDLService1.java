package com.example.administrator.mytest.aidltest;

import android.app.ActivityManager;
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

import com.example.administrator.mytest.DoubleService.AlarmReceiver;
import com.example.administrator.mytest.DoubleService.Service2;
import com.example.administrator.mytest.DoubleService.ServiceUtil;
import com.example.administrator.mytest.DoubleService.StrongService;
import com.example.administrator.mytest.ITestAidlInterface;
import com.example.administrator.mytest.R;

/**
 * Created by Administrator on 2019/1/21.
 */

public class AIDLService1 extends Service {


    private ITestAidlInterface binder = new ITestAidlInterface.Stub() {
        @Override
        public void method1() throws RemoteException {
            Log.d("servicetest", "method1"+getCurProcessName(AIDLService1.this));

        }

        @Override
        public void method2(int x) throws RemoteException {
            Log.d("servicetest", "method2"+getCurProcessName(AIDLService1.this));

        }
    };

    @Override
    public void onCreate() {
        Log.d("servicetest", "service1--onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("servicetest", "service1--onStartCommand::::::"+getCurProcessName(AIDLService1.this));
//        try {
//            binder.method1();
//
//        }catch (Exception ep){
//
//        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.d("servicetest", "service1--onDestroy");
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return (IBinder) binder;
    }

    private String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager mActivityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager
                .getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }

}

