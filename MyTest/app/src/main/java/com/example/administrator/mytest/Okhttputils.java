package com.example.administrator.mytest;

import android.util.Log;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.Authenticator;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Credentials;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.Route;

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

                try {
                    JSONObject jsonObject = new JSONObject(s);
                    String token = jsonObject.getString("access_token");
//                    getTasklist(token);
                    getTaskByTaskName(token);
                }catch (Exception ep){

                }



            }
        });
    }
    //获取试卷列表
    public void getTasklist( String token){

        OkHttpClient client = new OkHttpClient();
        //构造Request对象
        //采用建造者模式，链式调用指明进行Get请求,传入Get的请求地址
        Request request = new Request.Builder().get().url("http://10.0.200.49:8081/api/task/list").addHeader("Authorization", "Bearer "+token).build();
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
    public void getTaskByTaskName( String token){
//        {
//            "TaskName": null,
//                "StartTime": "0001-01-01T00:00:00",
//                "EndTime": "0001-01-01T00:00:00",
//                "ClassID": null,
//                "TaskTypeList": null,
//                "LastUpdateTime": "2018-11-12T13:39:40.816658",
//                "IgnoreDeleteFlag": true,
//                "PostParams": {
//            "page": 0,
//                    "count": 50,
//                    "sfilter": null,
//                    "filter": null,
//                    "sorting": null
//        }
//        }


//        {
//            "TaskName": "string",
//                "StartTime": "2018-11-12T03:44:47.351Z",
//                "EndTime": "2018-11-12T03:44:47.351Z",
//                "ClassID": "string",
//                "TeacherID": "string",
//                "StatusList": [
//            0
//  ],
//            "TaskTypeList": [
//            0
//  ],
//            "LastUpdateTime": "2018-11-12T03:44:47.351Z",
//                "PostParams": {
//            "page": 0,
//                    "count": 0,
//                    "sfilter": "string",
//                    "filter": {},
//            "sorting": {}
//        },
//            "IgnoreDeleteFlag": true
//        }
//
//        {
//            "TaskName": "string",
//                "StartTime": "2018-11-12T03:44:47.351Z",
//                "EndTime": "2018-11-12T03:44:47.351Z",
//                "ClassID": "string",
//                "TeacherID": "string",
//                "StatusList": [
//            0
//	],
//            "TaskTypeList": [
//            0
//	],
//            "LastUpdateTime": "2018-11-12T03:44:47.351Z",
//                "PostParams": {
//            "page": 0,
//                    "count": 0,
//                    "sfilter": "string",
//                    "filter": {},
//            "sorting": {}
//        },
//            "IgnoreDeleteFlag": true
//        }

//        {'TaskName': 'string', 'StartTime': '2018-11-12T03:44:47.351Z', 'EndTime': '2018-11-12T03:44:47.351Z', 'ClassID': 'string', 'TeacherID': 'string', 'StatusList': [0],'TaskTypeList': [0],'LastUpdateTime': '2018-11-12T03:44:47.351Z', 'PostParams': {'page': 0, 'count': 0, 'sfilter': 'string', 'filter': {},'sorting': {}},'IgnoreDeleteFlag': true}
        String poststr = " {'TaskName': '18110801', 'StartTime': '2018-10-12 00:00:00', 'EndTime': '2018-11-12 23:59:59', 'ClassID': '', 'StatusList': [],'TaskTypeList': [], 'PostParams': {'page': 0, 'count': 50, 'sfilter': '', 'filter': {},'sorting': {}}}";
        try {
            JSONObject jsonObject = new JSONObject(poststr);
//            jsonObject.put("TaskName","18110801");
//            jsonObject.put("StartTime","0001-01-01T00:00:00");
//            jsonObject.put("EndTime","0001-01-01T00:00:00");
//            jsonObject.put("ClassID","");
//            jsonObject.put("TaskTypeList","");
//            jsonObject.put("LastUpdateTime","2018-11-12T13:39:40.816658");
//            jsonObject.put("IgnoreDeleteFlag",true);
//            JSONObject obj = new JSONObject();
//            obj.put("page",0);
//            obj.put("count",50);
//            obj.put("sfilter","");
//            obj.put("filter","");
//            obj.put("sorting","");
//            jsonObject.put("PostParams",obj);
            poststr = jsonObject.toString();
        }catch (Exception ep){

        }
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8")
                , poststr);
        Request request = new Request.Builder()
                .url("http://10.0.200.49:8081/api/task/list/detailbypage")//请求的url
                .post(requestBody)
                .addHeader("Authorization", "Bearer "+token)
                .build();
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



    public void getTest(){
        OkHttpClient client = new OkHttpClient();
        //构造Request对象
        //采用建造者模式，链式调用指明进行Get请求,传入Get的请求地址
        Request request = new Request.Builder().get().url("http://47.105.169.181:3001/cars").build();
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

    public void postTest(){

        //"device":"","lon":"","lat":"","time":"","address":""}

        String poststr = "{'device':'android','lon':'30.0','lat':'120.9','time':111111111,'address':'黑色'}";
        try {
            JSONObject jsonObject = new JSONObject(poststr);
            poststr = jsonObject.toString();
        }catch (Exception ep){

        }


        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8")
                , poststr);
        Request request = new Request.Builder()
                .url("http://47.105.169.181:3001/locserv/add")//请求的url
                .post(requestBody)
                .build();
        Call call = client.newCall(request);
        //异步调用并设置回调函数
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.getMessage();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String responseStr = response.body().string();
            }
        });
    }





}
