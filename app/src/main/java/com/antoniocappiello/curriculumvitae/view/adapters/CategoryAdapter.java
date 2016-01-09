package com.antoniocappiello.curriculumvitae.view.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.antoniocappiello.curriculumvitae.R;
import com.antoniocappiello.curriculumvitae.model.Category;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private final Category[] mCategoryArray;

    public CategoryAdapter(Category[] categoryArray) {
        mCategoryArray = categoryArray;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View newCategoryView = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(newCategoryView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTitle.setText(mCategoryArray[position].getTitleResourceId());
        holder.mImageView.setImageResource(mCategoryArray[position].getImageResourceId());
    }

    @Override
    public int getItemCount() {
        return mCategoryArray.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder  {
        private final TextView mTitle;
        private final ImageView mImageView;

        public ViewHolder(View categoryView) {
            super(categoryView);
            mTitle = (TextView) categoryView.findViewById(R.id.title);
            mImageView = (ImageView) categoryView.findViewById(R.id.image);
        }
    }
}
