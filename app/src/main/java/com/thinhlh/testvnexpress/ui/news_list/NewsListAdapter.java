/*
 * Copyright (c) 2020. Thinh Lai
 * All rights reserved.
 *
 * Authors: thinhlh
 * File: NewsListAdapter.java
 * Created: 2020/11/14
 * Last modified: 11/14/20 1:21 AM
 */

package com.thinhlh.testvnexpress.ui.news_list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.thinhlh.testvnexpress.data.model.RssItem;
import com.thinhlh.testvnexpress.databinding.ItemNewsBinding;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Thinh Lai on 11/14/20.
 */
public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.NewsListViewHolder> {

    public interface NewsListAdapterListener {
        void onClickItem(RssItem item);
    }

    private List<RssItem> mItems;
    private NewsListAdapterListener mListener;

    @Inject
    NewsListAdapter() {
        mItems = new ArrayList<>();
    }

    public void setListener(NewsListAdapterListener listener) {
        this.mListener = listener;
    }

    public void setItems(List<RssItem> items) {
        this.mItems = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NewsListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemNewsBinding viewBinding = ItemNewsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        NewsListViewHolder viewHolder = new NewsListViewHolder(viewBinding.getRoot());
        viewHolder.setViewBinding(viewBinding);
        viewHolder.setListener(mListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsListViewHolder holder, int position) {
        final RssItem item = mItems.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        if (mItems == null) return 0;
        return mItems.size();
    }

    static class NewsListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ItemNewsBinding mViewBinding;
        private RssItem mItem;
        private NewsListAdapterListener mListener;

        public NewsListViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        public void setListener(NewsListAdapterListener listener) {
            this.mListener = listener;
        }

        public void setViewBinding(ItemNewsBinding viewBinding) {
            this.mViewBinding = viewBinding;
        }

        public void bind(RssItem item) {
            this.mItem = item;
            mViewBinding.title.setText(item.title);
            mViewBinding.date.setText(item.pubDate);
            mViewBinding.description.setText(item.content);
            Glide.with(mViewBinding.image.getContext())
                    .load(item.imageUrl)
                    .into(mViewBinding.image);
        }

        @Override
        public void onClick(View view) {
            if (mListener == null) return;
            mListener.onClickItem(mItem);
        }
    }
}
