package com.wordpress.piedcipher.aapnuamdavad.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Restaurant implements Parcelable {

    private String mRestaurantName;
    private int mRestaurantPhoto;
    private String mRestaurantDescription;
    private String mRestaurantReview;
    private String mRestaurantTimings;
    private String mRestaurantContactNumber;
    private String mRestaurantURL;

    public Restaurant(String restaurantName, int restaurantPhoto, String restaurantDescription, String restaurantReview, String restaurantTimings, String restaurantContactNumber, String restaurantURL) {
        mRestaurantName = restaurantName;
        mRestaurantPhoto = restaurantPhoto;
        mRestaurantDescription = restaurantDescription;
        mRestaurantReview = restaurantReview;
        mRestaurantTimings = restaurantTimings;
        mRestaurantContactNumber = restaurantContactNumber;
        mRestaurantURL = restaurantURL;
    }

    public String getRestaurantName() {
        return mRestaurantName;
    }

    public int getRestaurantPhoto() {
        return mRestaurantPhoto;
    }

    public String getRestaurantDescription() {
        return mRestaurantDescription;
    }

    public String getRestaurantReview() {
        return mRestaurantReview;
    }

    public String getRestaurantTimings() {
        return mRestaurantTimings;
    }

    public String getRestaurantContactNumber() {
        return mRestaurantContactNumber;
    }

    public String getRestaurantURL() {
        return mRestaurantURL;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mRestaurantName);
        dest.writeInt(this.mRestaurantPhoto);
        dest.writeString(this.mRestaurantDescription);
        dest.writeString(this.mRestaurantReview);
        dest.writeString(this.mRestaurantTimings);
        dest.writeString(this.mRestaurantContactNumber);
        dest.writeString(this.mRestaurantURL);
    }

    private Restaurant(Parcel in) {
        this.mRestaurantName = in.readString();
        this.mRestaurantPhoto = in.readInt();
        this.mRestaurantDescription = in.readString();
        this.mRestaurantReview = in.readString();
        this.mRestaurantTimings = in.readString();
        this.mRestaurantContactNumber = in.readString();
        this.mRestaurantURL = in.readString();
    }

    public static final Creator<Restaurant> CREATOR = new Creator<Restaurant>() {
        @Override
        public Restaurant createFromParcel(Parcel source) {
            return new Restaurant(source);
        }

        @Override
        public Restaurant[] newArray(int size) {
            return new Restaurant[size];
        }
    };
}