package com.galio.heydrink.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.galio.heydrink.Adapter.MainAdapter;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<MainAdapter> adapter;

    public HomeViewModel() {
        this.adapter = new MutableLiveData<>();
    }

    public void setAdapter(MainAdapter adapter) {
        this.adapter.setValue(adapter);
    }

    public LiveData<MainAdapter> getAdapter() {
        return adapter;
    }
}