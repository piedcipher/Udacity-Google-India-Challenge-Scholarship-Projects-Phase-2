package com.wordpress.piedcipher.dhun.models;

public class Album {
    private String mAlbumName;
    private String mAlbumArtist;
    private int mAlbumArt;

    public Album(String albumName, String albumArtist, int albumArt) {
        mAlbumName = albumName;
        mAlbumArtist = albumArtist;
        mAlbumArt = albumArt;
    }

    public String getAlbumName() {
        return mAlbumName;
    }

    public String getAlbumArtist() {
        return mAlbumArtist;
    }

    public int getAlbumArt() {
        return mAlbumArt;
    }
}