package com.example.administrator.mytest.network.movie;


import com.example.administrator.mytest.network.entity.DoubanMovieBean;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2019/3/13.
 */

public interface MoveService {
    //获取豆瓣Top250 榜单
    @GET("top250")
    Observable<DoubanMovieBean> getTop250(@Query("start") int start, @Query("count") int count);

    @FormUrlEncoded
    @POST("top250")
    Call<ResponseBody> getTop2501 (@Field("start") int start , @Field("count") int count);

    //    @FormUrlEncoded
//    @POST("top250")
//    Observable<DoubanMovieBean> getTop250 (@Field("start") int start , @Field("count") int count);

}
