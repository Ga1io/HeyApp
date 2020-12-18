package com.example.heydongju.Customer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.heydongju.Data.StoreData;
import com.example.heydongju.R;

public class CustomerShoppingCart extends Fragment {
    private Button backBtn;
    private int backAction;
    private StoreData backStore = null;

    public CustomerShoppingCart() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null){
            String backstack = getArguments().getString("backFragment");

            if (backstack.equals("selectStore")){
                backAction = R.id.action_customer_shopping_cart_to_nav_customer_select_store;
            }else if(backstack.equals("selectMenu")){
                backAction = R.id.action_customer_shopping_cart_to_navigation;
                backStore = (StoreData) getArguments().getSerializable("store");
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.customer_shopping_cart, container, false);

        init(root);

        return root;
    }

    private void init(View v){
        backBtn = v.findViewById(R.id.backBtn);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                if (backStore != null){
                    bundle.putSerializable("store", backStore);
                }
                Navigation.findNavController(view).navigate(backAction, bundle);
            }
        });
    }
}