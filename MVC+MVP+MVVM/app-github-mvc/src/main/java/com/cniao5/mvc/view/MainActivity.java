package com.cniao5.mvc.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cniao5.mvc.R;
import com.cniao5.mvc.adapter.RepoListAdapter;
import com.cniao5.mvc.model.GithubService;
import com.cniao5.mvc.model.Repo;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar ;
    private RecyclerView recyclerView;
    private RepoListAdapter adapter ;
    private TextView text_description ;
    private ProgressBar progress ;
    private TextView text_info ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        text_description = (TextView) findViewById(R.id.text_description);
        progress = (ProgressBar) findViewById(R.id.progress);
        text_info = (TextView) findViewById(R.id.text_info);

        setSupportActionBar(toolbar);
        text_description.setText("GitHub Java");
        loadGitHubRepo();
    }

    private void loadGitHubRepo() {
        String url = "http://github.laowch.com/json/java_daily" ;
        GithubService.Factory.create().javaRepositories(url)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<Repo>>() {
                    @Override
                    public void onCompleted() {
                        text_info.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Throwable e) {
//                        progress.setVisibility(View.GONE);
//                        text_info.setVisibility(View.VISIBLE);
//                        recyclerView.setVisibility(View.GONE);
                        progress.setVisibility(View.GONE);

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
                        setupRecyclerView(recyclerView,list);
                    }

                    @Override
                    public void onNext(List<Repo> repos) {
                        progress.setVisibility(View.GONE);
                        if(repos != null)
                            setupRecyclerView(recyclerView,repos);
                    }
                }) ;
    }


    private void setupRecyclerView(RecyclerView recyclerView,List<Repo> repos) {
        adapter = new RepoListAdapter(this,repos) ;
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager((this)));
    }
}
