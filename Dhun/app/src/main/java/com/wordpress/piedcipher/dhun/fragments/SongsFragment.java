package com.wordpress.piedcipher.dhun.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.wordpress.piedcipher.dhun.R;
import com.wordpress.piedcipher.dhun.activities.FavouritesActivity;
import com.wordpress.piedcipher.dhun.activities.MainActivity;
import com.wordpress.piedcipher.dhun.adapters.SongAdapter;
import com.wordpress.piedcipher.dhun.models.Song;
import com.wordpress.piedcipher.dhun.utils.DhunDataUtil;

import java.util.ArrayList;

public class SongsFragment extends Fragment {

    public SongsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View songListItem = inflater.inflate(R.layout.fragment_songs, container, false);
        ListView songsListView = songListItem.findViewById(R.id.songs_list_view);
        Button favoriteSongsButton = songListItem.findViewById(R.id.favorite_songs_button);

        final ArrayList<Song> songsArrayList = new DhunDataUtil().getPopulatedSongsArrayList(getActivity());

        SongAdapter songAdapter = new SongAdapter(getActivity(), songsArrayList);
        SongAdapter.setIsArtistSpecific(false);
        songsListView.setAdapter(songAdapter);

        songsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DhunDataUtil.setNowPlayingSongData(
                        songsArrayList.get(i).getTitle(),
                        songsArrayList.get(i).getArtist(),
                        songsArrayList.get(i).getAlbumArt(),
                        songsArrayList.get(i).getDuration(),
                        true,
                        false);

                if (getActivity() instanceof MainActivity) {
                    ((MainActivity) getActivity()).updateNowPlayingUI();
                }

                Toast.makeText(getActivity(),
                        "Playing " + DhunDataUtil.getNowPlayingSongTitle(),
                        Toast.LENGTH_SHORT).show();
            }
        });

        favoriteSongsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), FavouritesActivity.class));
            }
        });

        return songListItem;
    }
}