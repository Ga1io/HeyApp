package com.example.heydongju.Customer;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.heydongju.Adapter.CustomerStoreAdapter;
import com.example.heydongju.Adapter.MenuAdapter;
import com.example.heydongju.Data.CustomerOrderData;
import com.example.heydongju.Data.MenuData;
import com.example.heydongju.Data.StoreData;
import com.example.heydongju.MainActivity;
import com.example.heydongju.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class CustomerRecommendFragment extends Fragment {

    private CustomerOrderData customerOrderData;
    private MenuAdapter adapter2=new MenuAdapter();
    private RecyclerView storeRecyclerView;
    private GridLayoutManager gridLayoutManager;
    private CustomerStoreAdapter adapter = new CustomerStoreAdapter();
    private FloatingActionButton shoppingCartBtn;
    private RelativeLayout home;
    private RelativeLayout mylist;
    private RelativeLayout search;
    private RelativeLayout mypage;
    private RecyclerView menuRecyclerView;
    private StoreData storeData;
    private LinearLayoutManager linearLayoutManager;
    private ImageView order;
    private ImageView plz;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.customer_recommend, container, false);

        home = (RelativeLayout) root.findViewById(R.id.home);
        mylist = (RelativeLayout) root.findViewById(R.id.mylist);
        search = (RelativeLayout) root.findViewById(R.id.search);
        mypage = (RelativeLayout) root.findViewById(R.id.mypage);
        order = (ImageView) root.findViewById(R.id.order);
        plz = (ImageView) root.findViewById(R.id.plz);

        home.setSelected(true);
        customerOrderData= new CustomerOrderData();
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
                    Navigation.findNavController(root).navigate(R.id.action_nav_customer_select_store_to_nav_customer_home);

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

                    Navigation.findNavController(root).navigate(R.id.action_nav_customer_select_store_to_nav_customer_mylist);

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

                    Navigation.findNavController(root).navigate(R.id.action_nav_customer_select_store_to_nav_customer_mypage);


                }
            }
        });
        adapter.setStores(getData());


        if (getArguments() != null) {
            Bundle bundle = getArguments();
            storeData = (StoreData) getArguments().getSerializable("store");
        }



        if(storeData!=null){
            plz.setBackground(null);
            adapter2.setMenu(makeDummy(storeData));
            adapter2.notifyDataSetChanged();
            Log.e("sedfdfdx!",storeData.name);
        }
        else{
            Log.e("sedfdfdx!","sexsdsfdfsd");

        }


        menuRecyclerView = root.findViewById(R.id.menuRecyclerView);
        gridLayoutManager = new GridLayoutManager(getActivity(), 4);
        menuRecyclerView.setLayoutManager(gridLayoutManager);
        menuRecyclerView.setAdapter(adapter2);
        

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  Bundle bundle = new Bundle();
                //bundle.putString("backFragment", "selectStore");
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
    private ArrayList<MenuData> makeDummy(StoreData storeData){
        ArrayList<MenuData> data = new ArrayList<>();

        if(storeData.name=="아마스빈") {
            String[] menuName = {"아이스 아메리카노", "녹차 프라페", "미숫가루", "카페라떼", "망고스무디",
                    "체리스무디", "토마토주스", "자바칩프라페", "타로 버블티", "딸기 요거트 스무디"};

            Integer[] menuImg = {R.drawable.sample_ahah, R.drawable.sample_cafelette, R.drawable.sample_mangosm,
                    R.drawable.sample_javafrape, R.drawable.sample_tarobubble};

            int price = 1000;
            int idx = 0;

            for (int i = 0; i<10; i++){
                price += 500;

                MenuData newMenu = new MenuData();
                newMenu.name = menuName[i];
                newMenu.price = NumberFormat.getNumberInstance(Locale.US).format(price);
                newMenu.info = "메뉴에 대한 설명......";

                if (i== 2 || i ==3 || i == 8){
                    ArrayList<MenuData.Option> options = new ArrayList<>();

                    options.add(new MenuData.Option(newMenu, "타피오카 펄 추가", "500"));
                    options.add(new MenuData.Option(newMenu,"알로에 펄 추가", "500"));
                    options.add(new MenuData.Option(newMenu,"샷추가", "500"));

                    newMenu.options = options;
                }

                if (i == 0 || i == 3 || i ==4 || i == 7 || i== 8){
                    newMenu.img = menuImg[idx];
                    idx++;
                }

                data.add(newMenu);
            }
        }
        else if(storeData.name=="베브릿지"){

            String[] menuName = {"노이스 아메리카아", "녹차 프라페", "미숫가루", "카페라떼", "망고스무디",
                    "체리스무디", "토마토주스", "자바칩프라페", "타로 버블티", "딸기 요거트 스무디"};

            Integer[] menuImg = {R.drawable.sample_ahah, R.drawable.sample_cafelette, R.drawable.sample_mangosm,
                    R.drawable.sample_javafrape, R.drawable.sample_tarobubble};

            int price = 1000;
            int idx = 0;

            for (int i = 0; i<10; i++){
                price += 500;

                MenuData newMenu = new MenuData();
                newMenu.name = menuName[i];
                newMenu.price = NumberFormat.getNumberInstance(Locale.US).format(price);
                newMenu.info = "메뉴에 대한 설명......";

                if (i== 2 || i ==3 || i == 8){
                    ArrayList<MenuData.Option> options = new ArrayList<>();

                    options.add(new MenuData.Option(newMenu, "타피오카 펄 추가", "500"));
                    options.add(new MenuData.Option(newMenu,"알로에 펄 추가", "500"));
                    options.add(new MenuData.Option(newMenu,"샷추가", "500"));

                    newMenu.options = options;
                }

                if (i == 0 || i == 3 || i ==4 || i == 7 || i== 8){
                    newMenu.img = menuImg[idx];
                    idx++;
                }

                data.add(newMenu);
            }
        }
        else{

            String[] menuName = {"아이스 아메리카노", "녹차 프라페", "미숫가루", "카페라떼", "망고스무디",
                    "체리스무디", "토마토주스", "자바칩프라페", "타로 버블티", "딸기 요거트 스무디"};

            Integer[] menuImg = {R.drawable.sample_ahah, R.drawable.sample_cafelette, R.drawable.sample_mangosm,
                    R.drawable.sample_javafrape, R.drawable.sample_tarobubble};

            int price = 1000;
            int idx = 0;

            for (int i = 0; i<10; i++){
                price += 500;

                MenuData newMenu = new MenuData();
                newMenu.name = menuName[i];
                newMenu.price = NumberFormat.getNumberInstance(Locale.US).format(price);
                newMenu.info = "메뉴에 대한 설명......";

                if (i== 2 || i ==3 || i == 8){
                    ArrayList<MenuData.Option> options = new ArrayList<>();

                    options.add(new MenuData.Option(newMenu, "타피오카 펄 추가", "500"));
                    options.add(new MenuData.Option(newMenu,"알로에 펄 추가", "500"));
                    options.add(new MenuData.Option(newMenu,"샷추가", "500"));

                    newMenu.options = options;
                }

                if (i == 0 || i == 3 || i ==4 || i == 7 || i== 8){
                    newMenu.img = menuImg[idx];
                    idx++;
                }

                data.add(newMenu);
            }
        }

        return data;
    }

}