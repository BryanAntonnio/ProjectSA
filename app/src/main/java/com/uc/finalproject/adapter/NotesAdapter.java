package com.uc.finalproject.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.uc.finalproject.model.SimpanNotes;

import java.util.ArrayList;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.CardViewViewHolder> {
    private Context context;
    private ArrayList<SimpanNotes>listNotes;

    public ArrayList<SimpanNotes> getListNotes(){
        return listNotes;
    }

    public void setListNotes(ArrayList<SimpanNotes> listNotes) {
        this.listNotes = listNotes;
    }

    public NotesAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public NotesAdapter.CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.CardViewViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder {

        public CardViewViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
