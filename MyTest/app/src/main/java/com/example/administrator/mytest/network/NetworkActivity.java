package com.example.administrator.mytest.network;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.administrator.mytest.network.entity.DoubanMovieBean;
import com.example.administrator.mytest.network.interfaces.OnSuccessAndFaultListener;
import com.example.administrator.mytest.network.interfaces.OnSuccessAndFaultSub;
import com.example.administrator.mytest.network.movie.MoveService;
import com.example.administrator.mytest.network.movie.MovieLoader;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Administrator on 2019/3/13.
 */

public class NetworkActivity extends Activity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getMovieList();
//        Rxjavanetwork();

//        /************************同步请求*********************************/
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    final String BASE_URL = "https://api.douban.com/v2/movie/";
//                    Retrofit retrofit = new Retrofit.Builder()
//                            .baseUrl(BASE_URL)
//                            .addConverterFactory(GsonConverterFactory.create())
//                            .build();
//                    //获取接口实例
//                    MoveService movieService = retrofit.create(MoveService.class);
//                    //调用方法得到一个Call
//                    Call<DoubanMovieBean> call = movieService.getTop250(0,2);
//                    Response<DoubanMovieBean> response = call.execute();
//                    DoubanMovieBean move  =  response.body();
//                }catch (Exception ep){
//                    ep.getMessage();
//                }
//            }
//        }).start();
//        network1();
Rxjavanetwork();
    }
    public void network1(){
        final String BASE_URL = "https://api.douban.com/v2/movie/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        /************************异步请求*********************************/
        //获取接口实例
        MoveService movieService = retrofit.create(MoveService.class);
        //调用方法得到一个Call
        Call<ResponseBody> call = movieService.getTop2501(0,2);
        //进行网络请求
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                response.body();
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.getMessage();
            }
        });
    }

    public void Rxjavanetwork(){
        final String BASE_URL = "https://api.douban.com/v2/movie/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        MoveService movieService = retrofit.create(MoveService.class);
        movieService.getTop250(0,20)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<DoubanMovieBean, DoubanMovieBean>() {
                    @Override
                    public DoubanMovieBean apply(DoubanMovieBean doubanMovieBean) throws Exception {
                        doubanMovieBean.setTitle("修改了");
                        return doubanMovieBean;
                    }
                })
                .subscribe(new DisposableObserver<DoubanMovieBean>() {
                    @Override
                    public void onNext(DoubanMovieBean doubanMovieBean) {
                        DoubanMovieBean move =  doubanMovieBean;
                    }

                    @Override
                    public void onError(Throwable e) {
e.getMessage();
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }




    public void okhttpnetwork(){
        OkHttpClient.Builder okbuilder = new OkHttpClient.Builder();
        okbuilder.connectTimeout(10000, TimeUnit.SECONDS);//连接 超时时间
        okbuilder.writeTimeout(10000,TimeUnit.SECONDS);//写操作 超时时间
        okbuilder.readTimeout(10000,TimeUnit.SECONDS);//读操作 超时时间
        okbuilder.retryOnConnectionFailure(true);//错误重连
        //项目中设置头信息
        Interceptor headerInterceptor = new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                Request.Builder requestBuilder = originalRequest.newBuilder()
                        .addHeader("Accept-Encoding", "gzip")
                        .addHeader("Accept", "application/json")
                        .addHeader("Content-Type", "application/json; charset=utf-8")
                        .method(originalRequest.method(), originalRequest.body());
                requestBuilder.addHeader("Authorization", "Bearer " );//添加请求头信息，服务器进行token有效性验证
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        };
        okbuilder.addInterceptor(headerInterceptor);


        final String BASE_URL = "https://api.douban.com/v2/movie/";
        Retrofit retrofit = new Retrofit.Builder()
                .client(okbuilder.build())
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        MoveService movieService = retrofit.create(MoveService.class);
        movieService.getTop250(0,20)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<DoubanMovieBean>() {
                    @Override
                    public void onNext(DoubanMovieBean doubanMovieBean) {
                        DoubanMovieBean move =  doubanMovieBean;
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.getMessage();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /*
 *
 * 获取电影列表
 */
    private void getMovieList(){
       MovieLoader mMovieLoader = new MovieLoader();
//        mMovieLoader.getMovie(0,-1).subscribe(
//                new Consumer<List<DoubanMovieBean.SubjectsBean>>() {
//                    @Override
//                    public void accept(List<DoubanMovieBean.SubjectsBean> subjectsBeans) throws Exception {
//                        List<DoubanMovieBean.SubjectsBean> test =  subjectsBeans;
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//                       throwable.getMessage();
//                    }
//                });


//        mMovieLoader.getMovie(0,-1).subscribe(new OnSuccessAndFaultSub<List<DoubanMovieBean.SubjectsBean>>(new OnSuccessAndFaultListener<List<DoubanMovieBean.SubjectsBean>>() {
//            @Override
//            public void onSuccess(List<DoubanMovieBean.SubjectsBean> result) {
//                List<DoubanMovieBean.SubjectsBean> test =  result;
//            }
//
//            @Override
//            public void onFault(String errorMsg) {
//                String x = errorMsg;
//            }
//        }));


        mMovieLoader.getMovieAll(0,10).subscribe(new OnSuccessAndFaultSub<DoubanMovieBean>(new OnSuccessAndFaultListener() {
            @Override
            public void onSuccess(Object result) {
                DoubanMovieBean test =  (DoubanMovieBean)result;
            }

            @Override
            public void onFault(String errorMsg) {

            }
        }));

    }


}
