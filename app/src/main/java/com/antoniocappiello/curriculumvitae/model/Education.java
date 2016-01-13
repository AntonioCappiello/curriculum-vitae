/*
 * Created by Antonio Cappiello on 1/13/16 9:31 PM
 * Copyright (c) 2016. All rights reserved.
 *
 * Last modified 1/10/16 6:11 PM
 */

package com.antoniocappiello.curriculumvitae.model;

import java.util.Date;

public class Education implements Comparable<Education>{

    private String mType;
    private String mProvider;
    private String mLogoUrl;
    private String mName;
    private Date mDate;

    private Education(Builder builder) {
        mType = builder.mType;
        mProvider = builder.mProvider;
        mLogoUrl = builder.mLogoUrl;
        mName = builder.mName;
        mDate = builder.mDate;
    }

    public String getType() {
        return mType;
    }

    public String getProvider() {
        return mProvider;
    }

    public String getLogoUrl() {
        return mLogoUrl;
    }

    public String getName() {
        return mName;
    }

    public Date getDate() {
        return mDate;
    }

    @Override
    public String toString() {
        return "Education{" +
                "mType='" + mType + '\'' +
                ", mProvider='" + mProvider + '\'' +
                ", mLogoUrl='" + mLogoUrl + '\'' +
                ", mName='" + mName + '\'' +
                ", mDate='" + mDate + '\'' +
                '}';
    }

    @Override
    public int compareTo(Education another) {
        return this.getDate().compareTo(another.getDate());
    }

    public static class Builder{

        private String mType;
        private String mProvider;
        private String mLogoUrl;
        private String mName;
        private Date mDate;

        public Builder type(String type){
            mType = type;
            return this;
        }

        public Builder provider(String provider){
            mProvider = provider;
            return this;
        }

        public Builder logoUrl(String logoUrl){
            mLogoUrl = logoUrl;
            return this;
        }

        public Builder name(String name){
            mName = name;
            return this;
        }

        public Builder year(Date date){
            mDate = date;
            return this;
        }

        public Education build(){
            return new Education(this);
        }

    }

}
