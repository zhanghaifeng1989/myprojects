package com.example.administrator.mytest;

import java.util.Comparator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Handler;

/**
 * Created by Administrator on 2018/12/4.
 */

public class MyThreadPoolExecutor extends ThreadPoolExecutor{
    public static final int CORE_POOL_SIZE = 2;//核心线程池大小
    public static final int MAXIMUM_POOL_SIZE = 2;//最大线程池队列大小
    public static final int KEEP_ALIVE = 1;//保持存活时间，当线程数大于corePoolSize的空闲线程能保持的最大时间。
    public static final AtomicLong SEQ_SEED = new AtomicLong(0);//主要获取添加任务
    public MyThreadPoolExecutor(RejectedExecutionHandler handler) {
        super(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE,  TimeUnit.SECONDS, new PriorityBlockingQueue<Runnable>(), sThreadFactory,handler);
    }



    /**
     * 创建线程工厂
     */
    public static final ThreadFactory sThreadFactory = new ThreadFactory() {
        private final AtomicInteger mCount = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable runnable) {

            return new Thread(runnable, "test#" + mCount.getAndIncrement());
        }


    };

    @Override
    public void execute(Runnable command) {
        super.execute(command);
    }


}
