package com.example.siddharth.cardnews;

import android.annotation.SuppressLint;
import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.app.LoaderManager.LoaderCallbacks;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderCallbacks<List<NewsData>> {

    /** Tag for log messages */
    public static final String LOG_TAG = NewsLoader.class.getName();
    private static final String NEWS_REQUEST_URL ="https://newsapi.org/v2/top-headlines?country=in&apiKey=02d5a6c928c14944b23057e1054b68d9";
    private static  final  int NEWS_LOADER_ID=1;
    private  NewsAdapter mAdapter;
    private TextView mEmptyStateTextView;
//    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView newsListView = (ListView) findViewById(R.id.list);

        // Create a new adapter that takes an empty list of earthquakes as input
        mAdapter = new NewsAdapter(this, new ArrayList<NewsData>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        newsListView.setAdapter(mAdapter);

        // Set an item click listener on the ListView, which sends an intent to a web browser
        // to open a website with more information about the selected earthquake.
        newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Find the current earthquake that was clicked on
                NewsData currentNews = mAdapter.getItem(position);

                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri newsUri = Uri.parse(currentNews.getmUrl());

                // Create a new intent to view the earthquake URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, newsUri);

                // Send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        });

        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        newsListView.setEmptyView(mEmptyStateTextView);

        NewsAsyncTask task = new NewsAsyncTask();
        task.execute(NEWS_REQUEST_URL);

        LoaderManager loaderManager =getLoaderManager();

        loaderManager.initLoader(NEWS_LOADER_ID,null,this);
    }

    @Override
    public Loader onCreateLoader(int i, Bundle bundle) {
        return new NewsLoader(this,NEWS_REQUEST_URL);
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onLoadFinished(Loader<List<NewsData>> loader, List<NewsData> news) {
        mAdapter.clear();
        if(news !=null && !news.isEmpty()){
            mAdapter.addAll(news);
        }
        mEmptyStateTextView.setText(R.string.no_news);

        ProgressBar spinner= (ProgressBar)findViewById(R.id.loading_spinner);
        spinner.setVisibility(View.GONE);

    }

    @Override
    public void onLoaderReset(Loader<List<NewsData>> loader) {
        mAdapter.clear();

    }

    private class NewsAsyncTask extends AsyncTask<String, Void, List<NewsData>> {

        @Override
        protected List<NewsData> doInBackground(String... urls) {
            if (urls.length < 1 || urls[0] == null) {
                return null;
            }
            List<NewsData> result = QueryUtils.fetchNewsData(urls[0]);
            return result;

        }

        @Override
        protected void onPostExecute(List<NewsData> data) {
            mAdapter.clear();

            if (data != null && !data.isEmpty()) {
                mAdapter.addAll(data);
            }
        }
    }
}
