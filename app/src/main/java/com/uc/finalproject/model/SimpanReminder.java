package com.uc.finalproject.model;

import android.os.Parcel;
import android.os.Parcelable;

public class SimpanReminder implements Parcelable {
    private String id;
    private String judulReminder;

    public SimpanReminder(String judulReminder) {
    }

    public SimpanReminder(String id, String judulReminder) {
        this.id = id;
        this.judulReminder = judulReminder;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJudulReminder() {
        return judulReminder;
    }

    public void setJudulReminder(String judulReminder) {
        this.judulReminder = judulReminder;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.judulReminder);
    }

    public SimpanReminder(Parcel in) {
        this.id = in.readString();
        this.judulReminder = in.readString();
    }

    public static final Parcelable.Creator<SimpanReminder> CREATOR = new Parcelable.Creator<SimpanReminder>() {
        @Override
        public SimpanReminder createFromParcel(Parcel source) {
            return new SimpanReminder(source);
        }

        @Override
        public SimpanReminder[] newArray(int size) {
            return new SimpanReminder[size];
        }
    };
}
