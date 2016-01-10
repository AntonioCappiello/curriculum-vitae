package com.antoniocappiello.curriculumvitae.model;

public class WorkExperience {

    private String mRole;
    private String mCompany;
    private String mLogoUrl;
    private String mStart;
    private String mEnd;
    private String mDescription;
    private String mKeyWords;

    private WorkExperience(Builder builder) {
        mRole = builder.mRole;
        mCompany = builder.mCompany;
        mLogoUrl = builder.mLogoUrl;
        mStart = builder.mStart;
        mEnd = builder.mEnd;
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

    public String getStart() {
        return mStart;
    }

    public String getEnd() {
        return mEnd;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getKeyWords() {
        return mKeyWords;
    }

    @Override
    public String toString() {
        return "WorkExperience{" +
                "mRole='" + mRole + '\'' +
                ", mCompany='" + mCompany + '\'' +
                ", mLogoUrl='" + mLogoUrl + '\'' +
                ", mStart='" + mStart + '\'' +
                ", mEnd='" + mEnd + '\'' +
                ", mDescription='" + mDescription + '\'' +
                ", mKeyWords='" + mKeyWords + '\'' +
                '}';
    }

    public static class Builder {
        private String mRole;
        private String mCompany;
        private String mLogoUrl;
        private String mStart;
        private String mEnd;
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

        public Builder start(String start){
            mStart = start;
            return this;
        }

        public Builder end(String end){
            mEnd = end;
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
