package com.example.heydongju.Customer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.heydongju.Adapter.AdvertiseAdapter;
import com.example.heydongju.Adapter.DeliverInfoAdapter;
import com.example.heydongju.Adapter.StoreRecommendAdapter;
import com.example.heydongju.Data.AdvertiseData;
import com.example.heydongju.Data.DeliverInfoData;
import com.example.heydongju.Data.StoreRecommend;
import com.example.heydongju.R;

import java.util.Arrays;
import java.util.List;

public class CustomerHomeFragment extends Fragment {
    private DeliverInfoAdapter adapter;
    private StoreRecommendAdapter adapter2;
    private AdvertiseAdapter adapter3;
    private RelativeLayout home;
    private RelativeLayout mylist;
    private RelativeLayout search;
    private RelativeLayout mypage;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.customer_home, container, false);
        home = (RelativeLayout) root.findViewById(R.id.home);
        mylist = (RelativeLayout) root.findViewById(R.id.mylist);
        search = (RelativeLayout) root.findViewById(R.id.search);
        mypage = (RelativeLayout) root.findViewById(R.id.mypage);
        home.setSelected(true);


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

                    Navigation.findNavController(view).navigate(R.id.action_nav_customer_home_to_nav_customer_mylist);

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

                    Navigation.findNavController(root).navigate(R.id.action_nav_customer_home_to_nav_customer_select_store);


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

                    Navigation.findNavController(view).navigate(R.id.action_nav_customer_home_to_nav_customer_mypage);


                }
            }
        });

        RecyclerView recyclerView = root.findViewById(R.id.recyclerview);
        RecyclerView recyclerView2 = root.findViewById(R.id.recyclerview2);
        RecyclerView recyclerView3 = root.findViewById(R.id.recyclerview3);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),3);
        recyclerView2.setLayoutManager(gridLayoutManager);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView3.setLayoutManager(layoutManager);

        adapter = new DeliverInfoAdapter();
        adapter2 = new StoreRecommendAdapter();
        adapter3 = new AdvertiseAdapter();

        recyclerView.setAdapter(adapter);
        recyclerView2.setAdapter(adapter2);
        recyclerView3.setAdapter(adapter3);

        getData();
        getData2();
        getData3();
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
    private void getData2() {
        // 임의의 데이터입니다.
        List<String> listTitle = Arrays.asList("스타벅스", "요거프레스", "맥도널드");
        List<Integer> listIcon = Arrays.asList(
                R.drawable.starbucks,
                R.drawable.yogerpresso,
                R.drawable.mcdonald
        );
        for (int i = 0; i < listTitle.size(); i++) {
            // 각 List의 값들을 data 객체에 set 해줍니다.
            StoreRecommend data = new StoreRecommend();
            data.setName(listTitle.get(i));
            data.setIcon(listIcon.get(i));


            // 각 값이 들어간 data를 adapter에 추가합니다.
            adapter2.addItem(data);
        }

        // adapter의 값이 변경되었다는 것을 알려줍니다.
        adapter2.notifyDataSetChanged();
    }
    private void getData3() {
        // 임의의 데이터입니다.

        List<Integer> listIcon = Arrays.asList(
                R.drawable.event2,
                R.drawable.event4,
                R.drawable.event1,
                R.drawable.event3
        );
        for (int i = 0; i < listIcon.size(); i++) {
            // 각 List의 값들을 data 객체에 set 해줍니다.
            AdvertiseData data = new AdvertiseData();
            data.setRecommendImg(listIcon.get(i));
            // 각 값이 들어간 data를 adapter에 추가합니다.
            adapter3.addItem(data);
        }

        // adapter의 값이 변경되었다는 것을 알려줍니다.
        adapter3.notifyDataSetChanged();
    }

}
