package com.example.administrator.mytest.TouchEventTest;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.example.administrator.mytest.R;

/**
 * Created by Administrator on 2019/3/7.
 */

public class TouchEventActivity extends Activity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toucheventlayout);
//        LinearLayout ll = (LinearLayout)findViewById(R.id.ll_content);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }
}
