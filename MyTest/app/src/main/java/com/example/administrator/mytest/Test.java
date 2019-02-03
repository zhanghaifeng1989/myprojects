package com.example.administrator.mytest;

/**
 * Created by Administrator on 2019/2/1.
 */

public class Test {
    public   void main(){
        Test1 t = new Test1(){
            @Override
            public void test() {

            }
        };
        t.test();
        MThread th = new MThread();
        th.start();

        Thread th2 = new Thread(){
            @Override
            public void run() {
                super.run();
            }
        };
        th2.start();
        String xx = "";
        xx.hashCode();

    }


    class MThread extends  Thread{
        @Override
        public void run() {
            super.run();
        }
    }

}
