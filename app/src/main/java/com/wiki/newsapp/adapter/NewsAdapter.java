package com.wiki.newsapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wiki.newsapp.R;
import com.wiki.newsapp.model.Articles;
import com.wiki.newsapp.model.NewsResult;
import com.wiki.newsapp.ui.activities.NewsDetailsActivity;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private ArrayList<Articles> newsResultArrayList;
    private Context context;

    public NewsAdapter(ArrayList<Articles> newsResultArrayList) {
        this.newsResultArrayList = newsResultArrayList;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new NewsViewHolder(LayoutInflater.from(context).inflate(R.layout.item_news, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, final int position) {
        holder.title.setText(newsResultArrayList.get(position).getTitle());
        holder.details.setText(newsResultArrayList.get(position).getDescription());
        Glide.with(holder.itemView).load(newsResultArrayList.get(position).getUrlToImage()).placeholder(R.drawable.ic_image_black_24dp).centerCrop().into(holder.newsImage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intent = new Intent(context, NewsDetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("value", newsResultArrayList.get(position));
                intent.putExtra("bundle",bundle);
                intent.putExtra("title",newsResultArrayList.get(position).getTitle());
                intent.putExtra("description",newsResultArrayList.get(position).getDescription());
                intent.putExtra("author",newsResultArrayList.get(position).getAuthor());
                intent.putExtra("url",newsResultArrayList.get(position).getUrlToImage());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsResultArrayList.size();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder {
        TextView title, details;
        ImageView newsImage;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_heading);
            details = itemView.findViewById(R.id.tv_details);
            newsImage = itemView.findViewById(R.id.iv_news);
        }
    }
}
