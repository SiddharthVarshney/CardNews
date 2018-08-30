package com.example.siddharth.cardnews;

public class NewsData {

    private String mUrl;

    private String mHeadline;

    private String mDate_time;


    public NewsData(String url, String headline, String date_time) {
        mHeadline = headline;
        mDate_time = date_time;
        mUrl = url;
    }

    public String getmHeadline() {
        return mHeadline;
    }

    public String getmDate_time() {
        return mDate_time;
    }

    public String getmUrl() {
        return mUrl;
    }

}
