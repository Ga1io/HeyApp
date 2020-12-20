package com.example.heydongju.Customer;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import com.example.heydongju.Data.Me;
import com.example.heydongju.Data.StoreData;
import com.example.heydongju.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class CustomerMenuFragment extends Fragment {

    private StoreData store;
    private FragmentManager fm;
    private FragmentTransaction fragmentTransaction;
    private Fragment storeMenuFragment;
    private Fragment storeInfoFragment = new CustomerStoreInfoFragment();
    private FloatingActionButton shoppingCartBtn;

    private TextView storeNameTextView;
    private ImageView storeIconImageView;

    private TextView menuBtn;
    private TextView infoBtn;
    private RelativeLayout home;
    private RelativeLayout mylist;
    private RelativeLayout search;
    private RelativeLayout mypage;
    private ImageButton orderBtn;

    public static String currentFragment = "menu";

    public CustomerMenuFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null){
            store = (StoreData) getArguments().getSerializable("store");
        }

        storeMenuFragment = new CustomerSelectMenuFragment(store);

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
        View root = inflater.inflate(R.layout.customer_menu, container, false);
        home = (RelativeLayout) root.findViewById(R.id.home);
        mylist = (RelativeLayout) root.findViewById(R.id.mylist);
        search = (RelativeLayout) root.findViewById(R.id.search);
        mypage = (RelativeLayout) root.findViewById(R.id.mypage);
        search.setSelected(true);


        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.isSelected()){
                }
                else{
                    view.setSelected(true);
                    mylist.setSelected(false);
                    search.setSelected(false);
                    mypage.setSelected(false);
                    Navigation.findNavController(view).navigate(R.id.action_nav_customer_select_menu_to_nav_customer_home);

                }
            }
        });
        mylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.isSelected()){
                }
                else{
                    view.setSelected(true);
                    home.setSelected(false);
                    mypage.setSelected(false);
                    search.setSelected(false);

                    Navigation.findNavController(view).navigate(R.id.action_nav_customer_select_menu_to_nav_customer_mylist);

                }
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.isSelected()){
                }
                else{
                    view.setSelected(true);
                    home.setSelected(false);
                    mypage.setSelected(false);
                    mylist.setSelected(false);

                    Navigation.findNavController(root).navigate(R.id.action_customerMenuDetailFragment_to_nav_customer_store);


                }
            }
        });
        mypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.isSelected()){
                }
                else{
                    view.setSelected(true);
                    home.setSelected(false);
                    mylist.setSelected(false);
                    search.setSelected(false);

                    Navigation.findNavController(view).navigate(R.id.action_nav_customer_select_menu_to_nav_customer_mypage);


                }
            }
        });
        init(root);
        if (currentFragment.equals("selected")){
            Log.e("sex","goood soogooood");
        }else{

        }

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

        shoppingCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("backFragment", "selectMenu");
                bundle.putSerializable("store", store);
                Navigation.findNavController(view).navigate(R.id.action_global_customer_shopping_cart, bundle);
            }
        });

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