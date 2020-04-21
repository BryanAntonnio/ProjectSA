package com.uc.finalproject.ui.notes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NotesViewModel extends ViewModel {

    private MutableLiveData <String> mText;

    public NotesViewModel(){
        mText = new MutableLiveData<>();
        mText.setValue("There’s nothing to see");

    }

    public LiveData<String> getText() {
        return mText;
    }
}
