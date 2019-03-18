package com.chivox.student.kami.library.network.rxhttp.retrofit;


import retrofit2.CallAdapter;
import retrofit2.Converter;


/**
 * <pre>
 *      desc    : RetrofitFactory的配置
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
