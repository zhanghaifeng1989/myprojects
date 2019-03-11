package com.cniao5.mvp.presenter.impl;

import android.view.View;

import com.cniao5.mvp.model.GithubService;
import com.cniao5.mvp.model.Repo;
import com.cniao5.mvp.presenter.MainPresenter;
import com.cniao5.mvp.view.BaseView;
import com.cniao5.mvp.view.MainBaseView;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * <p>Description:
 *
 * @author xzhang
 */

public class MainPresenterImpl implements MainPresenter {

    private MainBaseView mainBaseView ;
    private List<Repo> repoList ;
    private Subscription subscribe ;


    @Override
    public void attachView(MainBaseView view) {
        this.mainBaseView =  view ;
    }

    @Override
    public void detachView() {
        mainBaseView = null ;
        if(subscribe != null){
            subscribe.unsubscribe();
        }
    }

    @Override
    public void loadGitHubJava() {
        mainBaseView.showProgress();

        String url = "http://github.laowch.com/json/java_daily" ;
        subscribe = GithubService.Factory.create().javaRepositories(url)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<Repo>>() {
                    @Override
                    public void onCompleted() {
                        if (repoList != null) {
                            mainBaseView.showRecyclerView(repoList);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
//                        mainBaseView.showErrorMessage();

                        List<Repo> list = new ArrayList<Repo>();
                        Repo repo1 = new Repo();
                        List<Repo.ContributorsBean> cons1 = new ArrayList<Repo.ContributorsBean>();
                        Repo.ContributorsBean con1 = new Repo.ContributorsBean();
                        con1.setName("Adrian Serrano");
                        con1.setAvatar("https://avatars0.githubusercontent.com/u/15056957?s=460&amp&v=4");
                        cons1.add(con1);
                        repo1.setContributors(cons1);
                        repo1.setDes("Open Source, Distributed, RESTful Search Engine");
                        repo1.setMeta("38971");
                        repo1.setName("elasticsearch");
                        repo1.setOwner("elastic");
                        repo1.setHref("1");
                        list.add(repo1);
                        repoList = list;
                        mainBaseView.showRecyclerView(repoList);

                    }

                    @Override
                    public void onNext(List<Repo> repos) {
                        repoList = repos;
                    }
                });

    }
}
