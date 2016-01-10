package com.antoniocappiello.curriculumvitae.model;

public class Education {

    private String mType;
    private String mProvider;
    private String mLogoUrl;
    private String mName;
    private String mYear;

    private Education(Builder builder) {
        mType = builder.mType;
        mProvider = builder.mProvider;
        mLogoUrl = builder.mLogoUrl;
        mName = builder.mName;
        mYear = builder.mYear;
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

    public String getYear() {
        return mYear;
    }

    @Override
    public String toString() {
        return "Education{" +
                "mType='" + mType + '\'' +
                ", mProvider='" + mProvider + '\'' +
                ", mLogoUrl='" + mLogoUrl + '\'' +
                ", mName='" + mName + '\'' +
                ", mYear='" + mYear + '\'' +
                '}';
    }

    public static class Builder{

        private String mType;
        private String mProvider;
        private String mLogoUrl;
        private String mName;
        private String mYear;

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

        public Builder year(String year){
            mYear = year;
            return this;
        }

        public Education build(){
            return new Education(this);
        }

    }

}
