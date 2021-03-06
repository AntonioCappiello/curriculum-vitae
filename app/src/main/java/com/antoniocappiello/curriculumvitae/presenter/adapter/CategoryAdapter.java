/*
 * Created by Antonio Cappiello on 1/13/16 9:31 PM
 * Copyright (c) 2016. All rights reserved.
 *
 * Last modified 1/13/16 8:15 PM
 */

package com.antoniocappiello.curriculumvitae.presenter.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.antoniocappiello.curriculumvitae.R;
import com.antoniocappiello.curriculumvitae.model.Category;
import com.antoniocappiello.curriculumvitae.presenter.event.CategoryClickedEvent;
import com.orhanobut.logger.Logger;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private final Category[] mCategoryArray;

    public CategoryAdapter(Category[] categoryArray) {
        mCategoryArray = categoryArray;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View newCategoryView = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false);
        return new ViewHolder(newCategoryView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.updateView(mCategoryArray[position]);
    }

    @Override
    public int getItemCount() {
        return mCategoryArray.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @Bind(R.id.title)
        TextView mTitleTextView;

        @Bind(R.id.image)
        ImageView mImageView;

        private Category mCategory;

        public ViewHolder(View categoryView) {
            super(categoryView);
            ButterKnife.bind(this, categoryView);
            categoryView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Logger.e(mCategory.name());
            EventBus.getDefault().post(new CategoryClickedEvent(mCategory, mImageView));
        }

        public void updateView(Category category) {
            mTitleTextView.setText(category.getTitleResourceId());
            mImageView.setImageResource(category.getImageResourceId());
            mCategory = category;
        }
    }
}
