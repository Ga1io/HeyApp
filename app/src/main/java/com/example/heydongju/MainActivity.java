package com.example.heydongju;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.heydongju.Customer.CustomerStoreFragment;
import com.example.heydongju.Server.SharedViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public NavController navController;
    CustomerStoreFragment store;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedViewModel model = new ViewModelProvider(this).get(SharedViewModel.class);


        store = new CustomerStoreFragment();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);

    }

    @Override
    public void onBackPressed() {
        if(navController.getCurrentDestination().getId()==R.id.nav_store
                ||navController.getCurrentDestination().getId()==R.id.nav_customer_home||
                navController.getCurrentDestination().getId()==R.id.nav_customer_mypage||
                navController.getCurrentDestination().getId()==R.id.nav_customer_mylist)
        {
        }else{
            super.onBackPressed();
        }

    }
}


