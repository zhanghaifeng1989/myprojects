package com.allen.rxhttputils;


import android.app.Application;

import com.allen.library.RxHttpUtils;
import com.allen.library.interfaces.BuildHeadersListener;
import com.allen.library.rxhttp.OkHttpConfig;
import com.allen.rxhttputils.api.BaseUrls;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;


/**
 * Created by allen on 2016/12/21.
 * <p>
 *
 * @author Allen
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        initRxHttpUtils();
       // initCustomRxHttpUtils();

    }


    /**
     * 快速上手，默认配置
     */
    private void initRxHttpUtils() {
        RxHttpUtils
                .getInstance()
                .init(this)
                .commonConfig(BaseUrls.baseurl,null,null);
    }
}
