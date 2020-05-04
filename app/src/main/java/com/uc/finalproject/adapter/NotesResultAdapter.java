package com.uc.finalproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.uc.finalproject.R;
import com.uc.finalproject.model.SimpanNotes;

import java.util.ArrayList;

public class NotesResultAdapter extends RecyclerView.Adapter<NotesResultAdapter.NotesResultViewHolder> {
    private Context context;
    private ArrayList<SimpanNotes> listNotes = new ArrayList<>();

    public NotesResultAdapter(Context context) {
        this.context = context;
    }

    public void setListNotes(ArrayList<SimpanNotes>list){
        listNotes.clear();
        listNotes.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NotesResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_notes_result,parent,false);
        return new NotesResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesResultViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return listNotes.size();
    }

    public class NotesResultViewHolder extends RecyclerView.ViewHolder {
        EditText judul, isi;
        public NotesResultViewHolder(@NonNull View itemView) {
            super(itemView);
            judul = itemView.findViewById(R.id.judul_result);
            isi = itemView.findViewById(R.id.result_isi);
        }
    }
}
