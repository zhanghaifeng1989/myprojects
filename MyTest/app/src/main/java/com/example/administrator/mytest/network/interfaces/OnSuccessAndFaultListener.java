package com.example.administrator.mytest.network.interfaces;

/**
 * Created by 眼神 on 2018/3/27.
 */
public interface OnSuccessAndFaultListener<T> {
    void onSuccess(T result);

    void onFault(String errorMsg);
}
