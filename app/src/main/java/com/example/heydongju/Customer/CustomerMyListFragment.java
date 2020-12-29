package com.example.heydongju.Customer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.heydongju.Adapter.AdvertiseAdapter;
import com.example.heydongju.Adapter.DeliverInfoAdapter;
import com.example.heydongju.Adapter.StoreRecommendAdapter;
import com.example.heydongju.Data.DeliverInfoData;
import com.example.heydongju.OnBackPressedListener;
import com.example.heydongju.R;

import java.util.Arrays;
import java.util.List;

public class CustomerMyListFragment extends Fragment implements OnBackPressedListener {
    private RelativeLayout home;
    private RelativeLayout mylist;
    private RelativeLayout search;
    private DeliverInfoAdapter adapter;

    private RelativeLayout mypage;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.customer_mylist, container, false);
        home = (RelativeLayout) root.findViewById(R.id.home);
        mylist = (RelativeLayout) root.findViewById(R.id.mylist);
        search = (RelativeLayout) root.findViewById(R.id.search);
        mypage = (RelativeLayout) root.findViewById(R.id.mypage);
        mylist.setSelected(true);
        RecyclerView recyclerView = root.findViewById(R.id.recyclerview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new DeliverInfoAdapter();

        recyclerView.setAdapter(adapter);
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
                    Navigation.findNavController(view).navigate(R.id.action_nav_customer_mylist_store_to_nav_customer_home);

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

                    Navigation.findNavController(root).navigate(R.id.action_nav_customer_mylist_to_nav_customer_st);


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

                    Navigation.findNavController(root).navigate(R.id.action_nav_customer_mylist_to_nav_customer_mypage);


                }
            }
        });



        getData();

        return root;
    }
    private void getData() {
        // 임의의 데이터입니다.
        List<String> listTitle = Arrays.asList("국화", "사막", "수국", "해파리");
        List<Integer> listCups = Arrays.asList(
                1,
                2,
                4,
                1
        );
        List<String> listTime = Arrays.asList(
                "1:24",
                "1:34",
                "21:24",
                "12:24"
        );
        for (int i = 0; i < listTitle.size(); i++) {
            // 각 List의 값들을 data 객체에 set 해줍니다.
            DeliverInfoData data = new DeliverInfoData();
            data.setDeliverName(listTitle.get(i));
            data.setDeliverCups(Float.valueOf(listCups.get(i)));
            data.setDeliverStar(Float.valueOf(listCups.get(i)));
            data.setTime(listTime.get(i));

            // 각 값이 들어간 data를 adapter에 추가합니다.
            adapter.addItem(data);
        }

        // adapter의 값이 변경되었다는 것을 알려줍니다.
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {

    }
}
