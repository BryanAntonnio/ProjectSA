package com.uc.finalproject.ui.reminder_input;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class inputReminderViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public inputReminderViewModel(){
        mText = new MutableLiveData<>();
        mText.setValue("");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
