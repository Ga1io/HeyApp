package com.galio.heydrink.ui.customer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SelectStoreViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SelectStoreViewModel() {
        mText = new MutableLiveData<>();
    }

    public LiveData<String> getText() {
        return mText;
    }
}