package com.uc.finalproject.model;

import android.os.Parcel;
import android.os.Parcelable;

public class SimpanNotes implements Parcelable {
    private String judul, isi;

    protected SimpanNotes(Parcel in) {
        judul = in.readString();
        isi = in.readString();
    }

    public SimpanNotes(String judul, String isi) {
        this.judul = judul;
        this.isi = isi;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getIsi() {
        return isi;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }

    public static final Creator<SimpanNotes> CREATOR = new Creator<SimpanNotes>() {
        @Override
        public SimpanNotes createFromParcel(Parcel in) {
            return new SimpanNotes(in);
        }

        @Override
        public SimpanNotes[] newArray(int size) {
            return new SimpanNotes[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(judul);
        dest.writeString(isi);
    }
}
