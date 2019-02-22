package com.example.administrator.mytest.fragmenttest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.administrator.mytest.R;

/**
 * Created by Administrator on 2019/2/22.
 */

public class MyFragmentActivity extends FragmentActivity{
    public String TAG = "MyFragmentActivity";

    private Button btn_f1,btn_f2;
    private FrameLayout container;

    @Override
    protected void onDestroy() {
        Log.i(TAG, "onDestroy");

        super.onDestroy();
    }

    @Override
    protected void onPause() {
        Log.i(TAG, "onPause");

        super.onPause();
    }



    @Override
    protected void onResume() {
        Log.i(TAG, "onResume");

        super.onResume();
    }



    @Override
    protected void onStart() {
        Log.i(TAG, "onStart");

        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.i(TAG, "onStop");

        super.onStop();
    }



    @Override
    protected void onRestart() {
        Log.i(TAG, "onRestart");

        super.onRestart();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreate");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragmentactivitylayout);
        container = (FrameLayout)findViewById(R.id.container);
        btn_f1 = (Button)findViewById(R.id.btn1);
        btn_f2 = (Button)findViewById(R.id.btn2);

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, Fragment1.newInstance(), "f1")
                     .addToBackStack("f1")
                    .commit();
        }

        btn_f1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
//                getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.container,Fragment1.newInstance(),"f1")
//                        .commit();
                getSupportFragmentManager().popBackStack("f2", FragmentManager.POP_BACK_STACK_INCLUSIVE);

            }
        });
        btn_f2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, Fragment2.newInstance(),"f2")
                         .addToBackStack("f2")
                        .commit();
//                getSupportFragmentManager().beginTransaction().attach(Fragment1.newInstance()).commit();
            }
        });


    }
    public void getStrFromFragment(String str){
btn_f1.setText(str);
    }
}
