package com.uc.finalproject.ui.reminder_input;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.uc.finalproject.R;

public class inputReminderFragments extends Fragment {
    private inputReminderViewModel inputReminderViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        inputReminderViewModel= ViewModelProviders.of(this).get(inputReminderViewModel.class);
        View root = inflater.inflate(R.layout.fragment_reminder_input, container, false);
        final EditText editText = root.findViewById(R.id.input_field);
        inputReminderViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>(){

            @Override
            public void onChanged(String s) {
                editText.setText(s);
            }
        });
        return root;
    }
}
