package com.example.javatest;

import java.util.HashMap;

/**
 * Created by Administrator on 2019/2/3.
 */

public class MyTest {
    public static void main(String[] args) {
        Sample mSample1 =  new Sample(2);
        Sample mSample2 = new Sample(2);
        Integer mSample3 =  mSample1.getSample();
        Integer mSample4 = mSample2.getSample();
        System.out.println(mSample1==mSample2);
        System.out.println(mSample3==mSample4);



//        System.out.println("mSample3==="+System.identityHashCode(mSample3));
//        Singleton1 s1 =  Singleton1.getInstance();
//        Singleton1 s2 =  Singleton1.getInstance();
//        System.out.println(s1==s2);


    }

}

class Sample {
    int  s1 =  1;
//    static Sample mSample1 = new Sample();

    public Sample(int x ) {
//         s1 = new Integer(2);
        s1 = x;
        System.out.println(System.identityHashCode(s1));
//        System.out.println("Sample()"+System.identityHashCode(s1));
//        System.out.println("s1==="+System.identityHashCode(s1));
//        System.out.println("mSample1==="+System.identityHashCode(mSample1));

    }
//    public static Sample getSample() {
//        return mSample1;
//    }
        public Integer  getSample() {
        return s1;
    }
}


// 饿汉式单例
class Singleton1 {
    int xx = 0;
    // 私有构造
    Singleton1() {
        System.out.println("xx==="+System.identityHashCode(xx));

    }

    private static Singleton1 single = new Singleton1();
    //
    // 静态工厂方法
    public static Singleton1 getInstance() {
        return single;
    }
}




