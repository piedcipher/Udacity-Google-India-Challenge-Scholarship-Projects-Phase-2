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
import com.wordpress.piedcipher.aapnuamdavad.models.Place;

import java.util.ArrayList;

public class PlacesListAdapter extends ArrayAdapter<Place> {

    private Context mContext;

    public PlacesListAdapter(Context context, ArrayList<Place> places) {
        super(context, 0, places);
        mContext = context;
    }

    class CustomViewHolder {
        TextView mPlaceNameTextView;
        ImageView mPlacePhotoImageView;

        CustomViewHolder(TextView placeNameTextView, ImageView placePhotoImageView) {
            mPlaceNameTextView = placeNameTextView;
            mPlacePhotoImageView = placePhotoImageView;
        }
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listViewItem = convertView;
        CustomViewHolder customViewHolder;
        TextView placeNameTextView;
        ImageView placePhotoImageView;

        if (listViewItem == null) {
            listViewItem = LayoutInflater.from(getContext()).inflate(R.layout.layout_place_restaurant_hotel_list_item, parent, false);
            placeNameTextView = listViewItem.findViewById(R.id.place_restaurant_hotel_name_text_view);
            placePhotoImageView = listViewItem.findViewById(R.id.place_restaurant_hotel_photo_image_view);
            customViewHolder = new CustomViewHolder(placeNameTextView, placePhotoImageView);
            listViewItem.setTag(customViewHolder);
        }

        Place currentPlace = getItem(position);
        customViewHolder = (CustomViewHolder) listViewItem.getTag();

        if (currentPlace != null) {
            customViewHolder.mPlaceNameTextView.setText(currentPlace.getPlaceName());
            Glide.with(mContext).load(currentPlace.getPlacePhoto()).into(customViewHolder.mPlacePhotoImageView);
        }

        return listViewItem;
    }
}