package com.example.administrator.mytest.DoubleService;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

/**
 * Created by Administrator on 2019/1/21.
 */

public class DoubleServiceActivity extends Activity{


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i1 = new Intent(DoubleServiceActivity.this, Service1.class);
        startService(i1);

        Intent i2 = new Intent(DoubleServiceActivity.this, Service2.class);
        startService(i2);
    }
}
