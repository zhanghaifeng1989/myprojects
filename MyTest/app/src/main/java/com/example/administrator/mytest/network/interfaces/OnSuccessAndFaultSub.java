package com.example.administrator.mytest.network.interfaces;

import android.util.Log;


import io.reactivex.observers.DisposableObserver;

/**
 * Created by 眼神 on 2018/3/27.
 * 用于在Http请求开始时，自动显示一个ProgressDialog
 * 在Http请求结束是，关闭ProgressDialog
 * 调用者自己对请求数据进行处理   成功时 通过result是否等于1分别回调onSuccess和onFault，默认处理了401错误转登录。
 * 回调结果为String，需要手动序列化
 */

public class OnSuccessAndFaultSub<T> extends DisposableObserver<T>
{



    private OnSuccessAndFaultListener mOnSuccessAndFaultListener;



    /**
     * @param mOnSuccessAndFaultListener 成功回调监听
     */
    public OnSuccessAndFaultSub(OnSuccessAndFaultListener mOnSuccessAndFaultListener) {
        this.mOnSuccessAndFaultListener = mOnSuccessAndFaultListener;
    }














    /**
     * 订阅开始时调用
     * 显示ProgressDialog
     */
    @Override
    public void onStart() {

    }


    /**
     * 完成，隐藏ProgressDialog
     */
    @Override
    public void onComplete() {

    }


    /**
     * 对错误进行统一处理
     * 隐藏ProgressDialog
     */
    @Override
    public void onError(Throwable e) {
        try {
            mOnSuccessAndFaultListener.onFault(e.getMessage());
//            if (e instanceof SocketTimeoutException) {//请求超时
//            } else if (e instanceof ConnectException) {//网络连接超时
//                //                ToastManager.showShortToast("网络连接超时");
//                mOnSuccessAndFaultListener.onFault("网络连接超时");
//            } else if (e instanceof SSLHandshakeException) {//安全证书异常
//                //                ToastManager.showShortToast("安全证书异常");
//                mOnSuccessAndFaultListener.onFault("安全证书异常");
//            } else if (e instanceof HttpException) {//请求的地址不存在
//                int code = ((HttpException) e).code();
//                if (code == 504) {
//                    //                    ToastManager.showShortToast("网络异常，请检查您的网络状态");
//                    mOnSuccessAndFaultListener.onFault("网络异常，请检查您的网络状态");
//                } else if (code == 404) {
//                    //                    ToastManager.showShortToast("请求的地址不存在");
//                    mOnSuccessAndFaultListener.onFault("请求的地址不存在");
//                } else {
//                    //                    ToastManager.showShortToast("请求失败");
//                    mOnSuccessAndFaultListener.onFault("请求失败");
//                }
//            } else if (e instanceof UnknownHostException) {//域名解析失败
//                //                ToastManager.showShortToast("域名解析失败");
//                mOnSuccessAndFaultListener.onFault("域名解析失败");
//            } else {
//                //                ToastManager.showShortToast("error:" + e.getMessage());
//                mOnSuccessAndFaultListener.onFault("error:" + e.getMessage());
//            }
        } catch (Exception e2) {
            e2.printStackTrace();
        } finally {
            Log.e("OnSuccessAndFaultSub", "error:" + e.getMessage());
            //            mOnSuccessAndFaultListener.onFault("error:" + e.getMessage());


        }

    }


    /**
     * 当result等于1回调给调用者，否则自动显示错误信息，若错误信息为401跳转登录页面。
     * ResponseBody  body = response.body();//获取响应体
     * InputStream inputStream = body.byteStream();//获取输入流
     * byte[] bytes = body.bytes();//获取字节数组
     * String str = body.string();//获取字符串数据
     */
    @Override
    public void onNext(T body) {
        try {
            mOnSuccessAndFaultListener.onSuccess(body);

//            final String result = CompressUtils.decompress(body.byteStream());
//            Log.e("body", result);
//            JSONObject jsonObject = new JSONObject(result);
//            int resultCode = jsonObject.getInt("ErrorCode");
//            if (resultCode == 1) {
//                mOnSuccessAndFaultListener.onSuccess(result);
//            } else {
//                String errorMsg = jsonObject.getString("ErrorMessage");
//                mOnSuccessAndFaultListener.onFault(errorMsg);
//                Log.e("OnSuccessAndFaultSub", "errorMsg: " + errorMsg);
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
