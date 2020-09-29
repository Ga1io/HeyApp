package com.galio.heydrink.ui.customer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.galio.heydrink.Adapter.StoreAdapter;
import com.galio.heydrink.R;

public class SelectStoreFragment extends Fragment {

    private SelectStoreViewModel selectStoreViewModel;
    private RecyclerView storeRecyclerView;
    private GridLayoutManager gridLayoutManager;
    private StoreAdapter storeAdapter = new StoreAdapter();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        selectStoreViewModel =
                ViewModelProviders.of(this).get(SelectStoreViewModel.class);
        View root = inflater.inflate(R.layout.customer_select_store, container, false);

        selectStoreViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });

        init(root);
        return root;
    }

    private void init(View view){
        storeRecyclerView = view.findViewById(R.id.storeRecyclerView);
        gridLayoutManager = new GridLayoutManager(getActivity(), 3);

        storeRecyclerView.setLayoutManager(gridLayoutManager);

        storeRecyclerView.setAdapter(storeAdapter);
    }
}