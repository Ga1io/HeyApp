package com.example.heydongju.Customer;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.heydongju.Data.StoreData;
import com.example.heydongju.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CustomerMenuFragment extends Fragment {

    private StoreData store;
    private FragmentManager fm;
    private FragmentTransaction fragmentTransaction;
    private Fragment storeMenuFragment;
    //private Fragment storeInfoFragment = new StoreInfoFragment();
    private FloatingActionButton shoppingCartBtn;

    private TextView storeNameTextView;
    private ImageView storeIconImageView;

    private TextView menuBtn;
    private TextView infoBtn;

    private ImageButton orderBtn;

    private String currentFragment = "menu";

    public CustomerMenuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.customer_menu, container, false);
        if (getArguments() != null){
            store = (StoreData) getArguments().getSerializable("store");
            Log.e("fucking yeas","what????");

        }else{
            Log.e("fucking yeas","whwfdsfsdfat????");

        }

        storeMenuFragment = new CustomerSelectMenuFragment();

        fm = getActivity().getSupportFragmentManager();
        fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.add(R.id.menuFragment, storeMenuFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        init(root);


        return root;
    }

    private void init(View v){
        storeIconImageView = v.findViewById(R.id.storeIcon);
        storeNameTextView = v.findViewById(R.id.storeName);
        menuBtn = v.findViewById(R.id.menuTextView);
        infoBtn = v.findViewById(R.id.infoTextView);
        orderBtn = v.findViewById(R.id.orderBtn);

        storeIconImageView.setBackgroundResource(store.icon);
        storeNameTextView.setText(store.name);

        shoppingCartBtn = v.findViewById(R.id.shoppingCartBtn);

        /*shoppingCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("backFragment", "selectMenu");
                bundle.putSerializable("store", store);
                Navigation.findNavController(view).navigate(R.id.action_global_customer_shopping_cart, bundle);
            }
        });*/

        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentFragment.equals("menu")){

                }else{
                    FragmentManager manager = getActivity().getSupportFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.replace(R.id.menuFragment, storeMenuFragment);
                    transaction.commit();
                }

                currentFragment = "menu";
            }
        });

     /*   infoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentFragment.equals("info")){

                }else{
                    FragmentManager manager = getActivity().getSupportFragmentManager();
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.replace(R.id.menuFragment, storeInfoFragment);
                    transaction.commit();
                }

                currentFragment = "info";
            }
        });*/

      /*  orderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Me.getInstance().cart.size() == 0){
                    Snackbar.make(view, "장바구니에 아무것도 없습니다.", Snackbar.LENGTH_SHORT).show();
                }else{
                    Navigation.findNavController(view).navigate(R.id.action_global_customer_order);
                }
            }
        });*/
    }
}