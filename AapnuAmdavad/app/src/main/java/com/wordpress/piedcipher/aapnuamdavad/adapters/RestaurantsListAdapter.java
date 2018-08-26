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
import com.wordpress.piedcipher.aapnuamdavad.models.Restaurant;

import java.util.ArrayList;

public class RestaurantsListAdapter extends ArrayAdapter<Restaurant> {

    private Context mContext;

    public RestaurantsListAdapter(Context context, ArrayList<Restaurant> restaurants) {
        super(context, 0, restaurants);
        mContext = context;
    }

    class CustomViewHolder {
        ImageView mRestaurantPhotoImageView;
        TextView mRestaurantNameTextView;

        CustomViewHolder(ImageView restaurantPhotoImageView, TextView restaurantNameTextView) {
            mRestaurantPhotoImageView = restaurantPhotoImageView;
            mRestaurantNameTextView = restaurantNameTextView;
        }
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listViewItem = convertView;
        CustomViewHolder customViewHolder;
        ImageView restaurantPhotoImageView;
        TextView restaurantNameTextView;

        if (listViewItem == null) {
            listViewItem = LayoutInflater.from(getContext()).inflate(R.layout.layout_place_restaurant_hotel_list_item, parent, false);
            restaurantPhotoImageView = listViewItem.findViewById(R.id.place_restaurant_hotel_photo_image_view);
            restaurantNameTextView = listViewItem.findViewById(R.id.place_restaurant_hotel_name_text_view);
            customViewHolder = new CustomViewHolder(restaurantPhotoImageView, restaurantNameTextView);
            listViewItem.setTag(customViewHolder);
        }

        Restaurant currentRestaurant = getItem(position);
        customViewHolder = (CustomViewHolder) listViewItem.getTag();

        if (currentRestaurant != null) {
            Glide.with(mContext).load(currentRestaurant.getRestaurantPhoto()).into(customViewHolder.mRestaurantPhotoImageView);
            customViewHolder.mRestaurantNameTextView.setText(currentRestaurant.getRestaurantName());
        }

        return listViewItem;
    }
}