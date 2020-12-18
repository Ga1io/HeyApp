package com.example.heydongju.Customer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.heydongju.Adapter.CustomerStoreAdapter;
import com.example.heydongju.Data.StoreData;
import com.example.heydongju.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class CustomerSelectStore extends Fragment {

    private RecyclerView storeRecyclerView;
    private GridLayoutManager gridLayoutManager;
    private CustomerStoreAdapter adapter = new CustomerStoreAdapter();
    private FloatingActionButton shoppingCartBtn;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.customer_store, container, false);


        adapter.setStores(getData());

        storeRecyclerView = root.findViewById(R.id.storeRecyclerView);
        gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        storeRecyclerView.setLayoutManager(gridLayoutManager);
        storeRecyclerView.setAdapter(adapter);

        shoppingCartBtn = root.findViewById(R.id.shoppingCartBtn);

        shoppingCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("backFragment", "selectStore");
                //Navigation.findNavController(view).navigate(R.id.action_nav_customer_select_store_to_customer_shopping_cart, bundle);
            }
        });



        return root;
    }
    private ArrayList<StoreData> getData(){
        ArrayList<StoreData> store = new ArrayList<>();

        Integer[] iconIds = {R.drawable.amasvin, R.drawable.angel_in_us, R.drawable.bebridge, R.drawable.burkerking,
                R.drawable.coffeebin, R.drawable.ediya, R.drawable.mcdonald, R.drawable.starbucks, R.drawable.tomtom,
                R.drawable.venti, R.drawable.yogerpresso, R.drawable.coffeenamu};
        String[] storeNames = {"아마스빈", "엔젤리너스", "베브릿지", "버거킹", "커피빈",
                "이디야", "맥도날드", "스타벅스", "탐앤탐스", "더 벤티", "요거프레소", "커피나무"};

        for (int i=0; i<iconIds.length; i++){
            store.add(new StoreData(storeNames[i], iconIds[i]));
        }
        return store;
    }

}