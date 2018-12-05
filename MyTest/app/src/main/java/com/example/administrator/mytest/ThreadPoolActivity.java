package com.example.administrator.mytest;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

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
            final int index = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    Log.i(TAG, "ExecutorActivity run: 任务 = " + index + "，线程 = " + Thread.currentThread().getName());

                   try {
                       Thread.sleep(5000);
                   }catch (Exception ep){

                   }
                }
            });
        }
    }
}
