package com.example.android.newsapp;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.ArrayList;

/**
 * Created by Dell on 5/30/2017.
 */

public class NewsLoader extends AsyncTaskLoader<ArrayList<News>> {

    /** Query URL */
    private String Url;

    public NewsLoader(Context context, String url) {
        super(context);
        Url = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public ArrayList<News> loadInBackground() {
        if (Url == null){
            return null;
        }

        ArrayList<News> News = QueryUtils.fetchNewsList(Url);

        return News;
    }
}
