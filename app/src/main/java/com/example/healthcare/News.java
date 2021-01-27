package com.example.healthcare;

public class News {

    private String mTitle;

    private String mImageUrl;

    private String mUrl;


    public News(String title, String imageUrl, String url) {
        mTitle = title;
        mImageUrl = imageUrl;
        mUrl = url;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public String getUrl() {
        return mUrl;
    }

}