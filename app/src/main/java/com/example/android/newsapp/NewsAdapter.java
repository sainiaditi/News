package com.example.android.newsapp;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.category;

/**
 * Created by Dell on 5/30/2017.
 */

public class NewsAdapter extends ArrayAdapter<News>{
    public NewsAdapter(@NonNull Context context,@NonNull ArrayList<News> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.activity_list, parent, false);
        }
        News currentNews = getItem(position);
        TextView currentCategory = (TextView) listItemView.findViewById(R.id.category);
        currentCategory.setText(currentNews.getCategory());
        TextView currentTitle = (TextView) listItemView.findViewById(R.id.newsHeadlines);
        currentTitle.setText(currentNews.getTitle());


        return listItemView;
    }
}
