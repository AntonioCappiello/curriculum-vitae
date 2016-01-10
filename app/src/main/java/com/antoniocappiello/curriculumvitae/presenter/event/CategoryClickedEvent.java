package com.antoniocappiello.curriculumvitae.presenter.event;

import android.widget.ImageView;

import com.antoniocappiello.curriculumvitae.model.Category;

public class CategoryClickedEvent {
    private final Category mCategory;
    private ImageView mImageView;

    public CategoryClickedEvent(Category category, ImageView imageView) {
        mCategory = category;
        mImageView = imageView;
    }

    public Category getCategory() {
        return mCategory;
    }

    public ImageView getImageView() {
        return mImageView;
    }

}
