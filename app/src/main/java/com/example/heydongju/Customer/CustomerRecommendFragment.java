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
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class CustomerRecommendFragment extends Fragment {

    private CustomerOrderData customerOrderData;
    private MenuAdapter adapter2=new MenuAdapter();
    private RecyclerView storeRecyclerView;
    private GridLayoutManager gridLayoutManager;
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
        customerOrderData = new CustomerOrderData();
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.isSelected()) {
                } else {
                    view.setSelected(true);
                    mylist.setSelected(false);
                    search.setSelected(false);
                    mypage.setSelected(false);
                    Navigation.findNavController(root).navigate(R.id.action_nav_recommend_to_nav_customer_home);

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

                    Navigation.findNavController(root).navigate(R.id.action_nav_recommend_to_nav_customer_mylist);

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

                    Navigation.findNavController(root).navigate(R.id.action_nav_recommend_to_nav_customer_select_menu);


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

                    Navigation.findNavController(root).navigate(R.id.action_nav_recommend_to_nav_customer_mypage);


                }
            }
        });


        if (getArguments() != null) {
            Bundle bundle = getArguments();
            storeData = (StoreData) getArguments().getSerializable("store");
        }


        if (storeData != null) {
            plz.setBackground(null);
            makeDummy(storeData);
            adapter2.notifyDataSetChanged();
            Log.e("sedfdfdx!", storeData.name);
        } else {
            Log.e("sedfdfdx!", "sexsdsfdfsd");

        }


        menuRecyclerView = root.findViewById(R.id.menuRecyclerView);
        gridLayoutManager = new GridLayoutManager(getActivity(), 4);
        menuRecyclerView.setLayoutManager(gridLayoutManager);
        menuRecyclerView.setAdapter(adapter2);


        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<MenuData> selectedMenu = new ArrayList<>();
                for(int i=0; i<adapter2.data.size(); i++) {
                    if (adapter2.data.get(i).isSelected()){
                        selectedMenu.add(adapter2.data.get(i));
                    }
                }
                if (selectedMenu.size()==0) {
                }
                else{
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("store", storeData);
                    bundle.putSerializable("menu", selectedMenu);
                    Navigation.findNavController(view).navigate(R.id.action_nav_customer_recommend_to_nav_amount,bundle);


                }


            }
        });


        return root;
    }

    private void makeDummy(StoreData storeData){
        ArrayList<MenuData> data = new ArrayList<>();
        List<String> menuName = new ArrayList<>();
        List<Integer> listIcon = new ArrayList<>();
        List<Integer> listPrice = new ArrayList<>();

        if(storeData.name.equals("스타벅스")){
            menuName = Arrays.asList("노현규 아메리카아", "녹차 프라페", "미숫가루", "카페라떼", "망고스무디",
                    "체리스무디", "토마토주스", "자바칩프라페", "타로 버블티", "딸기 요거트 스무디");

            listIcon = Arrays.asList(
                    R.drawable.sample_ahah, R.drawable.sample_cafelette, R.drawable.sample_mangosm,
                    R.drawable.sample_javafrape, R.drawable.sample_tarobubble,R.drawable.sample_ahah, R.drawable.sample_cafelette, R.drawable.sample_mangosm,
                    R.drawable.sample_javafrape, R.drawable.sample_tarobubble
            );
            listPrice = Arrays.asList(
                    1200, 1500, 2000,1000,1000,3000,
                    1000,1000,1000,1000
            );

        }
        else if(storeData.name.equals("베브릿지")){

            menuName = Arrays.asList("노이스 dasdf아메리카아", "녹차 프라페", "미숫가루", "카페라떼", "망고스무디",
                    "체리스무디", "토마토주스", "자바칩프라페", "타로 버블티", "딸기 요거트 스무디");

            listIcon = Arrays.asList(
                    R.drawable.sample_ahah, R.drawable.sample_cafelette, R.drawable.sample_mangosm,
                    R.drawable.sample_javafrape, R.drawable.sample_tarobubble,R.drawable.sample_ahah, R.drawable.sample_cafelette, R.drawable.sample_mangosm,
                    R.drawable.sample_javafrape, R.drawable.sample_tarobubble
            );
            listPrice = Arrays.asList(
                    1200, 1500, 2000,1000,1000,3000,
                    1000,1000,1000,1000
            );
        }
        else{

            menuName = Arrays.asList("노이스 아메리카아", "녹차 프라페", "미숫가루", "카페라떼", "망고스무디",
                    "체리스무디", "토마토주스", "자바칩프라페", "타로 버블티", "딸기 요거트 스무디");

            listIcon = Arrays.asList(
                    R.drawable.sample_ahah, R.drawable.sample_cafelette, R.drawable.sample_mangosm,
                    R.drawable.sample_javafrape, R.drawable.sample_tarobubble,R.drawable.sample_ahah, R.drawable.sample_cafelette, R.drawable.sample_mangosm,
                    R.drawable.sample_javafrape, R.drawable.sample_tarobubble
            );
            listPrice = Arrays.asList(
                    1200, 1500, 2000,1000,1000,3000,
                    1000,1000,1000,1000
            );
        }

        for (int i = 0; i < menuName.size(); i++) {
            // 각 List의 값들을 data 객체에 set 해줍니다.
            MenuData menuData = new MenuData();
            menuData.setName(menuName.get(i));
            menuData.setImg(listIcon.get(i));
            menuData.setPrice(listPrice.get(i));

            // 각 값이 들어간 data를 adapter에 추가합니다.
            adapter2.addItem(menuData);
        }
        // adapter의 이 변경되었다는 것을 알려줍니다.
        adapter2.notifyDataSetChanged();

    }

}
