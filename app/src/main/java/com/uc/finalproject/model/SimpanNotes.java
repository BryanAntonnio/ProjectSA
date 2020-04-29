package com.uc.finalproject.model;

import android.os.Parcel;
import android.os.Parcelable;

public class SimpanNotes implements Parcelable {
    private String id;
    private String judul;
    private String isi;

    public SimpanNotes(){

    }

    public SimpanNotes(String id, String judul, String isi) {
        this.id = id;
        this.judul = judul;
        this.isi = isi;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.judul);
        dest.writeString(this.isi);
    }

    protected SimpanNotes(Parcel in) {
        this.id = in.readString();
        this.judul = in.readString();
        this.isi = in.readString();
    }

    public static final Parcelable.Creator<SimpanNotes> CREATOR = new Parcelable.Creator<SimpanNotes>() {
        @Override
        public SimpanNotes createFromParcel(Parcel source) {
            return new SimpanNotes(source);
        }

        @Override
        public SimpanNotes[] newArray(int size) {
            return new SimpanNotes[size];
        }
    };
}
