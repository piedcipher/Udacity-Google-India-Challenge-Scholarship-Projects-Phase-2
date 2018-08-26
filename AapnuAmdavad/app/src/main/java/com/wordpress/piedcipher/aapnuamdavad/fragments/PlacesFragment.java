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
import com.wordpress.piedcipher.aapnuamdavad.activities.PlaceActivity;
import com.wordpress.piedcipher.aapnuamdavad.adapters.PlacesListAdapter;
import com.wordpress.piedcipher.aapnuamdavad.models.Place;
import com.wordpress.piedcipher.aapnuamdavad.utils.AppUtil;

import java.util.ArrayList;

public class PlacesFragment extends Fragment {

    public PlacesFragment() {

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_places, container, false);

        if (getActivity() != null) {
            final ArrayList<Place> placeArrayList = AppUtil.getPopulatedPlacesArrayList(getActivity());
            ListView placesListView = fragmentView.findViewById(R.id.places_list_view);
            placesListView.setAdapter(new PlacesListAdapter(getActivity(), placeArrayList));
            placesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    startActivity(new Intent(
                            getActivity(), PlaceActivity.class)
                            .putExtra("Place", placeArrayList.get(i)));
                }
            });
        }

        return fragmentView;
    }
}