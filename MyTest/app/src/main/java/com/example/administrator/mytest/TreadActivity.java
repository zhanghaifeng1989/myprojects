package com.example.administrator.mytest;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Administrator on 2018/12/3.
 */

public class TreadActivity extends Activity {
    private String TAG = "TreadActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**********************************FixedThreadPool****************************************************/
        ExecutorService executor1 = Executors.newFixedThreadPool(3);
        /**********************************CachedThreadPool****************************************************/
//        ExecutorService executor1 = Executors.newCachedThreadPool();
        /**********************************newSingleThreadExecutor****************************************************/
//        ExecutorService executor1 = Executors.newSingleThreadExecutor();
        for(int i = 0 ; i < 10 ; i++){
            final int index = i;
            executor1.execute(new Runnable() {
                @Override
                public void run() {
                    Log.i(TAG, "ExecutorActivity run: 任务 = " + index + "，线程 = " + Thread.currentThread().getName());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
//        test1();
    }

/********************************************Thread********************************************
    private  int count = 1000;
    private boolean isRunning = false;
    Lock lock = new ReentrantLock();
    private void test1() {
        isRunning=true;
        SyncThread syncThread1 = new SyncThread("线程一");
        SyncThread syncThread2 = new SyncThread("线程二");
        SyncThread syncThread3 = new SyncThread("线程三");

        syncThread1.start();
        syncThread2.start();
        syncThread3.start();

    }


      //继承Thread方式
    private class SyncThread extends Thread {

        SyncThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            while (isRunning) {
                count();
            }
        }
    }
    private void count() {
        lock.lock();

        if (count > 0) {
                Log.e(TAG, Thread.currentThread().getName() + "--->" + count--);
            } else {
                isRunning = false;
            }
        lock.unlock();
    }
*/
}
