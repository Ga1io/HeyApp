package com.example.heydongju;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.heydongju.Customer.CustomerMenuDetailFragment;
import com.example.heydongju.Customer.CustomerMenuFragment;
import com.example.heydongju.Customer.CustomerStoreFragment;
import com.example.heydongju.Data.MenuData;
import com.example.heydongju.Data.StoreData;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    public NavController navController;
    CustomerStoreFragment store;
    CustomerMenuFragment menu;
    CustomerMenuDetailFragment menuDetail;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        store = new CustomerStoreFragment();
        menu = new CustomerMenuFragment();
        menuDetail = new CustomerMenuDetailFragment();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);

    }


}


