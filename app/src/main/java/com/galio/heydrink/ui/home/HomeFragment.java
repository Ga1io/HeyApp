package com.galio.heydrink.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.galio.heydrink.Adapter.MainAdapter;
import com.galio.heydrink.Data.DeliverOrder;
import com.galio.heydrink.Data.Order;
import com.galio.heydrink.Data.User;
import com.galio.heydrink.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class HomeFragment extends Fragment {
    private HomeViewModel homeViewModel;
    private RecyclerView homeRecyclerView;
    private LinearLayoutManager linearLayoutManager;
    private MainAdapter adapter;
    private ImageButton searchBtn;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        init(root);

        System.out.println("INIT Fragment");

        homeViewModel.getAdapter().observe(getActivity(), new Observer<MainAdapter>() {
            @Override
            public void onChanged(MainAdapter adpater) {
                adapter.addOrders(makeDummy());
                homeRecyclerView.setAdapter(adapter);
            }
        });

        return root;
    }

    private void init(View view){
        homeRecyclerView = view.findViewById(R.id.homeRecyclerView);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        homeRecyclerView.setLayoutManager(linearLayoutManager);

        searchBtn = view.findViewById(R.id.homeSearchBtn);
    }

    private ArrayList<Order> makeDummy(){
        ArrayList<Order> dummy = new ArrayList<>();
        String[] building = {"208관", "310관", "도서관", "정문", "입학처"};

        for (int i=0; i<5; i++){
            DeliverOrder deliverOrder = new DeliverOrder(new User());

            ArrayList<String> buildings = new ArrayList<>();
            HashMap<String, String> times = new HashMap<>();

            String time = "09:1";

            for (int j = 0; i< (new Random()).nextInt(2) + 1; i++){
                int idx = (new Random()).nextInt(5);
                buildings.add(building[idx]);
                times.put(building[idx], time + Integer.toString(i*3));
            }

            deliverOrder.setDestinations(buildings);
            deliverOrder.setDestTime(times);
            dummy.add(deliverOrder);
        }

        return dummy;
    }
}