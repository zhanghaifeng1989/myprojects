package com.chivox.student.kami.library.network;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import com.chivox.student.kami.library.network.rxhttp.retrofit.RetrofitConfig;
import com.chivox.student.kami.library.network.rxhttp.download.DownloadRetrofit;
import com.chivox.student.kami.library.network.rxhttp.RxHttpClient;
import com.chivox.student.kami.library.network.rxhttp.RxHttpManager;
import com.chivox.student.kami.library.network.rxhttp.upload.UploadRetrofit;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;

/**
 * 网络请求
 */

public class RxHttpUtils {

    @SuppressLint("StaticFieldLeak")
    private static RxHttpUtils instance;
    @SuppressLint("StaticFieldLeak")
    private static Application context;

    public static RxHttpUtils getInstance() {
        if (instance == null) {
            synchronized (RxHttpUtils.class) {
                if (instance == null) {
                    instance = new RxHttpUtils();
                }
            }

        }
        return instance;
    }


    /**
     * 必须在全局Application先调用，获取context上下文，否则缓存无法使用
     *
     * @param app Application
     */
    public RxHttpUtils init(Application app) {
        context = app;
        return this;
    }

    /**
     * 获取全局上下文
     */
    public  static Context getContext() {
        checkInitialize();
        return context;
    }

    /**
     * 检测是否调用初始化方法
     */
    private static void checkInitialize() {
        if (context == null) {
            throw new ExceptionInInitializerError("请先在全局Application中调用 RxHttpUtils.getInstance().init(this) 初始化！");
        }
    }


    public RxHttpClient commonConfig(@NonNull String baseurl, OkHttpClient okHttpClient, RetrofitConfig retrofitConfig) {
        checkInitialize();
        RxHttpClient.getInstance().setBaseUrl(baseurl);
        RxHttpClient.getInstance().setOkClient(okHttpClient);
        RxHttpClient.getInstance().setRetrofitConfig(retrofitConfig);
        return RxHttpClient.getInstance();
    }


    /**
     * 使用全局参数创建请求
     *
     * @param cls Class
     * @param <K> K
     * @return 返回
     */
    public   <K> K createApi(Class<K> cls) {
        return RxHttpClient.getInstance().createGApi(cls);
    }

    
    /**
     * 下载文件
     *
     * @param fileUrl 地址
     * @return ResponseBody
     */
    public static Observable<ResponseBody> downloadFile(String fileUrl) {
        return DownloadRetrofit.downloadFile(fileUrl);
    }

    /**
     * 上传单张图片
     *
     * @param uploadUrl 地址
     * @param filePath  文件路径
     * @return ResponseBody
     */
    public static Observable<ResponseBody> uploadImg(String uploadUrl, String filePath) {
        return UploadRetrofit.uploadImage(uploadUrl, filePath);
    }

    /**
     * 上传多张图片
     *
     * @param uploadUrl 地址
     * @param filePaths 文件路径
     * @return ResponseBody
     */
    public static Observable<ResponseBody> uploadImages(String uploadUrl, List<String> filePaths) {
        return UploadRetrofit.uploadImages(uploadUrl, filePaths);
    }

    /**
     * 上传多张图片
     *
     * @param uploadUrl 地址
     * @param filePaths 文件路径
     * @return ResponseBody
     */
    /**
     * 上传多张图片
     *
     * @param uploadUrl 地址
     * @param fileName  后台接收文件流的参数名
     * @param paramsMap 参数
     * @param filePaths 文件路径
     * @return ResponseBody
     */
    public static Observable<ResponseBody> uploadImagesWithParams(String uploadUrl, String fileName, Map<String, Object> paramsMap, List<String> filePaths) {
        return UploadRetrofit.uploadFilesWithParams(uploadUrl, fileName, paramsMap, filePaths);
    }

    /**
     * 取消所有请求
     */
    public static void cancelAll() {
        RxHttpManager.get().cancelAll();
    }

    /**
     * 取消某个或某些请求
     */
    public static void cancel(Object... tag) {
        RxHttpManager.get().cancel(tag);
    }
}
