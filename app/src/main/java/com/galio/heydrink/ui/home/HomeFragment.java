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
import com.galio.heydrink.R;

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

        homeViewModel.getAdapter().observe(getActivity(), new Observer<MainAdapter>() {
            @Override
            public void onChanged(MainAdapter adpater) {
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
}