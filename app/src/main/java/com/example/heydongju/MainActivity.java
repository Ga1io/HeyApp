package com.example.heydongju;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.heydongju.Customer.CustomerMenuDetailFragment;
import com.example.heydongju.Customer.CustomerMenuFragment;
import com.example.heydongju.Customer.CustomerStoreFragment;
import com.example.heydongju.Data.MenuData;
import com.example.heydongju.Data.StoreData;

public class MainActivity extends AppCompatActivity {

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

    }

    public void fragChange(){

        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.frame, store);
        fragmentTransaction.commit();
    }

    public void toMenu(StoreData store){

        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //fragmentTransaction.addToBackStack(null);

        Bundle bundle = new Bundle();
        //  bundle.putSerializable("json", response.body());

        bundle.putSerializable("store", store);
        menu.setArguments(bundle); //data being send to SecondFragment
        Log.e("fucking", store.name);
        fragmentTransaction.replace(R.id.frame, menu);
        fragmentTransaction.commit();
    }
    public void toMenuDetail(StoreData store, MenuData menu){

        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //fragmentTransaction.addToBackStack(null);

        Bundle bundle = new Bundle();
        //  bundle.putSerializable("json", response.body());

        bundle.putSerializable("store", store);
        bundle.putSerializable("menu", menu);

        menuDetail.setArguments(bundle); //data being send to SecondFragment
        fragmentTransaction.replace(R.id.frame, menuDetail);
        fragmentTransaction.commit();
    }

}
