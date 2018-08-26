package com.wordpress.piedcipher.aapnuamdavad.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.wordpress.piedcipher.aapnuamdavad.R;
import com.wordpress.piedcipher.aapnuamdavad.activities.RestaurantActivity;
import com.wordpress.piedcipher.aapnuamdavad.adapters.RestaurantsListAdapter;
import com.wordpress.piedcipher.aapnuamdavad.models.Restaurant;
import com.wordpress.piedcipher.aapnuamdavad.utils.AppUtil;

import java.util.ArrayList;

public class RestaurantsFragment extends Fragment {

    public RestaurantsFragment() {

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_restaurants, container, false);

        if (getActivity() != null) {
            ListView restaurantsListView = fragmentView.findViewById(R.id.restaurants_list_view);
            final ArrayList<Restaurant> restaurantsArrayList = AppUtil.getPopulatedRestaurantsArrayList(getActivity());
            restaurantsListView.setAdapter(new RestaurantsListAdapter(getActivity(), restaurantsArrayList));
            restaurantsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    startActivity(new Intent(
                            getActivity(), RestaurantActivity.class)
                            .putExtra("Restaurant", restaurantsArrayList.get(i)));
                }
            });
        }

        return fragmentView;
    }
}
