package com.uc.finalproject.model;

import android.os.Parcel;
import android.os.Parcelable;

public class SimpanReminder implements Parcelable {
    private String judulReminder;

    protected SimpanReminder(Parcel in) {
        judulReminder = in.readString();
    }

    public SimpanReminder(String judulReminder) {
        this.judulReminder = judulReminder;
    }

    public String getJudulReminder() {
        return judulReminder;
    }

    public static final Creator<SimpanReminder> CREATOR = new Creator<SimpanReminder>() {
        @Override
        public SimpanReminder createFromParcel(Parcel in) {
            return new SimpanReminder(in);
        }

        @Override
        public SimpanReminder[] newArray(int size) {
            return new SimpanReminder[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(judulReminder);
    }
}
