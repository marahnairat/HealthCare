package com.example.healthcare;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class NewsAdapter extends ArrayAdapter<News> {

    Date date = new Date();

    public NewsAdapter(Context context, List<News> newsList) {
        super(context, 0, newsList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.article, parent, false);
        }

        News currentNews = getItem(position);

        ImageView imageView = listItemView.findViewById(R.id.thumbnail);
        String formattedImage = currentNews.getImageUrl();
        URL url = null;
        try {
            url = new URL(formattedImage);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Bitmap bmp;
        if (url != null) {
            try {
                bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                imageView.setImageBitmap(bmp);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        TextView titleView = listItemView.findViewById(R.id.title);
        String formattedTitle = currentNews.getTitle();
        titleView.setText(formattedTitle);

        return listItemView;
    }
}