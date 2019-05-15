package com.example.administrator.mytest.DoubleService;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import com.example.administrator.mytest.R;

/**
 * Created by Administrator on 2019/1/21.
 */

public class DoubleServiceActivity extends Activity{


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent i1 = new Intent(DoubleServiceActivity.this, Service1.class);
//        Intent i2 = new Intent(DoubleServiceActivity.this, Service2.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(i1);
//            startForegroundService(i2);
        } else {
            startService(i1);
//            startService(i2);
        }

    }
}
