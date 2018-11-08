package com.example.administrator.mytest;

import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import net.lingala.zip4j.unzip.Unzip;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import fr.arnaudguyon.xmltojsonlib.XmlToJson;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("子线程1");
                mhandler.sendEmptyMessage(1);
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("子线程2");
                mhandler.sendEmptyMessage(2);
            }
        });
        thread1.start();
        thread2.start();

//        new Okhttputils().Downloadpaper();


//        File file  = new File(Environment.getExternalStorageDirectory()+"/demo");
//       if(file.exists()) {
//           try {
//               new UnzipUtils().unzip(file,Environment.getExternalStorageDirectory()+"/app_demo","C1h2i3v4o5x6");
//
//           }catch (Exception ep){
//ep.getMessage();
//           }
//       }

       // new UnzipUtils().UnPackTaskFile(MainActivity.this,"Chivox2016.DON.Exam",Environment.getExternalStorageDirectory()+"/app_demo.task",Environment.getExternalStorageDirectory()+"");



        try {
            InputStream is = MainActivity.this.getAssets().open("Paper.xml");
            XmlToJson xmlToJson = new XmlToJson.Builder(is, null).build();
            String json = xmlToJson.toString();
            is.close();
        } catch (Exception ep) {
        }
    }

    Handler mhandler  = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if(msg.what == 1){
                System.out.println("Main1");
            } else if(msg.what == 2){
                System.out.println("Main2");
            }
            return false;
        }
    });
}
