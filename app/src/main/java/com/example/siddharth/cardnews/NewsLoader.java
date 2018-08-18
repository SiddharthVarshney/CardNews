package com.example.siddharth.cardnews;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Loads a list of earthquakes by using an AsyncTask to perform the
 * network request to the given URL.
 */
public class NewsLoader extends AsyncTaskLoader<List<NewsData>> {



    /** Query URL */
    private String mUrl;

    /**
     * Constructs a new {@link NewsLoader}.
     *
     * @param context of the activity
     * @param url to load data from
     */
    public NewsLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    /**
     * This is on a background thread.
     */
    @Override
    public List<NewsData> loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        // Perform the network request, parse the response, and extract a list of earthquakes.
        List<NewsData> news = QueryUtils.fetchNewsData(mUrl);
        return news;
    }
}

