package com.chivox.student.kami.library.network.rxhttp;

import com.chivox.student.kami.library.network.rxhttp.retrofit.RetrofitConfig;
import com.chivox.student.kami.library.network.rxhttp.retrofit.RetrofitClient;

import java.util.HashMap;

import okhttp3.OkHttpClient;

/**
 * 网络请求工具类---使用的是全局配置的变量
 */

public class RxHttpClient {

    private static RxHttpClient instance;

    /**
     * 缓存retrofit针对同一个ApiService不会重复创建retrofit对象
     */
    private static HashMap<String, Object> retrofitServiceCache;

    private RetrofitClient mRetrofitClient;

    public RxHttpClient() {
        retrofitServiceCache = new HashMap<>();
        mRetrofitClient = new RetrofitClient();
    }

    public static RxHttpClient getInstance() {

        if (instance == null) {
            synchronized (RxHttpClient.class) {
                if (instance == null) {
                    instance = new RxHttpClient();
                }
            }

        }
        return instance;
    }



    /**
     * 设置baseUrl
     *
     * @param baseUrl
     * @return
     */
    public RxHttpClient setBaseUrl(String baseUrl) {
        mRetrofitClient.setBase_url(baseUrl);
        return this;
    }


    /**
     * 设置自己的client
     *
     * @param okClient
     * @return
     */
    public RxHttpClient setOkClient(OkHttpClient okClient) {
        mRetrofitClient.setOkHttpClient(okClient);
        return this;
    }
    /**
     * 设置自己的retrofitConfig
     *
     * @param retrofitConfig
     * @return
     */
    public RxHttpClient setRetrofitConfig(RetrofitConfig retrofitConfig) {
        mRetrofitClient.setRetrofitConfig(retrofitConfig);
        return this;
    }

    /**
     * 使用全局变量的请求
     *
     * @param cls
     * @param <K>
     * @return
     */
    public  <K> K createGApi(final Class<K> cls) {
        if (retrofitServiceCache == null) {
            retrofitServiceCache = new HashMap<>();
        }
        K retrofitService = (K) retrofitServiceCache.get(cls.getCanonicalName());
        if (retrofitService == null) {
            retrofitService = mRetrofitClient.getRetrofit().create(cls);
            retrofitServiceCache.put(cls.getCanonicalName(), retrofitService);
        }
        return retrofitService;
    }


}
