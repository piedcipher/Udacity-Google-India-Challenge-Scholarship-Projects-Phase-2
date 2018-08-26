package com.wordpress.piedcipher.aapnuamdavad.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wordpress.piedcipher.aapnuamdavad.R;
import com.wordpress.piedcipher.aapnuamdavad.models.Hotel;

import java.util.ArrayList;

public class HotelsListAdapter extends ArrayAdapter<Hotel> {

    private Context mContext;

    public HotelsListAdapter(Context context, ArrayList<Hotel> hotels) {
        super(context, 0, hotels);
        mContext = context;
    }

    class CustomViewHolder {
        ImageView mHotelPhotoImageView;
        TextView mHotelNameTextView;

        CustomViewHolder(ImageView hotelPhotoImageView, TextView hotelNameTextView) {
            mHotelPhotoImageView = hotelPhotoImageView;
            mHotelNameTextView = hotelNameTextView;
        }
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listViewItem = convertView;
        CustomViewHolder customViewHolder;
        ImageView hotelPhotoImageView;
        TextView hotelNameTextView;

        if (listViewItem == null) {
            listViewItem = LayoutInflater.from(getContext()).inflate(R.layout.layout_place_restaurant_hotel_list_item, parent, false);
            hotelPhotoImageView = listViewItem.findViewById(R.id.place_restaurant_hotel_photo_image_view);
            hotelNameTextView = listViewItem.findViewById(R.id.place_restaurant_hotel_name_text_view);
            customViewHolder = new CustomViewHolder(hotelPhotoImageView, hotelNameTextView);
            listViewItem.setTag(customViewHolder);
        }

        Hotel currentHotel = getItem(position);
        customViewHolder = (CustomViewHolder) listViewItem.getTag();

        if (currentHotel != null) {
            Glide.with(mContext).load(currentHotel.getHotelPhoto()).into(customViewHolder.mHotelPhotoImageView);
            customViewHolder.mHotelNameTextView.setText(currentHotel.getHotelName());
        }

        return listViewItem;
    }
}