package com.wiki.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import android.os.Bundle;
import android.util.Log;

import com.wiki.newsapp.model.NewsResult;
import com.wiki.newsapp.network.NewsApiService;
import com.wiki.newsapp.network.repository.NetworkRepository;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NewsApiService retrofit = new NetworkRepository().getApi();
        retrofit.getlatestnews("in","68fceafffd564513a8083b36ff0b8d99").enqueue(new Callback<NewsResult>() {
            /**
             * Invoked for a received HTTP response.
             *
             * <p>Note: An HTTP response may still indicate an application-level failure such as a 404 or 500.
             * Call {@link Response#isSuccessful()} to determine if the response indicates success.
             *
             * @param call
             * @param response
             */
            @Override
            public void onResponse(Call<NewsResult> call, Response<NewsResult> response) {
                Log.i("Data", response.body().getArticles().get(0).getSource().getName());
            }

            /**
             * Invoked when a network exception occurred talking to the server or when an unexpected exception
             * occurred creating the request or processing the response.
             *
             * @param call
             * @param t
             */
            @Override
            public void onFailure(Call<NewsResult> call, Throwable t) {
                Log.i("Data", t.toString());
            }
        });


    }
}
