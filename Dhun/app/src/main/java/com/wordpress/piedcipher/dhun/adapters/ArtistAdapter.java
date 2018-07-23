package com.wordpress.piedcipher.dhun.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wordpress.piedcipher.dhun.R;
import com.wordpress.piedcipher.dhun.models.Artist;

import java.util.ArrayList;

public class ArtistAdapter extends ArrayAdapter<Artist> {
    public ArtistAdapter(Context context, ArrayList<Artist> artists) {
        super(context, 0, artists);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View artistListItem = convertView;
        if (artistListItem == null) {
            artistListItem = LayoutInflater.from(getContext()).inflate(R.layout.layout_artist_list_item, parent, false);
        }

        Artist currentArtist = getItem(position);

        TextView artistNameTextView = artistListItem.findViewById(R.id.artist_name);
        ImageView artistPhotoImageView = artistListItem.findViewById(R.id.artist_photo);
        TextView artistAlbumCountTextView = artistListItem.findViewById(R.id.artist_album_count);
        TextView artistSongCountTextView = artistListItem.findViewById(R.id.artist_song_count);

        if (currentArtist != null) {
            artistPhotoImageView.setImageResource(currentArtist.getArtistPhoto());
            artistNameTextView.setText(currentArtist.getArtistName());
            String albumCount = currentArtist.getArtistAlbumCount(currentArtist) == 1 ? currentArtist.getArtistAlbumCount(currentArtist) + " album" : currentArtist.getArtistAlbumCount(currentArtist) + " albums";
            artistAlbumCountTextView.setText(albumCount);
            String songCount = currentArtist.getArtistSongCount(currentArtist) == 1 ? currentArtist.getArtistSongCount(currentArtist) + " song" : currentArtist.getArtistSongCount(currentArtist) + " songs";
            artistSongCountTextView.setText(songCount);
        }

        return artistListItem;
    }
}