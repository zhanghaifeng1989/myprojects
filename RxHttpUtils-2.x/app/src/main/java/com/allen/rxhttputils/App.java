package com.allen.rxhttputils;


import android.app.Application;

import com.chivox.student.kami.library.network.RxHttpUtils;
import com.chivox.student.kami.library.network.rxhttp.OkHttpConfig;
import com.allen.rxhttputils.api.BaseUrls;


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
    }


    /**
     * 快速上手，默认配置
     */
    private void initRxHttpUtils() {
        OkHttpConfig okhttpconfig = new OkHttpConfig();
        okhttpconfig.setDebug(true);
        okhttpconfig.build();
        RxHttpUtils
                .getInstance()
                .init(this)
                .commonConfig(BaseUrls.baseurl,okhttpconfig.getOkHttpClient(),null);
    }
}
