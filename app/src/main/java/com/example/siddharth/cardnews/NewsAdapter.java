package com.example.siddharth.cardnews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class NewsAdapter extends ArrayAdapter<NewsData> {

    public NewsAdapter(Context context, List<NewsData> news) {
        super(context, 0, news);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.news_list, parent, false);
        }


        NewsData currentNews = getItem(position);

        TextView headlineView = listItemView.findViewById(R.id.headline);
        headlineView.setText(currentNews.getmHeadline());

        String originalTime = currentNews.getmDate_time();


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Date date = null;
        try {
            date = sdf.parse(originalTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String newDate = new SimpleDateFormat("dd-MM-yyyy").format(date);
        TextView dateView = listItemView.findViewById(R.id.date);
        dateView.setText(newDate);


        String imageUri=currentNews.getmImageUrl();
        ImageView newsImage = listItemView.findViewById(R.id.news_image);
        Picasso.with(getContext()).load(imageUri).into(newsImage);


        return listItemView;
    }


}
