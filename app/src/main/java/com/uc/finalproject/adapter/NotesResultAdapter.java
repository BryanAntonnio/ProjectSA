package com.uc.finalproject.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NotesResultAdapter extends RecyclerView.Adapter<NotesResultAdapter.ResultViewHolder> {
    @NonNull
    @Override
    public ResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ResultViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ResultViewHolder extends RecyclerView.ViewHolder {
        public ResultViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
