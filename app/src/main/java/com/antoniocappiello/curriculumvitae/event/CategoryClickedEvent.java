package com.antoniocappiello.curriculumvitae.event;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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
