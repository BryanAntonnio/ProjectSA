package com.uc.finalproject.model;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    private String username, pass, cpass;

    protected User(Parcel in) {
        username = in.readString();
        pass = in.readString();
        cpass = in.readString();
    }

    public User(String username, String pass, String cpass) {
        this.username = username;
        this.pass = pass;
        this.cpass = cpass;
    }

    public String getUsername() {
        return username;
    }

    public String getPass() {
        return pass;
    }

    public String getCpass() {
        return cpass;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(pass);
        dest.writeString(cpass);
    }
}
