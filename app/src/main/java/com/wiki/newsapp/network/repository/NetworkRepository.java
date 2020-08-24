package com.wiki.newsapp.network.repository;

import com.google.gson.Gson;
import com.wiki.newsapp.constant.Constants;
import com.wiki.newsapp.network.NewsApiService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkRepository {
    public NewsApiService getApi() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    return retrofit.create(NewsApiService.class);
    }
}
