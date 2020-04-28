package com.uc.finalproject.ui.reminder;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.uc.finalproject.R;
import com.uc.finalproject.adapter.ReminderAdapter;
import com.uc.finalproject.model.ArrayReminder;
import com.uc.finalproject.model.SimpanReminder;

import java.util.ArrayList;

public class ReminderFragments extends Fragment {
    TextView lbl_nodata_3, lbl_nodata_4, lbl_nodata_5;
    RecyclerView mRecycleView;
    ArrayList<SimpanReminder> listReminder = ArrayReminder.listReminder;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_reminder, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lbl_nodata_3 = view.findViewById(R.id.lbl_nodata_3);
        lbl_nodata_4 = view.findViewById(R.id.lbl_nodata_4);
        lbl_nodata_5 = view.findViewById(R.id.lbl_nodata_5);
        mRecycleView = view.findViewById(R.id.recycler_reminder);
        FloatingActionButton button_add_reminder = view.findViewById(R.id.button_tambah_reminder);
        button_add_reminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
        if (listReminder.isEmpty()){
            lbl_nodata_3.setVisibility(View.VISIBLE);
            lbl_nodata_4.setVisibility(View.VISIBLE);
            lbl_nodata_5.setVisibility(View.VISIBLE);
        }
        else {
            lbl_nodata_3.setVisibility(View.INVISIBLE);
            lbl_nodata_4.setVisibility(View.INVISIBLE);
            lbl_nodata_5.setVisibility(View.INVISIBLE);
            showReminder(listReminder);
        }
    }

    private void openDialog() {
        ReminderAddActivity reminderAddActivity = new ReminderAddActivity();
        reminderAddActivity.show(getActivity().getSupportFragmentManager(), "input reminder");
    }

    public void showReminder (ArrayList<SimpanReminder>listReminder){
        mRecycleView.setHasFixedSize(true);
        mRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        ReminderAdapter adapter = new ReminderAdapter(getActivity());
        adapter.setListReminder(listReminder);
        mRecycleView.setAdapter(adapter);
    }
}
