package com.example.heydongju.Deliver;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.heydongju.Adapter.CustomerStoreAdapter;
import com.example.heydongju.Adapter.MenuAdapter;
import com.example.heydongju.Data.CustomerOrderData;
import com.example.heydongju.Data.MenuData;
import com.example.heydongju.Data.StoreData;
import com.example.heydongju.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DeliverStoreFragment extends Fragment {


    private RelativeLayout home;
    private RelativeLayout mylist;
    private RelativeLayout search;
    private RelativeLayout mypage;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.deliver_store, container, false);
        home = (RelativeLayout) root.findViewById(R.id.home);
        mylist = (RelativeLayout) root.findViewById(R.id.mylist);
        search = (RelativeLayout) root.findViewById(R.id.search);
        mypage = (RelativeLayout) root.findViewById(R.id.mypage);


        search.setSelected(true);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.isSelected()) {
                } else {
                    view.setSelected(true);
                    mylist.setSelected(false);
                    search.setSelected(false);
                    mypage.setSelected(false);
                    Navigation.findNavController(root).navigate(R.id.action_nav_deliver_store_to_nav_deliver_home);

                }
            }
        });
        mylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.isSelected()) {
                } else {
                    view.setSelected(true);
                    home.setSelected(false);
                    mypage.setSelected(false);
                    search.setSelected(false);

                    Navigation.findNavController(root).navigate(R.id.action_nav_deliver_store_to_nav_deliver_mylist);

                }
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.isSelected()) {
                } else {

                }
            }
        });
        mypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.isSelected()) {
                } else {
                    view.setSelected(true);
                    home.setSelected(false);
                    mylist.setSelected(false);
                    search.setSelected(false);

                    Navigation.findNavController(root).navigate(R.id.action_nav_deliver_store_to_nav_deliver_mypage);


                }
            }
        });






        return root;
    }


}
