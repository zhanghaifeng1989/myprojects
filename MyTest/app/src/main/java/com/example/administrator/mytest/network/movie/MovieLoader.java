package com.example.administrator.mytest.network.movie;


import com.example.administrator.mytest.network.retrofitmanager.ObjectLoader;
import com.example.administrator.mytest.network.entity.DoubanMovieBean;
import com.example.administrator.mytest.network.retrofitmanager.RetrofitServiceManager;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Created by Administrator on 2019/3/13.
 */

public class MovieLoader extends ObjectLoader {
    private MoveService mMovieService;
    public MovieLoader(){
        mMovieService = RetrofitServiceManager.getInstance().create(MoveService.class);
    }
    /**
     * 获取电影列表
     * @param start
     * @param count
     * @return
     */
    public Observable<List<DoubanMovieBean.SubjectsBean>> getMovie(int start, int count){

        return observe(mMovieService.getTop250(start , count)).map(new Function<DoubanMovieBean, List<DoubanMovieBean.SubjectsBean>>() {
            @Override
            public List<DoubanMovieBean.SubjectsBean> apply(DoubanMovieBean doubanMovieBean) throws Exception {
                return doubanMovieBean.getSubjects();
            }
        });


    }

    public Observable<DoubanMovieBean> getMovieAll(int start, int count){

        return observe(mMovieService.getTop250(start , count));

    }





}


