package com.example.administrator.mytest;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("子线程1");
                mhandler.sendEmptyMessage(1);
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("子线程2");
                mhandler.sendEmptyMessage(2);
            }
        });
        thread1.start();
        thread2.start();

    }

    Handler mhandler  = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if(msg.what == 1){
                System.out.println("Main1");
            } else if(msg.what == 2){
                System.out.println("Main2");
            }
            return false;
        }
    });
}