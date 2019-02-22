package com.example.administrator.mytest;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2018/12/4.
 * 线程池
 */

public class ThreadPoolActivity extends Activity{
    private String TAG = "ThreadPoolActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ExecutorService executorService = new MyThreadPoolExecutor(new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                Log.i(TAG, "rejectedExecution");

            }
        });

        for (int i = 0; i < 20; i++) {
             int index = i;
            executorService.execute(new PriorityRunable(index));
        }
    }




    public class myrunable {

    }
}
