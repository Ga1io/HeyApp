package com.example.heydongju.Customer;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.heydongju.Adapter.AmountAdapter;
import com.example.heydongju.Adapter.MenuAdapter;
import com.example.heydongju.Data.AdvertiseData;
import com.example.heydongju.Data.CustomerOrderData;
import com.example.heydongju.Data.DeliverInfoData;
import com.example.heydongju.Data.Me;
import com.example.heydongju.Data.MenuData;
import com.example.heydongju.Data.StoreData;
import com.example.heydongju.MainActivity;
import com.example.heydongju.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static androidx.core.content.ContextCompat.getSystemService;

public class CustomerAmountFragment extends Fragment {

    private AmountAdapter adapter=new AmountAdapter();
    private RecyclerView amountRecyclerView;
    private RelativeLayout home;
    private RelativeLayout mylist;
    private RelativeLayout search;
    private RelativeLayout mypage;
    private LinearLayoutManager linearLayoutManager;
    private StoreData storeData;
    private ArrayList<MenuData> menuData;
    private ImageView order;
    private CustomerOrderData customerOrderData;
    public static TextView totalPrice;
    private Spinner place;
    private EditText request;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.customer_amount, container, false);

        home = (RelativeLayout) root.findViewById(R.id.home);
        mylist = (RelativeLayout) root.findViewById(R.id.mylist);
        search = (RelativeLayout) root.findViewById(R.id.search);
        mypage = (RelativeLayout) root.findViewById(R.id.mypage);
        totalPrice = (TextView) root.findViewById(R.id.totalPrice);
        order = (ImageView) root.findViewById(R.id.order);
        place = (Spinner) root.findViewById(R.id.place);
        request = (EditText) root.findViewById(R.id.request);





        customerOrderData = new CustomerOrderData();
        if (getArguments() != null) {
            storeData = (StoreData) getArguments().getSerializable("store");
            menuData = (ArrayList<MenuData>) getArguments().getSerializable("menu");
        }

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.isSelected()) {
                } else {
                    view.setSelected(true);
                    mylist.setSelected(false);
                    search.setSelected(false);
                    mypage.setSelected(false);
                    Navigation.findNavController(root).navigate(R.id.nav_customer_amount_to_nav_customer_home);

                }
            }
        });
        mylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.isSelected()) {
                } else {
                    view.setSelected(true);
                    home.setSelected(false);
                    mypage.setSelected(false);
                    search.setSelected(false);

                    Navigation.findNavController(root).navigate(R.id.nav_customer_amount_to_nav_customer_mylist);

                }
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.isSelected()) {
                } else {
                    view.setSelected(true);
                    home.setSelected(false);
                    mypage.setSelected(false);
                    mylist.setSelected(false);

                    Navigation.findNavController(root).navigate(R.id.nav_customer_amount_to_nav_customer_select_menu);


                }
            }
        });
        mypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.isSelected()) {
                } else {
                    view.setSelected(true);
                    home.setSelected(false);
                    mylist.setSelected(false);
                    search.setSelected(false);

                    Navigation.findNavController(root).navigate(R.id.nav_customer_amount_to_nav_customer_mypage);


                }
            }
        });

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<MenuData> toOrder = new ArrayList<>();
                for(int i=0; i<adapter.listData.size(); i++){
                    if(adapter.listData.get(i).getAmount()!=0){
                        toOrder.add(adapter.listData.get(i));
                    }
                }

                customerOrderData.setMenuData(toOrder);
                customerOrderData.setPlace(place.toString());
                customerOrderData.setPriceSum(Integer.valueOf(String.valueOf(totalPrice.getText())));
                customerOrderData.setRequest(request.getText().toString());
                customerOrderData.setStoreData(storeData);

                Bundle bundle = new Bundle();
                bundle.putSerializable("order", customerOrderData);
                Navigation.findNavController(view).navigate(R.id.nav_customer_amount_to_nav_customer_payment,bundle);
            }
        });


        amountRecyclerView = root.findViewById(R.id.recyclerview);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        amountRecyclerView.setLayoutManager(linearLayoutManager);
        amountRecyclerView.setAdapter(adapter);


        makeDummy();

        totalPrice.setText(String.valueOf(0));

        return root;
    }
    private void makeDummy() {

        for(int i=0; i<menuData.size();i++){
            adapter.addItem(menuData.get(i));

        }

        // adapter의 값이 변경되었다는 것을 알려줍니다.
        adapter.notifyDataSetChanged();
    }

}