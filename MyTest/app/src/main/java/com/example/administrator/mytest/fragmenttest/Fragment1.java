package com.example.administrator.mytest.fragmenttest;

import android.animation.Animator;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;

import com.example.administrator.mytest.R;

/**
 * Created by Administrator on 2019/2/22.
 */

public class Fragment1 extends Fragment{

    public String TAG = "MyFragment1";
    public static Fragment1 frag;
    public MyFragmentActivity myFragmentActivity;
    public static Fragment1 newInstance() {
        if(frag == null) {
             frag = new Fragment1();
        }
        return frag;
    }






    @Override
    public void onAttach(Context context) {
        Log.i(TAG, "onAttach");
       if(context instanceof MyFragmentActivity){
           myFragmentActivity = (MyFragmentActivity)context;
       }
        super.onAttach(context);
    }





    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreate");

        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView");

        View root = inflater.inflate(R.layout.fragment1layout, container, false);
        Button btn = (Button)root.findViewById(R.id.btn1);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                myFragmentActivity.getStrFromFragment("Fragment1");
            }
        });
        return root;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onViewCreated");
        super.onViewCreated(view, savedInstanceState);
    }
    @Override
    public void onStart() {
        Log.i(TAG, "onStart");

        super.onStart();
    }

    @Override
    public void onResume() {
        Log.i(TAG, "onResume");
        super.onResume();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        Log.i(TAG, "onSaveInstanceState");

        super.onSaveInstanceState(outState);
    }

    @Override
    public void onPause() {
        Log.i(TAG, "onPause");

        super.onPause();
    }

    @Override
    public void onStop() {
        Log.i(TAG, "onStop");

        super.onStop();
    }

    @Override
    public void onDestroyView() {
        Log.i(TAG, "onDestroyView");

        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy");

        super.onDestroy();
    }

    @Override
    public void onDetach() {
        Log.i(TAG, "onDetach");

        super.onDetach();
    }








}
