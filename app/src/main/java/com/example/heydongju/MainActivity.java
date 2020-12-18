package com.example.heydongju;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.heydongju.Customer.CustomerSelectStore;

import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    CustomerSelectStore select;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        select = new CustomerSelectStore ();

    }

    public void fragChange(){

        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.frame, select);
        fragmentTransaction.commit();
    }


}
