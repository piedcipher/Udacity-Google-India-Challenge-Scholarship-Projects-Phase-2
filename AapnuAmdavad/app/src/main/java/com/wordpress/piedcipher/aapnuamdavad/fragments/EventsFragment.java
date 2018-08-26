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
import com.wordpress.piedcipher.aapnuamdavad.activities.EventActivity;
import com.wordpress.piedcipher.aapnuamdavad.adapters.EventsListAdapter;
import com.wordpress.piedcipher.aapnuamdavad.models.Event;
import com.wordpress.piedcipher.aapnuamdavad.utils.AppUtil;

import java.util.ArrayList;

public class EventsFragment extends Fragment {

    public EventsFragment() {

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_events, container, false);

        if (getActivity() != null) {
            final ArrayList<Event> eventsArrayList = AppUtil.getPopulatedEventsArrayList(getActivity());
            ListView eventsListView = fragmentView.findViewById(R.id.events_list_view);
            eventsListView.setAdapter(new EventsListAdapter(getActivity(), eventsArrayList));
            eventsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    startActivity(new Intent(
                            getActivity(), EventActivity.class)
                            .putExtra("Event", eventsArrayList.get(i)));
                }
            });
        }

        return fragmentView;
    }
}