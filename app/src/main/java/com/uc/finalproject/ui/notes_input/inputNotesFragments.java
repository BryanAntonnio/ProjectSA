package com.uc.finalproject.ui.notes_input;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class inputNotesFragments extends Fragment {

    private inputNotesViewModel inputNotesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        inputNotesViewModel = ViewModelProviders.of(this).get(inputNotesViewModel.class);

        return root;
    }
}
