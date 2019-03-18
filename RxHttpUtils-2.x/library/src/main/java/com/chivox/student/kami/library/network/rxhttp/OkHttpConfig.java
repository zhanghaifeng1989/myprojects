package com.chivox.student.kami.library.network.rxhttp;

import com.chivox.student.kami.library.network.interceptor.HeaderInterceptor;
import com.chivox.student.kami.library.network.interceptor.RxHttpLogger;
import com.chivox.student.kami.library.network.interfaces.BuildHeadersListener;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * <pre>
 *      desc    : 统一OkHttp配置信息
 * </pre>
 */
public class OkHttpConfig {
    private static final long defaultTimeout = 10;
    private static OkHttpClient.Builder okHttpClientBuilder;
    private static OkHttpClient okHttpClient;

    private BuildHeadersListener buildHeadersListener;


    public OkHttpConfig() {
        okHttpClientBuilder = new OkHttpClient.Builder();
    }





    public void setDebug(boolean isDebug) {
        if (isDebug) {
            HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor(new RxHttpLogger());
            logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            okHttpClientBuilder.addInterceptor(logInterceptor);
        }
    }

    /**
     * 配置headers
     */
    public  void setHeadersConfig(HeaderInterceptor headerInterceptor) {
            okHttpClientBuilder.addInterceptor(headerInterceptor);
    }

    public void  setAddInterceptor(Interceptor... interceptors) {
            if (null != interceptors) {
                for (Interceptor interceptor : interceptors) {
                    okHttpClientBuilder.addInterceptor(interceptor);
                }
            }
    }


    /**
     * 配置超时信息
     */
    public void setTimeout(long readTimeout,long writeTimeout,long connectTimeout) {
        okHttpClientBuilder.readTimeout(readTimeout == 0 ? defaultTimeout : readTimeout, TimeUnit.SECONDS);
        okHttpClientBuilder.writeTimeout(writeTimeout == 0 ? defaultTimeout : writeTimeout, TimeUnit.SECONDS);
        okHttpClientBuilder.connectTimeout(connectTimeout == 0 ? defaultTimeout : connectTimeout, TimeUnit.SECONDS);
        okHttpClientBuilder.retryOnConnectionFailure(true);
    }

    public  OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    public OkHttpClient build(){
        okHttpClient = okHttpClientBuilder.build();
        return okHttpClient;
    }
}

