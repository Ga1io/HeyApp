package com.galio.heydrink.ui.customer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.galio.heydrink.Data.Store;
import com.galio.heydrink.R;


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

    private String currentFragment = "menu";

    public SelectMenuFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            store = (Store) getArguments().getSerializable("store");
        }catch (Exception e){

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
    }
}