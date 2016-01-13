/*
 * Created by Antonio Cappiello on 1/13/16 9:31 PM
 * Copyright (c) 2016. All rights reserved.
 *
 * Last modified 1/10/16 9:02 PM
 */

package com.antoniocappiello.curriculumvitae.model;

import java.util.Date;

public class WorkExperience implements Comparable<WorkExperience>{

    private String mRole;
    private String mCompany;
    private String mLogoUrl;
    private Date mDateStart;
    private Date mDateEnd;
    private String mDescription;
    private String mKeyWords;

    private WorkExperience(Builder builder) {
        mRole = builder.mRole;
        mCompany = builder.mCompany;
        mLogoUrl = builder.mLogoUrl;
        mDateStart = builder.mDateStart;
        mDateEnd = builder.mDateEnd;
        mDescription = builder.mDescription;
        mKeyWords = builder.mKeyWords;
    }

    public String getRole() {
        return mRole;
    }

    public String getCompany() {
        return mCompany;
    }

    public String getLogoUrl() {
        return mLogoUrl;
    }

    public Date getDateStart() {
        return mDateStart;
    }

    public Date getDateEnd() {
        return mDateEnd;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getKeyWords() {
        return mKeyWords;
    }

    @Override
    public int compareTo(WorkExperience another) {
        return this.getDateStart().compareTo(another.getDateStart());
    }


    public static class Builder {
        private String mRole;
        private String mCompany;
        private String mLogoUrl;
        private Date mDateStart;
        private Date mDateEnd;
        private String mDescription;
        private String mKeyWords;

        public Builder role(String role){
            mRole = role;
            return this;
        }

        public Builder company(String company){
            mCompany = company;
            return this;
        }

        public Builder logoUrl(String logoUrl){
            mLogoUrl = logoUrl;
            return this;
        }

        public Builder startDate(Date date){
            mDateStart = date;
            return this;
        }

        public Builder endDate(Date date){
            mDateEnd = date;
            return this;
        }

        public Builder description(String description){
            mDescription = description;
            return this;
        }

        public Builder keyWords(String keyWords){
            mKeyWords = keyWords;
            return this;
        }

        public WorkExperience build(){
            return new WorkExperience(this);
        }
    }
}
