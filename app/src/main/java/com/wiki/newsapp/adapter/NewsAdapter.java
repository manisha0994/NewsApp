package com.wiki.newsapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wiki.newsapp.R;
import com.wiki.newsapp.model.Articles;
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
                
                intent.putExtra(context.getString(R.string.title_serializedname), newsResultArrayList.get(position).getTitle());
                intent.putExtra(context.getString(R.string.description_serializedname), newsResultArrayList.get(position).getDescription());
                intent.putExtra(context.getString(R.string.author_serializedname), newsResultArrayList.get(position).getAuthor());
                intent.putExtra(context.getString(R.string.imageurl_serializedname), newsResultArrayList.get(position).getUrlToImage());
                intent.putExtra(context.getString(R.string.url_serializedname), newsResultArrayList.get(position).getUrl());
                
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

        private NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_heading);
            details = itemView.findViewById(R.id.tv_details);
            newsImage = itemView.findViewById(R.id.iv_news);
        }
    }
}
