package com.uc.finalproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.uc.finalproject.R;
import com.uc.finalproject.model.SimpanNotes;

import java.util.ArrayList;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder>{
    private Context context;
    private ArrayList<SimpanNotes>listNotes = new ArrayList<>();

    public NotesAdapter(Context context) {
        this.context = context;
    }

    public void setListNotes(ArrayList<SimpanNotes>list){
        listNotes.clear();
        listNotes.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_notes_list,parent,false);
        return new NotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        SimpanNotes simpanNotes = listNotes.get(position);
        holder.id.setText(simpanNotes.getId());
        holder.judul.setText(simpanNotes.getJudul());
    }

    @Override
    public int getItemCount() {
        return listNotes.size();
    }

    public class NotesViewHolder extends RecyclerView.ViewHolder {
        TextView id, judul;
        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id_notes);
            judul = itemView.findViewById(R.id.judul_notes);
        }
    }
}
