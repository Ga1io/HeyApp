package com.galio.heydrink.ui.customer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.galio.heydrink.Data.Me;
import com.galio.heydrink.Data.Store;
import com.galio.heydrink.R;
import com.google.android.material.snackbar.Snackbar;


public class SelectMenuFragment extends Fragment {
    private Store store;
    private FragmentManager fm;
    private FragmentTransaction fragmentTransaction;
    private Fragment storeMenuFragment;
    private Fragment storeInfoFragment = new StoreInfoFragment();

    private TextView storeNameTextView;
    private ImageView storeIconImageView;

    private TextView menuBtn;
    private TextView infoBtn;

    private ImageButton orderBtn;

    private String currentFragment = "menu";

    public SelectMenuFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null){
            store = (Store) getArguments().getSerializable("store");
        }

        storeMenuFragment = new StoreMenuFragment(store);

        fm = getActivity().getSupportFragmentManager();
        fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.add(R.id.menuFragment, storeMenuFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.customer_select_menu, container, false);

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

        infoBtn.setOnClickListener(new View.OnClickListener() {
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
        });

        orderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Me.getInstance().cart.size() == 0){
                    Snackbar.make(view, "장바구니에 아무것도 없습니다.", Snackbar.LENGTH_SHORT).show();
                }else{
                    Navigation.findNavController(view).navigate(R.id.action_global_customer_order);
                }
            }
        });
    }
}