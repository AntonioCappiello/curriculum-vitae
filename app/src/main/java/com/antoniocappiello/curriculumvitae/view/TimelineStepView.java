/*
 * Created by Antonio Cappiello on 1/13/16 9:31 PM
 * Copyright (c) 2016. All rights reserved.
 *
 * Last modified 1/10/16 8:09 PM
 */

package com.antoniocappiello.curriculumvitae.view;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.antoniocappiello.curriculumvitae.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import de.hdodenhof.circleimageview.CircleImageView;

public class TimelineStepView extends RelativeLayout{

    private TextView mTitleTextView;
    private TextView mSubtitleTextView;
    private TextView mDateTextView;
    private CircleImageView mLogoImage;
    private View mLine;

    public TimelineStepView(Context context, Builder builder) {
        super(context);
        RelativeLayout rootView = (RelativeLayout) inflate(context, R.layout.timeline_step_view, this);
        initWidgets(rootView);
        updateView(builder);
    }

    private void initWidgets(RelativeLayout rootView) {
        mTitleTextView = (TextView) rootView.findViewById(R.id.timeline_title);
        mSubtitleTextView = (TextView) rootView.findViewById(R.id.timeline_subtitle);
        mDateTextView = (TextView) rootView.findViewById(R.id.date);
        mLogoImage = (CircleImageView) rootView.findViewById(R.id.logo);
        mLine = rootView.findViewById(R.id.line);
    }

    public void updateView(Builder builder){
        mTitleTextView.setText(builder.mTitle);
        mSubtitleTextView.setText(builder.mSubtitle);
        mDateTextView.setText(builder.mDate);
        ImageLoader.getInstance().displayImage(builder.mLogo, mLogoImage);
        mLine.setVisibility(builder.mCanShow ? VISIBLE : GONE);
    }

    public static class Builder {
        private final Context mContext;
        private String mTitle;
        private String mSubtitle;
        private String mDate;
        private String mLogo;
        private boolean mCanShow;

        public Builder(Context context) {
            mContext = context;
        }

        public Builder setTitle(String title) {
            mTitle = title;
            return this;
        }

        public Builder setSubtitle(String subtitle) {
            mSubtitle = subtitle;
            return this;
        }

        public Builder setDate(String date) {
            mDate = date;
            return this;
        }

        public Builder setLogo(String logo) {
            mLogo = logo;
            return this;
        }

        public Builder showLine(boolean canShow) {
            mCanShow = canShow;
            return this;
        }

        public TimelineStepView build() {
            return new TimelineStepView(mContext, this);
        }
    }
}
