package com.example.administrator.mytest.DoubleService;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

/**
 * Created by Administrator on 2019/1/12.
 */

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i1 = new Intent(context, Service1.class);
        context.startService(i1);

        Intent i2 = new Intent(context, Service2.class);
        context.startService(i2);
    }
}
