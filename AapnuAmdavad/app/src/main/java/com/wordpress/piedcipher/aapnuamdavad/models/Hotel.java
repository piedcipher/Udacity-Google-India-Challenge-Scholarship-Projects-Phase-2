package com.wordpress.piedcipher.aapnuamdavad.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Hotel implements Parcelable {
    private String mHotelName;
    private int mHotelPhoto;
    private String mHotelReview;
    private String mHotelRating;
    private String mHotelDescription;
    private String mHotelContactNumber;
    private String mHotelURL;

    public Hotel(String hotelName, int hotelPhoto, String hotelRating, String hotelReview, String hotelDescription, String hotelContactNumber, String hotelURL) {
        mHotelName = hotelName;
        mHotelPhoto = hotelPhoto;
        mHotelRating = hotelRating;
        mHotelReview = hotelReview;
        mHotelDescription = hotelDescription;
        mHotelContactNumber = hotelContactNumber;
        mHotelURL = hotelURL;
    }

    public String getHotelName() {
        return mHotelName;
    }

    public int getHotelPhoto() {
        return mHotelPhoto;
    }

    public String getHotelRatings() {
        return mHotelRating;
    }

    public String getHotelReview() {
        return mHotelReview;
    }

    public String getHotelDescription() {
        return mHotelDescription;
    }

    public String getHotelContactNumber() {
        return mHotelContactNumber;
    }

    public String getHotelURL() {
        return mHotelURL;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mHotelName);
        dest.writeInt(this.mHotelPhoto);
        dest.writeString(this.mHotelReview);
        dest.writeString(this.mHotelRating);
        dest.writeString(this.mHotelDescription);
        dest.writeString(this.mHotelContactNumber);
        dest.writeString(this.mHotelURL);
    }

    private Hotel(Parcel in) {
        this.mHotelName = in.readString();
        this.mHotelPhoto = in.readInt();
        this.mHotelReview = in.readString();
        this.mHotelRating = in.readString();
        this.mHotelDescription = in.readString();
        this.mHotelContactNumber = in.readString();
        this.mHotelURL = in.readString();
    }

    public static final Creator<Hotel> CREATOR = new Creator<Hotel>() {
        @Override
        public Hotel createFromParcel(Parcel source) {
            return new Hotel(source);
        }

        @Override
        public Hotel[] newArray(int size) {
            return new Hotel[size];
        }
    };
}