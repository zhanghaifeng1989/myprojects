package com.allen.library.rxhttp.retrofit;


import retrofit2.CallAdapter;
import retrofit2.Converter;


/**
 * <pre>
 *      @author : Allen
 *      e-mail  : lygttpod@163.com
 *      date    : 2018/12/08
 *      desc    : RetrofitFactory的配置
 *      version : 1.0
 * </pre>
 */
public class RetrofitConfig {

    private CallAdapter.Factory[] callAdapterFactory;

    private Converter.Factory[] converterFactory;

    private RetrofitConfig() {
    }



    /**
     * 添加CallAdapterFactory
     *
     * @param factories CallAdapter.Factory
     */
    public void addCallAdapterFactory(CallAdapter.Factory... factories) {
        this.callAdapterFactory = factories;

    }

    /**
     * 添加ConverterFactory
     *
     * @param factories Converter.Factory
     */
    public void addConverterFactory(Converter.Factory... factories) {
        this.converterFactory = factories;
    }

    /**
     * 获取CallAdapter.Factory
     *
     * @return
     */
    public CallAdapter.Factory[] getCallAdapterFactory() {
        return callAdapterFactory;
    }

    /**
     * 获取Converter.Factory
     *
     * @return
     */
    public Converter.Factory[] getConverterFactory() {
        return converterFactory;
    }

}
