package com.example.android.newsapp;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

/**
 * Created by Dell on 5/30/2017.
 */

public class News {
    private String url;
    private String category;
    private String title;

    public News(String Category,String Title,String URL){
        title = Title;
        url = URL;
        category = Category;
    }

    public String getUrl(){
        return url;
    }

    public String getCategory(){
        return category;
    }

    public String getTitle(){
        return title;
    }
}
