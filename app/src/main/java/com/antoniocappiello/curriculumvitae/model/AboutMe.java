package com.antoniocappiello.curriculumvitae.model;

public class AboutMe {

    private String mName;
    private String mNationality;
    private String mOverview;
    private String mCareer;
    private String mFreeTime;

    private AboutMe(Builder builder) {
        mName = builder.mName;
        mNationality = builder.mNationality;
        mOverview = builder.mOverview;
        mCareer = builder.mCareer;
        mFreeTime = builder.mFreeTime;
    }

    @Override
    public String toString() {
        return "AboutMe{" +
                "mName='" + mName + '\'' +
                ", mNationality='" + mNationality + '\'' +
                ", mOverview='" + mOverview + '\'' +
                ", mCareer='" + mCareer + '\'' +
                ", mFreeTime='" + mFreeTime + '\'' +
                '}';
    }

    public static class Builder {

        private String mName;
        private String mNationality;
        private String mOverview;
        private String mCareer;
        private String mFreeTime;

        public Builder name(String name){
            mName = name;
            return this;
        }

        public Builder nationality(String nationality){
            mNationality = nationality;
            return this;
        }

        public Builder overview(String overview){
            mOverview = overview;
            return this;
        }

        public Builder career(String career){
            mCareer = career;
            return this;
        }

        public Builder freeTime(String freeTime){
            mFreeTime = freeTime;
            return this;
        }

        public AboutMe build(){
            return new AboutMe(this);
        }

    }

}