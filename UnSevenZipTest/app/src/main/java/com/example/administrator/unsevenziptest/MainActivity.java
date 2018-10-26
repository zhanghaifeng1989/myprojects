package com.example.administrator.unsevenziptest;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.zhanghaifeng.sevenziptest.ZipUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Thread(new Runnable() {
            @Override
            public void run() {

                    String path = Environment.getExternalStorageDirectory().getAbsolutePath();
                    ZipUtils.excuteCommand(getLmUnZipComments(path+"/01c3767f7f3f4fbb9b3189607d036598.model.lm",path));
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
