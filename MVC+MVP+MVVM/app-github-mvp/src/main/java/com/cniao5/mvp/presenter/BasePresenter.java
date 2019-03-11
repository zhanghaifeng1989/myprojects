package com.cniao5.mvp.presenter;

import com.cniao5.mvp.view.BaseView;

/**
 * <p>Description:
 * 业务逻辑处理的基类
 *
 * @author xzhang
 */

public interface BasePresenter<T> {

    void attachView(T view) ;

    void detachView() ;

}
