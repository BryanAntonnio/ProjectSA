package com.uc.finalproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.uc.finalproject.R;
import com.uc.finalproject.model.SimpanReminder;

import java.util.ArrayList;

public class ReminderAdapter extends RecyclerView.Adapter<ReminderAdapter.CardViewViewHolder> {
    private Context context;
    private ArrayList<SimpanReminder>listReminder = new ArrayList<>();

    public void setListReminder(ArrayList<SimpanReminder> list) {
        listReminder.clear();
        listReminder.addAll(list);
        notifyDataSetChanged();
    }

    public ReminderAdapter(Context context){
        this.context=context;
    }

    @NonNull
    @Override
    public ReminderAdapter.CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_reminder_result, viewGroup, false);
        return new ReminderAdapter.CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReminderAdapter.CardViewViewHolder holder, int position) {
        final SimpanReminder s = listReminder.get(position);
        holder.id.setText(s.getId());
        holder.judulReminder.setText(s.getJudulReminder());
    }

    @Override
    public int getItemCount() {
        return listReminder.size();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder {
        TextView id, judulReminder;
        public CardViewViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id_reminder);
            judulReminder = itemView.findViewById(R.id.reminder_text);
        }
    }
}
