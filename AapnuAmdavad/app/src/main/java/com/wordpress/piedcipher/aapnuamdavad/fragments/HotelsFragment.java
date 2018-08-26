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
import com.wordpress.piedcipher.aapnuamdavad.activities.HotelActivity;
import com.wordpress.piedcipher.aapnuamdavad.adapters.HotelsListAdapter;
import com.wordpress.piedcipher.aapnuamdavad.models.Hotel;
import com.wordpress.piedcipher.aapnuamdavad.utils.AppUtil;

import java.util.ArrayList;

public class HotelsFragment extends Fragment {

    public HotelsFragment() {

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_hotels, container, false);
        if (getActivity() != null) {
            ListView hotelsListView = fragmentView.findViewById(R.id.hotels_list_view);
            final ArrayList<Hotel> hotelsArrayList = AppUtil.getPopulatedHotelsArrayList(getActivity());
            hotelsListView.setAdapter(new HotelsListAdapter(getActivity(), hotelsArrayList));
            hotelsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    startActivity(new Intent(
                            getActivity(), HotelActivity.class)
                            .putExtra("Hotel", hotelsArrayList.get(i)));
                }
            });
        }

        return fragmentView;
    }
}