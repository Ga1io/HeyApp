package com.example.heydongju.Customer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
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

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.customer_home, container, false);

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
        List<String> listTitle = Arrays.asList("스타벅스", "요거프레스", "맥도널드", "버거킹", "이디야", "벤티");
        List<Integer> listIcon = Arrays.asList(
                R.drawable.starbucks,
                R.drawable.yogerpresso,
                R.drawable.mcdonald,
                R.drawable.burkerking,
                R.drawable.ediya,
                R.drawable.venti
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
        List<String> listTitle = Arrays.asList("이벤트1", "이벤트2", "이벤트3", "이벤트4", "이벤트5", "이벤트6");
        List<String> listContent = Arrays.asList("이거 사세염", "제발요", "섹스!", "고추", "노현규", "노현");
        List<String> listContent2 = Arrays.asList("노", "김진원", "박동후!", "이이잉", "부라라라랄", "달랑달랑");
        List<Integer> listIcon = Arrays.asList(
                R.drawable.americano,
                R.drawable.yogerpresso,
                R.drawable.mcdonald,
                R.drawable.burkerking,
                R.drawable.ediya,
                R.drawable.venti
        );
        for (int i = 0; i < listTitle.size(); i++) {
            // 각 List의 값들을 data 객체에 set 해줍니다.
            AdvertiseData data = new AdvertiseData();
            data.setTitle(listTitle.get(i));
            data.setContent(listContent.get(i));
            data.setContent2(listContent2.get(i));
            data.setRecommendImg(listIcon.get(i));
            // 각 값이 들어간 data를 adapter에 추가합니다.
            adapter3.addItem(data);
        }

        // adapter의 값이 변경되었다는 것을 알려줍니다.
        adapter3.notifyDataSetChanged();
    }

}
