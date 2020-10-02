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
import com.galio.heydrink.Data.Store;
import com.galio.heydrink.R;

import java.util.ArrayList;

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
        storeAdapter.setStores(makeDummy());

        storeRecyclerView = view.findViewById(R.id.storeRecyclerView);
        gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        storeRecyclerView.setLayoutManager(gridLayoutManager);
        storeRecyclerView.setAdapter(storeAdapter);
    }

    private ArrayList<Store> makeDummy(){
        ArrayList<Store> store = new ArrayList<>();

        Integer[] iconIds = {R.drawable.amasvin, R.drawable.angel_in_us, R.drawable.bebridge, R.drawable.burkerking,
                R.drawable.coffeebin, R.drawable.ediya, R.drawable.mcdonald, R.drawable.starbucks, R.drawable.tomtom,
                R.drawable.venti, R.drawable.yogerpresso, R.drawable.coffeenamu};
        String[] storeNames = {"아마스빈", "엔젤리너스", "베브릿지", "버거킹", "커피빈",
                "이디야", "맥도날드", "스타벅스", "탐앤탐스", "더 벤티", "요거프레소", "커피나무"};

        for (int i=0; i<iconIds.length; i++){
            store.add(new Store(storeNames[i], iconIds[i]));
        }
        return store;
    }
}