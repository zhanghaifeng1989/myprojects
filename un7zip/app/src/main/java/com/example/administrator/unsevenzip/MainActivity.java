package com.example.administrator.unsevenzip;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import com.example.administrator.un7zip.R;
import com.example.zhanghaifeng.sevenziptest.ZipUtils;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String path = Environment.getExternalStorageDirectory().getAbsolutePath();
                    ZipUtils.excuteCommand(getLmUnZipComments(path+"/TestAsset.7z",path));
                }catch (Exception ep){
                }
            }
        }).start();
    }

    public   String getLmUnZipComments(String sevenzfilepath,String filepath){
        StringBuilder sbCmd = new StringBuilder("7z ");
        sbCmd.append("x ");    //7z x
        sbCmd.append("'"+sevenzfilepath+"' "); //7z 待解压文件路径
        sbCmd.append("'-o"   + filepath+"' ");  //7z 解压路径
        sbCmd.append("-aoa "); //-aoa Overwrite All existing files without prompt.
        return sbCmd.toString();
    }
}
