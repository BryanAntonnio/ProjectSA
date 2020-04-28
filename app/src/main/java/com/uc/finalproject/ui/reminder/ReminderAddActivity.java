package com.uc.finalproject.ui.reminder;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.appcompat.widget.Toolbar;

import com.uc.finalproject.MainActivity;
import com.uc.finalproject.R;
import com.uc.finalproject.model.ArrayReminder;
import com.uc.finalproject.model.SimpanReminder;

public class ReminderAddActivity extends AppCompatDialogFragment {
    private EditText reminder_input;
    String judulReminder;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_reminder_input,null);

        builder.setView(view).setNegativeButton("cancel", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        }).setPositiveButton("Simpan", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SimpanReminder simpanReminder = new SimpanReminder(judulReminder);
                ArrayReminder.listReminder.add(simpanReminder);
                getActivity().finish();
            }
        });

        reminder_input = view.findViewById(R.id.reminder_field);
        return builder.create();
    }
}
