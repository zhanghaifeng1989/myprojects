package com.example.administrator.mytest.network2.api;




import android.database.Observable;

import com.example.administrator.mytest.network2.entity.BookBean;
import com.example.administrator.mytest.network2.entity.Top250Bean;
 import java.util.List;


/**
 * Created by allen on 2016/12/26.
 */

public interface ApiService {

    @GET("v2/book/1220562")
    Observable<BookBean> getBook();

    @GET("v2/movie/top250")
    Observable<Top250Bean> getTop250(@Query("count") int count);

    @GET("v2/book/1220562")
    Observable<String> getBookString();

    /**
     * 上传多个文件  demo
     *
     * @param uploadUrl 地址
     * @param files     文件
     * @return ResponseBody
     */
    @Multipart
    @POST
    Observable<String> uploadFiles(@Url String uploadUrl,
                                   @Part List<MultipartBody.Part> files);
    //以下是post请求的两种方式demo示例

//    /**
//     * post提交json数据 demo
//     * @param map 键值对
//     * @return
//     */
//    @POST("xxx")
//    Observable<BaseData<T>> getData(@Body Map map);
//
//    /**
//     * post提交表单 demo
//     * @param name
//     * @return
//     */
//    @FormUrlEncoded
//    @POST("xxx")
//    Observable<BaseData<T>> getData(@Field("name") String name);
//

}
