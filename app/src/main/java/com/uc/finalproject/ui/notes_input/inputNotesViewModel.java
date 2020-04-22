package com.uc.finalproject.ui.notes_input;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class inputNotesViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public inputNotesViewModel(){
        mText = new MutableLiveData<>();
        mText.setValue("There’s nothing to see");

    }

    public LiveData<String> getText() {
        return mText;
    }
}
