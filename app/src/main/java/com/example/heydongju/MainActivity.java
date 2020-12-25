package com.example.heydongju;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.heydongju.Customer.CustomerStoreFragment;

public class MainActivity extends AppCompatActivity {

    public NavController navController;
    CustomerStoreFragment store;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        store = new CustomerStoreFragment();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);

    }


}


