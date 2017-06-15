package com.example.android.newsapp;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<News>> {

    private String REQUEST_URL="http://content.guardianapis.com/search?q=debate&tag=politics/politics&from-date=2014-01-01&api-key=test";
    private NewsAdapter Adapter;
    private TextView emptyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emptyTextView = (TextView)findViewById(R.id.emptyTextView);
        Adapter = new NewsAdapter(this,new ArrayList<News>());
        ListView newsListView = (ListView)findViewById(R.id.listView);
        newsListView.setEmptyView(emptyTextView);
        newsListView.setAdapter(Adapter);
        newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent=new Intent(Intent.ACTION_VIEW);
                News currentNews=Adapter.getItem(position);
                String url=currentNews.getUrl();
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });
        ConnectivityManager connectivityManager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {

            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(1, null, this);
        } else {
            View loadingIndicator = findViewById(R.id.progressBar);
            loadingIndicator.setVisibility(View.GONE);
            emptyTextView.setText(R.string.noInternetConnection);
        }
    }

    @Override
    public Loader<ArrayList<News>> onCreateLoader(int i, Bundle bundle) {
        return new NewsLoader(this,REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<News>> loader, ArrayList<News> news) {
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        emptyTextView.setText(R.string.noNews);
        Adapter.clear();
        if (news != null && !news.isEmpty()) {

            Adapter.addAll(news);
        }
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<News>> loader) {
        Adapter.clear();
    }
}
