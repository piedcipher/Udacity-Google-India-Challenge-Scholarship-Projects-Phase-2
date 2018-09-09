package com.wordpress.piedcipher.samachar.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wordpress.piedcipher.samachar.R;
import com.wordpress.piedcipher.samachar.models.News;

import java.util.List;

public class NewsAdapter extends ArrayAdapter<News> {

    private int listItemLayoutResource;

    public NewsAdapter(Context context, List<News> newsList, int listItemLayoutResource) {
        super(context, 0, newsList);
        this.listItemLayoutResource = listItemLayoutResource;
    }

    private class CustomViewHolder {
        TextView headlineTextView;
        ImageView thumbnailImageView;
        TextView sectionTextView;
        TextView timestampTextView;
        TextView authorTextView;

        CustomViewHolder(TextView headlineTextView, ImageView thumbnailImageView, TextView sectionTextView, TextView timestampTextView, TextView authorTextView) {
            this.headlineTextView = headlineTextView;
            this.thumbnailImageView = thumbnailImageView;
            this.sectionTextView = sectionTextView;
            this.timestampTextView = timestampTextView;
            this.authorTextView = authorTextView;
        }
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        News currentNewsItem = getItem(position);
        TextView headlineTextView;
        ImageView thumnailImageView;
        TextView sectionTextView;
        TextView timestampTextView;
        TextView authorTextView;
        CustomViewHolder customViewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(listItemLayoutResource, parent, false);
            headlineTextView = convertView.findViewById(R.id.headline);
            thumnailImageView = convertView.findViewById(R.id.thumbnail);
            sectionTextView = convertView.findViewById(R.id.section);
            timestampTextView = convertView.findViewById(R.id.timestamp);
            authorTextView = convertView.findViewById(R.id.author);

            customViewHolder = new CustomViewHolder(headlineTextView, thumnailImageView, sectionTextView, timestampTextView, authorTextView);
            convertView.setTag(customViewHolder);
        }

        if (currentNewsItem != null) {
            customViewHolder = (CustomViewHolder) convertView.getTag();
            Glide.with(getContext()).load(Uri.parse(currentNewsItem.getThumbnail())).into(customViewHolder.thumbnailImageView);
            customViewHolder.headlineTextView.setText(currentNewsItem.getHeadline());
            customViewHolder.sectionTextView.setText(currentNewsItem.getSection());
            customViewHolder.timestampTextView.setText(currentNewsItem.getTimestamp());
            customViewHolder.authorTextView.setText(currentNewsItem.getAuthor());
        }

        return convertView;
    }
}