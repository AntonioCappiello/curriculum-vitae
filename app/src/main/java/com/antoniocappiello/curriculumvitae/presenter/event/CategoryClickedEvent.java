/*
 * Created by Antonio Cappiello on 1/13/16 9:31 PM
 * Copyright (c) 2016. All rights reserved.
 *
 * Last modified 1/10/16 10:36 AM
 */

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
