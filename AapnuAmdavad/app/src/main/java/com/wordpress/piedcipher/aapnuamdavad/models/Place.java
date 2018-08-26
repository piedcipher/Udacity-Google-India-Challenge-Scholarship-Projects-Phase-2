package com.wordpress.piedcipher.aapnuamdavad.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Place implements Parcelable {

    private String mPlaceName;
    private int mPlacePhoto;
    private String mPlaceDescription;
    private String mPlaceWikiTitle;

    public Place(String placeName, int placePhoto, String placeDescription, String placeWikiTitle) {
        mPlaceName = placeName;
        mPlacePhoto = placePhoto;
        mPlaceDescription = placeDescription;
        mPlaceWikiTitle = placeWikiTitle;
    }

    public String getPlaceName() {
        return mPlaceName;
    }

    public int getPlacePhoto() {
        return mPlacePhoto;
    }

    public String getPlaceDescription() {
        return mPlaceDescription;
    }

    public String getPlaceWikiTitle() {
        return mPlaceWikiTitle;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mPlaceName);
        dest.writeInt(this.mPlacePhoto);
        dest.writeString(this.mPlaceDescription);
        dest.writeString(this.mPlaceWikiTitle);
    }

    private Place(Parcel in) {
        this.mPlaceName = in.readString();
        this.mPlacePhoto = in.readInt();
        this.mPlaceDescription = in.readString();
        this.mPlaceWikiTitle = in.readString();
    }

    public static final Creator<Place> CREATOR = new Creator<Place>() {
        @Override
        public Place createFromParcel(Parcel source) {
            return new Place(source);
        }

        @Override
        public Place[] newArray(int size) {
            return new Place[size];
        }
    };
}