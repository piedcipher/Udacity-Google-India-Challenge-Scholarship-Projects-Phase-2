package com.wordpress.piedcipher.dhun.models;

import android.content.Context;

import com.wordpress.piedcipher.dhun.utils.DhunDataUtil;

public class Artist {
    private Context mContext;
    private String mArtistName;
    private int mArtistPhoto;

    public Artist(Context context, String artistName, int artistPhoto) {
        mContext = context;
        mArtistName = artistName;
        mArtistPhoto = artistPhoto;
    }

    public String getArtistName() {
        return mArtistName;
    }

    public int getArtistPhoto() {
        return mArtistPhoto;
    }

    public int getArtistSongCount(Artist artist) {
        return DhunDataUtil.getArtistSongCount(mContext, artist);
    }

    public int getArtistAlbumCount(Artist artist) {
        return DhunDataUtil.getArtistAlbumCount(mContext, artist);
    }
}