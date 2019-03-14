package com.example.administrator.mytest;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;



/**
 * Created by Administrator on 2018/10/29.
 * https://gank.io/post/560e15be2dca930e00da1083#toc_25
 * 在正文开始之前的最后，放上 GitHub 链接和引入依赖的 gradle 代码： Github：
 * https://github.com/ReactiveX/RxJava
 * https://github.com/ReactiveX/RxAndroid
 * 引入依赖：
 * compile 'io.reactivex:rxjava:1.0.14'
 * compile 'io.reactivex:rxandroid:1.0.1'
 */

public class RxJavaTestActivity extends Activity {
    public String tag = "RxJavaTestActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rxjavatestctivity);
         /****************************************RXJava完整流程*********************************************
        //创建观察者
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onNext(String s) {
                Log.d(tag, "Item: " + s);
            }

            @Override
            public void onCompleted() {
                Log.d(tag, "Completed!");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(tag, "Error!");
            }
        };
        // 创建观察者
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onNext(String s) {
                Log.d(tag, "Item: " + s);
            }

            @Override
            public void onCompleted() {
                Log.d(tag, "Completed!");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(tag, "Error!");
            }
        };
        //创建被观察者
        Observable observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello");
                subscriber.onNext("Hi");
                subscriber.onNext("Aloha");
                subscriber.onCompleted();
            }
        });
//        Observable observable = Observable.just("Hello", "Hi", "Aloha");//快捷创建被观察者
//        String[] words = {"Hello", "Hi", "Aloha"};
//        Observable observable = Observable.from(words);//快捷创建被观察者
        //订阅
        observable.subscribe(observer);
         // 或者：
        //observable.subscribe(subscriber);

*/

/***********************************************RxJava-不完整定义的回调**************************************************************
        //创建被观察者
        Observable observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello");
                subscriber.onNext("Hi");
                subscriber.onNext("Aloha");
                subscriber.onCompleted();
            }
        });
        //创建观察者
        Action1<String> onNextAction = new Action1<String>() {
            // onNext()
            @Override
            public void call(String s) {
                Log.d(tag, s);
            }
        };
        Action1<Throwable> onErrorAction = new Action1<Throwable>() {
            // onError()
            @Override
            public void call(Throwable throwable) {
                // Error handling
            }
        };
        Action0 onCompletedAction = new Action0() {
            // onCompleted()
            @Override
            public void call() {
                Log.d(tag, "completed");
            }
        };

        // 自动创建 Subscriber ，并使用 onNextAction 来定义 onNext()
        observable.subscribe(onNextAction);
        // 自动创建 Subscriber ，并使用 onNextAction 和 onErrorAction 来定义 onNext() 和 onError()
        //observable.subscribe(onNextAction, onErrorAction);
        // 自动创建 Subscriber ，并使用 onNextAction、 onErrorAction 和 onCompletedAction 来定义 onNext()、 onError() 和 onCompleted()
        //observable.subscribe(onNextAction, onErrorAction, onCompletedAction);
        *******/


/******************************************************一个简单例子****************************************************
        String[] names = new String[]{"1","2","3","4"};
        Observable.from(names)
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String name) {
                        Log.d(tag, name);
                    }
                });
 **************/


/******************************************************Scheduler简单例子****************************************************
        Observable.just(1, 2, 3, 4)
                .subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程
                .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer number) {
                        Log.d(tag, "number:" + number);
                    }
                });
 **************/

/******************************************************变换****************************************************/
//TODO
//map  变换对象
//flatmap  变换被观察者
//常用于嵌套的异步操作

/*****************************************************scheduler线程切换*******************************************
        Observable.just(1, 2, 3, 4) // IO 线程，由 subscribeOn() 指定
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .map(mapOperator) // 新线程，由 observeOn() 指定
                .observeOn(Schedulers.io())
                .map(mapOperator2) // IO 线程，由 observeOn() 指定
                .observeOn(AndroidSchedulers.mainThread)
                .subscribe(subscriber);  // Android 主线程，由 observeOn() 指定
 *********/

    }


}
