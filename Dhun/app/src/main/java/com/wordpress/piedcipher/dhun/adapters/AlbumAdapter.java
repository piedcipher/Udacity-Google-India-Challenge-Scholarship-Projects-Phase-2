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
import com.wordpress.piedcipher.dhun.models.Album;

import java.util.ArrayList;

public class AlbumAdapter extends ArrayAdapter<Album> {
    public AlbumAdapter(Context context, ArrayList<Album> albums) {
        super(context, 0, albums);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View albumGridItem = convertView;
        if (albumGridItem == null) {
            albumGridItem = LayoutInflater.from(getContext()).inflate(R.layout.layout_album_grid_item, parent, false);
        }

        Album currentAlbum = getItem(position);

        TextView albumNameTextView = albumGridItem.findViewById(R.id.album_name);
        TextView albumArtistTextView = albumGridItem.findViewById(R.id.album_artist);
        ImageView albumArtImageView = albumGridItem.findViewById(R.id.album_art);

        if (currentAlbum != null) {
            albumArtImageView.setImageResource(currentAlbum.getAlbumArt());
            albumArtistTextView.setText(currentAlbum.getAlbumArtist());
            albumNameTextView.setText(currentAlbum.getAlbumName());
        }

        return albumGridItem;
    }
}