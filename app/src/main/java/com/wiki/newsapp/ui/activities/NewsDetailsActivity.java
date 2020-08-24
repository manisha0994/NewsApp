package com.wiki.newsapp.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wiki.newsapp.R;
import com.wiki.newsapp.model.Articles;

public class NewsDetailsActivity extends AppCompatActivity {
    Articles articleData;
    TextView title,description,author;
    ImageView articleImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        Bundle bundle = getIntent().getBundleExtra("bundle");
        articleData = (Articles) bundle.getSerializable("value");
        initViews();
        Log.d("value",articleData.getSource().getName());
        title.setText(getIntent().getStringExtra("title"));
        description.setText(getIntent().getStringExtra("description"));
        author.setText(getIntent().getStringExtra("author"));
        Glide.with(this).load(getIntent().getStringExtra("url")).placeholder(R.drawable.ic_image_black_24dp).centerCrop().into(articleImage);

    }
     private void initViews(){
        title = findViewById(R.id.tv_news_title);
        description = findViewById(R.id.tv_news_details);
        author = findViewById(R.id.tv_author);
        articleImage = findViewById(R.id.im_news_image);

     }
}
