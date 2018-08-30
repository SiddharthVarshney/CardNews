package com.example.siddharth.cardnews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

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
        String date;
        String time;

        if (originalTime.contains("T")) {
            String[] parts = originalTime.split("T");
            date = parts[0];
            String[] parts1 = parts[1].split("Z");
            time = parts1[0];
        } else {
            time = getContext().getString(R.string.not_avilalble);
            date = time;
        }

        TextView dateView = listItemView.findViewById(R.id.date);
        dateView.setText(date);

        TextView timeView = listItemView.findViewById(R.id.time);
        timeView.setText(time);


        return listItemView;
    }


}
