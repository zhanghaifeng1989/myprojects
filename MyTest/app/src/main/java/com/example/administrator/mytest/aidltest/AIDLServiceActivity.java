package com.example.administrator.mytest.aidltest;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.administrator.mytest.DoubleService.Service1;
import com.example.administrator.mytest.ITestAidlInterface;
import com.example.administrator.mytest.R;

/**
 * Created by Administrator on 2019/1/21.
 */

public class AIDLServiceActivity extends Activity{


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("servicetest", "AIDLServiceActivity"+getCurProcessName(AIDLServiceActivity.this));


        ServiceConnection connection  = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                try {
                    ITestAidlInterface binder =   ITestAidlInterface.Stub.asInterface(service);
                    binder.method1();
                }catch (Exception ep){

                }

            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };

        Intent i1 = new Intent(AIDLServiceActivity.this, AIDLService1.class);

        bindService(i1,connection,BIND_AUTO_CREATE);

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
