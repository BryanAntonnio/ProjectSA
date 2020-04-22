package com.uc.finalproject.ui.notes_input;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.uc.finalproject.R;

public class inputNotesFragments extends Fragment {

    private inputNotesViewModel inputNotesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        inputNotesViewModel = ViewModelProviders.of(this).get(inputNotesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notes_input, container, false);
        final EditText editText = root.findViewById(R.id.input_field);
        inputNotesViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                editText.setText(s);
            }
        });
        return root;
    }
}
