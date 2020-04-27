package com.uc.finalproject.ui.reminder;

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
    private ArrayList<SimpanReminder>listReminder;

    public ArrayList<SimpanReminder> getListReminder() {
        return listReminder;
    }

    public void setListReminder(ArrayList<SimpanReminder> listReminder) {
        this.listReminder = listReminder;
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
        final SimpanReminder s = getListReminder().get(position);
        holder.judulReminder.setText(s.getJudulReminder());
    }

    @Override
    public int getItemCount() {
        return getListReminder().size();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder {
        TextView judulReminder;
        public CardViewViewHolder(@NonNull View itemView) {
            super(itemView);
            judulReminder = itemView.findViewById(R.id.reminder_text);
        }
    }
}
