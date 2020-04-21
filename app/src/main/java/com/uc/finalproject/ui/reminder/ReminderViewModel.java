package com.uc.finalproject.ui.reminder;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ReminderViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ReminderViewModel(){
        mText = new MutableLiveData<>();
        mText.setValue("There’s nothing to see.");

    }

    public LiveData<String> getText() {
        return mText;
    }
}
