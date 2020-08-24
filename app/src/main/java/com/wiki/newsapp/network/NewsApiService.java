package com.wiki.newsapp.network;

import com.wiki.newsapp.model.NewsResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApiService {
    @GET("/v2/top-headlines")
    Call<NewsResult> getlatestnews(@Query("country") String country, @Query("apikey") String apikey);

}
