package com.wordpress.piedcipher.dhun.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.wordpress.piedcipher.dhun.R;
import com.wordpress.piedcipher.dhun.activities.DetailsActivity;
import com.wordpress.piedcipher.dhun.adapters.ArtistAdapter;
import com.wordpress.piedcipher.dhun.models.Artist;
import com.wordpress.piedcipher.dhun.utils.DhunDataUtil;

import java.util.ArrayList;

public class ArtistsFragment extends Fragment {

    public ArtistsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View artistGridItem = inflater.inflate(R.layout.fragment_artists, container, false);
        ListView artistsListView = artistGridItem.findViewById(R.id.artists_list_view);

        final ArrayList<Artist> artistsArrayList = new DhunDataUtil().getPopulatedArtistArrayList(getActivity());

        ArtistAdapter artistAdapter = new ArtistAdapter(getActivity(), artistsArrayList);
        artistsListView.setAdapter(artistAdapter);

        artistsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(
                        getActivity(), DetailsActivity.class)
                        .putExtra("ArtistName", artistsArrayList.get(i).getArtistName())
                );
            }
        });

        return artistGridItem;
    }
}