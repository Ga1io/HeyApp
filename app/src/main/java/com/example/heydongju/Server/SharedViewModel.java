package com.example.heydongju.Server;


import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {
   public String mode;

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
}
