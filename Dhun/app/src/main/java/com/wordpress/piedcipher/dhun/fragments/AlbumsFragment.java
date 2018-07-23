package com.wordpress.piedcipher.dhun.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.wordpress.piedcipher.dhun.R;
import com.wordpress.piedcipher.dhun.activities.DetailsActivity;
import com.wordpress.piedcipher.dhun.adapters.AlbumAdapter;
import com.wordpress.piedcipher.dhun.models.Album;
import com.wordpress.piedcipher.dhun.utils.DhunDataUtil;

import java.util.ArrayList;

public class AlbumsFragment extends Fragment {

    public AlbumsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View albumGridItem = inflater.inflate(R.layout.fragment_albums, container, false);
        GridView albumsGridView = albumGridItem.findViewById(R.id.albums_grid_view);

        final ArrayList<Album> albumsArrayList = new DhunDataUtil().getPopulatedAlbumsArrayList(getActivity());

        AlbumAdapter albumAdapter = new AlbumAdapter(getActivity(), albumsArrayList);
        albumsGridView.setAdapter(albumAdapter);

        albumsGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(
                        getActivity(), DetailsActivity.class)
                        .putExtra("AlbumArt", albumsArrayList.get(i).getAlbumArt())
                        .putExtra("AlbumArtist", albumsArrayList.get(i).getAlbumArtist())
                        .putExtra("AlbumName", albumsArrayList.get(i).getAlbumName())
                );
            }
        });

        return albumGridItem;
    }
}