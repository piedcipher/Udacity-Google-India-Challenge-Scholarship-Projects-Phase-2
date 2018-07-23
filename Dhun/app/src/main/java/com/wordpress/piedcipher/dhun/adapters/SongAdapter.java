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
import com.wordpress.piedcipher.dhun.models.Song;
import com.wordpress.piedcipher.dhun.utils.DhunDataUtil;

import java.util.ArrayList;

public class SongAdapter extends ArrayAdapter<Song> {
    private static boolean mIsArtistSpecific;

    public SongAdapter(Context context, ArrayList<Song> songs) {
        super(context, 0, songs);
    }

    public static void setIsArtistSpecific(boolean isArtistSpecific) {
        mIsArtistSpecific = isArtistSpecific;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View songListItem = convertView;
        if (songListItem == null) {
            songListItem = LayoutInflater.from(getContext()).inflate(R.layout.layout_song_list_item, parent, false);
        }

        Song currentSong = getItem(position);

        ArrayList<Album> albumArrayList = new DhunDataUtil().getPopulatedAlbumsArrayList(getContext());

        TextView songTitleTextView = songListItem.findViewById(R.id.song_title);
        TextView songArtistTextView = songListItem.findViewById(R.id.song_artist);
        ImageView songAlbumArtImageView = songListItem.findViewById(R.id.song_album_art);
        TextView songDurationTextView = songListItem.findViewById(R.id.song_duration);

        String albumName = "";

        if (currentSong != null) {
            songAlbumArtImageView.setImageResource(currentSong.getAlbumArt());
            songTitleTextView.setText(currentSong.getTitle());
            if(!SongAdapter.mIsArtistSpecific) {
                songArtistTextView.setText(currentSong.getArtist());
            } else {
                for(int i = 0; i < albumArrayList.size(); i++) {
                    if(albumArrayList.get(i).getAlbumArt() == (currentSong.getAlbumArt())) {
                        albumName = albumArrayList.get(i).getAlbumName();
                    }
                }

                songArtistTextView.setText(albumName);
            }
            songDurationTextView.setText(currentSong.getDuration());
        }

        return songListItem;
    }
}