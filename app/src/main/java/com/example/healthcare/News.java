package com.example.healthcare;

// Based on ud843-Quake Report - Udemy Android Basics Application

/**
 * News
 * An {@link News} object contains information related to a single news item.
 */
public class News {

    // Time of the news
    private String mTitle;

    // Image for news
    private String mImageUrl;

    //Detail for news
    private String mUrl;


    /**
     * Constructs a new {@link News} object.
     *
     * @param title       is the title of news itemi
     * @param imageUrl    is the website URL to the news thumbnail
     * @param url         is the website URL to the news item details
     */
    public News(String title, String imageUrl, String url) {
        mTitle = title;
        mImageUrl = imageUrl;
        mUrl = url;
    }

    // Returns the time of the earthquake.
    public String getTitle() {
        return mTitle;
    }

    // Returns the website URL to find more information about image.
    public String getImageUrl() {
        return mImageUrl;
    }

    //Returns detail news information
    public String getUrl() {
        return mUrl;
    }

}