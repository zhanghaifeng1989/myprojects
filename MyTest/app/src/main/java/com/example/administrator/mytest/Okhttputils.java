package com.example.administrator.mytest;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2018/11/5.
 */

public class Okhttputils {
    //登录
    public void kaolalogin(){
        OkHttpClient okHttpClient = new OkHttpClient();

        RequestBody requestBody = new FormBody.Builder()
                .add("grant_type", "password")
                .add("username", "08@08.com$kaola")
                .add("password", "123")
                .build()
                ;
        Request request = new Request.Builder()
                .url("http://10.0.200.49:8081/token")
                .post(requestBody)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("MainActivity", "onFailure: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ResponseBody body = response.body();
                InputStream inputStream = body.byteStream();//获取输入流
                byte[] bytes = body.bytes();//获取字节数组
                String s = new String(bytes);
                s.length();
                getTasklist();
            }
        });
    }
    //获取试卷列表
    public void getTasklist(){
        OkHttpClient client = new OkHttpClient();
        //构造Request对象
        //采用建造者模式，链式调用指明进行Get请求,传入Get的请求地址
        Request request = new Request.Builder().get().url("http://10.0.200.49:8081/api/task/list").build();
        Call call = client.newCall(request);
        //异步调用并设置回调函数
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String responseStr = response.body().string();
            }
        });


    }

    //获取试卷列表
    public void Downloadpaper(){
        OkHttpClient client = new OkHttpClient();
        //构造Request对象
        //采用建造者模式，链式调用指明进行Get请求,传入Get的请求地址
        Request request = new Request.Builder().get().url("http://10.0.200.49:8081//api/task/fa0c65e25e7647ce9a0d86c75e72a50d/download").build();
        Call call = client.newCall(request);
        //异步调用并设置回调函数
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String responseStr = response.body().string();
            }
        });
    }

}
