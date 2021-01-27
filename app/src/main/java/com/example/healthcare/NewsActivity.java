package com.example.healthcare;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;


public class NewsActivity extends Fragment {

    private static final String LOG_TAG = NewsActivity.class.getName();
    private static final String NEWS_REQUEST_URL =
            "https://newsapi.org/v2/top-headlines?country=us&apiKey=923f81756b044aaea07a1c7ef534a6ce";
    private NewsAdapter mAdapter;
    private TextView mEmptyStateTextView;

    NewsActivity.NewsActivityListener activityCallback;
    //Listener for onButtonClick UI
    public interface NewsActivityListener {

    }


//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//        try {
//            activityCallback = (NewsActivityListener) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString()
//                    + " must implement NewsactiityListener");
//        }
//    }

    //We get the reference to the editText and the button setUp the OnClickListener
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.articles, container, false);




        // Find a reference to the {@link ListView} in the layout
        ListView newsListView = (ListView) view.findViewById(R.id.list);

        mEmptyStateTextView = view.findViewById(R.id.empty_view);
        newsListView.setEmptyView(mEmptyStateTextView);

        // Create a new adapter that takes an empty list of news items as input
        mAdapter = new NewsAdapter(this.getContext(), new ArrayList<News>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        newsListView.setAdapter(mAdapter);

        //Enable multi-thread for thumbnail
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // Set an item click listener on the ListView, which sends an intent to a web browser
        // to open a website with more information about the selected news item
        newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Find the current earthquake that was clicked on
                News currentNews = mAdapter.getItem(position);

                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri newsUri = Uri.parse(currentNews.getUrl());

                // Create a new intent to view the earthquake URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, newsUri);

                // Send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        });




        return view;
    }



    public void clickedButton(View view,String area)
    {


    }



}