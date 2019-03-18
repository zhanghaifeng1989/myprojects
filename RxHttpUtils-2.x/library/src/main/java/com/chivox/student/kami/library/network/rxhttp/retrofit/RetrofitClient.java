package com.chivox.student.kami.library.network.rxhttp.retrofit;

import com.chivox.student.kami.library.network.interceptor.gson.GsonAdapter;
import com.chivox.student.kami.library.network.rxhttp.OkHttpConfig;

import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * RetrofitClient工具类
 */

public class RetrofitClient {


    private Retrofit.Builder mRetrofitBuilder;

    private OkHttpClient okHttpClient;

    private RetrofitConfig mRetrofitConfig;


    public RetrofitClient() {
        mRetrofitBuilder = new Retrofit.Builder();
    }
    public void setBase_url(String url){
        mRetrofitBuilder.baseUrl(url);
    }

    public void setRetrofitConfig(RetrofitConfig retrofitConfig){
        if(mRetrofitConfig!=null) {
            this.mRetrofitConfig = retrofitConfig;
            CallAdapter.Factory[] callAdapterFactories = mRetrofitConfig.getCallAdapterFactory();
            Converter.Factory[] converterFactories = mRetrofitConfig.getConverterFactory();

            if (null != callAdapterFactories && callAdapterFactories.length > 0) {
                for (CallAdapter.Factory factory : callAdapterFactories) {
                    mRetrofitBuilder.addCallAdapterFactory(factory);
                }
            } else {
                mRetrofitBuilder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
            }

            if (null != converterFactories && converterFactories.length > 0) {
                for (Converter.Factory factory : converterFactories) {
                    mRetrofitBuilder.addConverterFactory(factory);
                }
            } else {
                mRetrofitBuilder.addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create(GsonAdapter.buildGson()));
            }
        }else{
            mRetrofitBuilder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
            mRetrofitBuilder.addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(GsonAdapter.buildGson()));

        }
    }
    private void initDefaultOkHttpClient() {
        OkHttpConfig okHttpConfig = new OkHttpConfig();
        okHttpConfig.setTimeout(10,10,10);
        okHttpClient = okHttpConfig.build();
    }

    public void setOkHttpClient(OkHttpClient okhttpclient){
        if(okhttpclient!=null) {
            this.okHttpClient = okhttpclient;
        }

    }

    public Retrofit getRetrofit() {
        if(okHttpClient == null){
            initDefaultOkHttpClient();
        }
        return mRetrofitBuilder.client(okHttpClient).build();
    }

}
