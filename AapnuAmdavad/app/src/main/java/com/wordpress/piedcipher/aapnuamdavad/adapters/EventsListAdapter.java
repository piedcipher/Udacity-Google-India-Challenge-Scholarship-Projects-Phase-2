package com.wordpress.piedcipher.aapnuamdavad.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.wordpress.piedcipher.aapnuamdavad.R;
import com.wordpress.piedcipher.aapnuamdavad.models.Event;

import java.util.ArrayList;

public class EventsListAdapter extends ArrayAdapter<Event> {

    private Context mContext;

    public EventsListAdapter(Context context, ArrayList<Event> events) {
        super(context, 0, events);
        mContext = context;
    }

    class CustomViewHolder {
        ImageView mEventPhotoImageView;

        CustomViewHolder(ImageView eventPhotoImageView) {
            mEventPhotoImageView = eventPhotoImageView;
        }
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listViewItem = convertView;
        CustomViewHolder customViewHolder;
        ImageView eventPhotoImageView;

        if (listViewItem == null) {
            listViewItem = LayoutInflater.from(getContext()).inflate(R.layout.layout_event_list_item, parent, false);
            eventPhotoImageView = listViewItem.findViewById(R.id.event_photo_image_view);
            customViewHolder = new CustomViewHolder(eventPhotoImageView);
            listViewItem.setTag(customViewHolder);
        }

        Event currentEvent = getItem(position);
        customViewHolder = (CustomViewHolder) listViewItem.getTag();

        if (currentEvent != null) {
            Glide.with(mContext).load(currentEvent.getEventPhoto()).into(customViewHolder.mEventPhotoImageView);
        }

        return listViewItem;
    }
}