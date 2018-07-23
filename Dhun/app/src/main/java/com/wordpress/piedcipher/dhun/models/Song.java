package com.wordpress.piedcipher.dhun.models;

public class Song {
    private String mSongTitle;
    private String mSongArtist;
    private String mSongDuration;
    private int mSongAlbumArt;

    public Song(String songTitle, String songArtist, String songDuration, int songAlbumArt) {
        mSongTitle = songTitle;
        mSongArtist = songArtist;
        mSongDuration = songDuration;
        mSongAlbumArt = songAlbumArt;
    }

    public String getTitle() {
        return mSongTitle;
    }

    public String getArtist() {
        return mSongArtist;
    }

    public String getDuration() {
        return mSongDuration;
    }

    public int getAlbumArt() {
        return mSongAlbumArt;
    }
}