package com.example.administrator.mytest;

import android.support.annotation.NonNull;
import android.util.Log;

/**
 * Created by Administrator on 2019/2/20.
 */

public class PriorityRunable implements Runnable,Comparable<PriorityRunable>{
    private String TAG = "PriorityRunable";

    int mindex = 0;
    public PriorityRunable(int index) {
        this.mindex = index;
    }

    @Override
    public void run() {
        Log.i(TAG, "ExecutorActivity run: 任务 = " + mindex + "，线程 = " + Thread.currentThread().getName());

        try {
            Thread.sleep(5000);
        }catch (Exception ep){

        }
    }
    @Override
    public int compareTo(@NonNull PriorityRunable o) {
        if(mindex%2 == 0){
            return -1;
        }else{
            return 1;
        }
    }
}
