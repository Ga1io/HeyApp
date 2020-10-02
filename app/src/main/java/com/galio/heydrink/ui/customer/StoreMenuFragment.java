package com.galio.heydrink.ui.customer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.galio.heydrink.Adapter.MenuAdapter;
import com.galio.heydrink.Data.Menu;
import com.galio.heydrink.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class StoreMenuFragment extends Fragment {
    private MenuAdapter adapter;
    private RecyclerView menuRecyclerView;
    private LinearLayoutManager linearLayoutManager;

    public StoreMenuFragment(){
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.customer_store_menu, container, false);

        init(root);

        return root;
    }

    private void init(View v){
        adapter = new MenuAdapter();
        adapter.setMenu(makeDummy());

        menuRecyclerView = v.findViewById(R.id.menuRecyclerView);
        linearLayoutManager = new LinearLayoutManager(getActivity());

        menuRecyclerView.setLayoutManager(linearLayoutManager);
        menuRecyclerView.setAdapter(adapter);
    }

    private ArrayList<Menu> makeDummy(){
        ArrayList<Menu> data = new ArrayList<>();

        String[] menuName = {"아이스 아메리카노", "녹차 프라페", "미숫가루", "카페라떼", "망고스무디",
                "체리스무디", "토마토주스", "자바칩프라페", "타로 버블티", "딸기 요거트 스무디"};

        Integer[] menuImg = {R.drawable.sample_ahah, R.drawable.sample_cafelette, R.drawable.sample_mangosm,
                R.drawable.sample_javafrape, R.drawable.sample_tarobubble};

        int price = 1000;
        int idx = 0;

        for (int i = 0; i<10; i++){
            price += 500;

            Menu newMenu = new Menu();
            newMenu.name = menuName[i];
            newMenu.price = NumberFormat.getNumberInstance(Locale.US).format(price);
            newMenu.info = "메뉴에 대한 설명......";

            if (i== 2 || i ==3 || i == 8){
                HashMap<String, String> options = new HashMap<>();

                options.put("타피오카 펄 추가", "500");
                options.put("알로에 펄 추가", "500");
                options.put("샷추가", "500");

                newMenu.options = options;
            }

            if (i == 0 || i == 3 || i ==4 || i == 7 || i== 8){
                newMenu.img = menuImg[idx];
                idx++;
            }

            data.add(newMenu);
        }

        return data;
    }
}
