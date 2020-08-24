package com.wiki.newsapp.ui.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.wiki.newsapp.R;

import androidx.appcompat.app.AppCompatActivity;

public class NewsDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    TextView title, description, author;
    ImageView articleImage;
    ImageButton gotoNews;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        if (getSupportActionBar() != null)
            getSupportActionBar().hide();

        initViews();
        initClickListeners();

        title.setText(getIntent().getStringExtra("title"));
        description.setText(getIntent().getStringExtra("description"));
        author.setText(getIntent().getStringExtra("author"));

        Glide.with(this).load(getIntent().getStringExtra("imageurl")).placeholder(R.drawable.ic_image_black_24dp).centerCrop().into(articleImage);

    }

    private void initClickListeners() {
        gotoNews.setOnClickListener(this);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void initViews() {
        title = findViewById(R.id.tv_news_title);
        description = findViewById(R.id.tv_news_details);
        author = findViewById(R.id.tv_author);
        articleImage = findViewById(R.id.im_news_image);
        gotoNews = findViewById(R.id.ib_goto);
        toolbar = findViewById(R.id.tb_news_details);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_goto:
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(getIntent().getStringExtra("url")));
                startActivity(i);
                break;

        }
    }
}
