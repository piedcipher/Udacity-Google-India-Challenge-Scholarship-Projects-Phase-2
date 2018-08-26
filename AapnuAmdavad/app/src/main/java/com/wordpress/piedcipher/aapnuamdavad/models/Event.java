package com.wordpress.piedcipher.aapnuamdavad.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Event implements Parcelable {

    private String mEventTitle;
    private String mEventDescription;
    private String mEventDate;
    private String mEventVenue;
    private int mEventPhoto;
    private String mEventURL;

    public Event(String eventTitle, String eventDescription, String eventDate, String eventVenue, int eventPhoto, String eventURL) {
        mEventTitle = eventTitle;
        mEventDescription = eventDescription;
        mEventDate = eventDate;
        mEventVenue = eventVenue;
        mEventPhoto = eventPhoto;
        mEventURL = eventURL;
    }

    public String getEventTitle() {
        return mEventTitle;
    }

    public String getEventDescription() {
        return mEventDescription;
    }

    public String getEventDate() {
        return mEventDate;
    }

    public String getEventVenue() {
        return mEventVenue;
    }

    public int getEventPhoto() {
        return mEventPhoto;
    }

    public String getEventURL() {
        return mEventURL;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mEventTitle);
        dest.writeString(this.mEventDescription);
        dest.writeString(this.mEventDate);
        dest.writeString(this.mEventVenue);
        dest.writeInt(this.mEventPhoto);
        dest.writeString(this.mEventURL);
    }

    private Event(Parcel in) {
        this.mEventTitle = in.readString();
        this.mEventDescription = in.readString();
        this.mEventDate = in.readString();
        this.mEventVenue = in.readString();
        this.mEventPhoto = in.readInt();
        this.mEventURL = in.readString();
    }

    public static final Creator<Event> CREATOR = new Creator<Event>() {
        @Override
        public Event createFromParcel(Parcel source) {
            return new Event(source);
        }

        @Override
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };
}