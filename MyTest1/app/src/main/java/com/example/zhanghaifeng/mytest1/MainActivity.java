package com.example.zhanghaifeng.mytest1;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = (Button)findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
jump1();
            }
        });
    }
    public void jump1(){
        Intent intent = new Intent(Intent.ACTION_MAIN);
/**知道要跳转应用的包命与目标Activity*/
        ComponentName componentName = new ComponentName("com.example.zhanghaifeng.mytest2", "com.example.zhanghaifeng.mytest2.MainActivity");
        intent.setComponent(componentName);
        intent.putExtra("", "");//这里Intent传值
        startActivity(intent);
    }

    public void jump2(){
        Intent intent = getPackageManager().getLaunchIntentForPackage("com.example.zhanghaifeng.mytest2");
        if (intent != null) {
            intent.putExtra("type", "110");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }


    public void jump3(){
        Intent intent = new Intent();
        intent.setData(Uri.parse("csd://com.example.zhanghaifeng.mytest2/main?type=110"));
        intent.putExtra("", "");//这里Intent当然也可传递参数,但是一般情况下都会放到上面的URL中进行传递
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
