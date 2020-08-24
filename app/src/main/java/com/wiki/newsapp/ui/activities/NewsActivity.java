package com.wiki.newsapp.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.util.Log;

import com.wiki.newsapp.R;
import com.wiki.newsapp.adapter.NewsAdapter;
import com.wiki.newsapp.model.Articles;
import com.wiki.newsapp.model.NewsResult;
import com.wiki.newsapp.network.NewsApiService;
import com.wiki.newsapp.network.repository.NetworkRepository;

import java.util.ArrayList;

public class NewsActivity extends AppCompatActivity {
    RecyclerView rvNews;
    ArrayList<Articles> newsList;
    NewsAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        getSupportActionBar().hide();
        initViews();
        rvNews.setLayoutManager(new LinearLayoutManager(this));
        rvNews.setAdapter(adapter);
        initiateApi();
    }

    void initViews() {
        rvNews = findViewById(R.id.rv_news);
        newsList = new ArrayList<>();
        adapter = new NewsAdapter(newsList);
    }

    void initiateApi() {
        NewsApiService retrofit = new NetworkRepository().getApi();
        retrofit.getlatestnews("in", getResources().getString(R.string.api_key)).enqueue(new Callback<NewsResult>() {

            @Override
            public void onResponse(Call<NewsResult> call, Response<NewsResult> response) {
             //   Log.i("Data", response.body().getArticles().get(0).getSource().getName());

                newsList.addAll(response.body().getArticles());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<NewsResult> call, Throwable t) {
                Log.i("Data", t.toString());
            }
        });
    }
}
