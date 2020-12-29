package com.example.heydongju.Customer;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.heydongju.Adapter.CustomerStoreAdapter;
import com.example.heydongju.Adapter.MenuAdapter;
import com.example.heydongju.Data.CustomerOrderData;
import com.example.heydongju.Data.MenuData;
import com.example.heydongju.Data.StoreData;
import com.example.heydongju.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class CustomerTrainFragment extends Fragment {

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
    private GridLayoutManager linearLayoutManager;
    private ImageView order;
    private ImageView plz;
    private ArrayList<StoreData> stores;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.customer_train, container, false);
        home = (RelativeLayout) root.findViewById(R.id.home);
        mylist = (RelativeLayout) root.findViewById(R.id.mylist);
        search = (RelativeLayout) root.findViewById(R.id.search);
        mypage = (RelativeLayout) root.findViewById(R.id.mypage);
        order = (ImageView) root.findViewById(R.id.order);
        plz = (ImageView) root.findViewById(R.id.plz);

        stores = new ArrayList<>();
        if (getArguments() != null) {
            stores = (ArrayList<StoreData>) getArguments().getSerializable("stores");
            storeData = (StoreData) getArguments().getSerializable("store");
        }

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
                    Navigation.findNavController(root).navigate(R.id.action_nav_train_to_nav_customer_home);

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

                    Navigation.findNavController(root).navigate(R.id.action_nav_train_to_nav_customer_mylist);

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

                    Navigation.findNavController(root).navigate(R.id.action_nav_customer_train_to_nav_customer_select_store);


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

                    Navigation.findNavController(root).navigate(R.id.action_nav_train_to_nav_customer_mypage);


                }
            }
        });
        getData(stores);





        if(storeData!=null){
            plz.setBackground(null);
            makeDummy(storeData);

            for(int i=0; i<stores.size(); i++){
                if(stores.get(i).getName().equals(storeData.getName())){
                    stores.get(i).setSelected(true);

                    Log.e("select",stores.get(0).getName());
                }
                else{
                    stores.get(i).setSelected(false);

                }
            }

            adapter2.notifyDataSetChanged();
            Log.e("sedfdfdx!",storeData.name);


        }
        else{
            Log.e("sedfdfdx!","sexsdsfdfsd");

        }

        storeRecyclerView = root.findViewById(R.id.storeRecyclerView);
        linearLayoutManager = new GridLayoutManager(getActivity(), 3);
        storeRecyclerView.setLayoutManager(linearLayoutManager);
        storeRecyclerView.setAdapter(adapter);

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

                    Navigation.findNavController(view).navigate(R.id.action_nav_customer_train_to_nav_amount,bundle);

                }

            }
        });



        return root;
    }
    private void getData(ArrayList<StoreData> stores){


        adapter.addItem(stores.get(0));
        adapter.addItem(stores.get(1));
        adapter.addItem(stores.get(2));

        adapter.notifyDataSetChanged();

    }
    private void makeDummy(StoreData storeData) {

        ArrayList<MenuData> data = new ArrayList<>();
        List<String> menuName = new ArrayList<>();
        List<Integer> listIcon = new ArrayList<>();
        List<Integer> listPrice = new ArrayList<>();
        List<List<String>> options = new ArrayList<>();
        List<List<Boolean>> listOptionSelected = new ArrayList<>();

        if (storeData.name.equals("맥도널드")) {
            menuName = Arrays.asList("맥도널드 아메리카아", "녹차 프라페", "미숫가루", "카페라떼", "망고스무디",
                    "체리스무디", "토마토주스", "자바칩프라페", "타로 버블티", "딸기 요거트 스무디");

            listIcon = Arrays.asList(
                    R.drawable.sample_ahah, R.drawable.sample_cafelette, R.drawable.sample_mangosm,
                    R.drawable.sample_javafrape, R.drawable.sample_tarobubble, R.drawable.sample_ahah, R.drawable.sample_cafelette, R.drawable.sample_mangosm,
                    R.drawable.sample_javafrape, R.drawable.sample_tarobubble
            );
            listPrice = Arrays.asList(
                    1200, 1500, 2000,1000,1000,3000,
                    1000,1000,1000,1000
            );
            options = Arrays.asList(
                    Arrays.asList("옵션1", "옵션2","옵션3", "옵션4", "옵션5", "옵션6", "옵션7", "옵션8"),
                    Arrays.asList("샷추가", "고추"),
                    Arrays.asList("샷추가", "원샷"),
                    Arrays.asList("샷추가", "노현규"),
                    Arrays.asList("샷추가", "고기추가"),
                    Arrays.asList("샷추가", "쌈무추가"),
                    Arrays.asList("샷추가", "그르릉"),
                    Arrays.asList("샷추가", "섹스"),
                    Arrays.asList("샷추가", "변영무"),
                    Arrays.asList("샷추가", "현규영무섹스")
            );
            listOptionSelected = Arrays.asList(
                    Arrays.asList(false,false,false, false, false, false, false, false),
                    Arrays.asList(false, false),
                    Arrays.asList(false, false),
                    Arrays.asList(false, false),
                    Arrays.asList(false, false),
                    Arrays.asList(false, false),
                    Arrays.asList(false, false),
                    Arrays.asList(false, false),
                    Arrays.asList(false, false),
                    Arrays.asList(false, false)
            );
        } else if (storeData.name.equals("스타벅스")) {

            menuName = Arrays.asList("스타벅스꺼 dasdf아메리카아", "녹차 프라페", "미숫가루", "카페라떼", "망고스무디",
                    "체리스무디", "토마토주스", "자바칩프라페", "타로 버블티", "딸기 요거트 스무디");

            listIcon = Arrays.asList(
                    R.drawable.sample_ahah, R.drawable.sample_cafelette, R.drawable.sample_mangosm,
                    R.drawable.sample_javafrape, R.drawable.sample_tarobubble, R.drawable.sample_ahah, R.drawable.sample_cafelette, R.drawable.sample_mangosm,
                    R.drawable.sample_javafrape, R.drawable.sample_tarobubble
            );
            listPrice = Arrays.asList(
                    1200, 1500, 2000,1000,1000,3000,
                    1000,1000,1000,1000
            );
            options = Arrays.asList(
                    Arrays.asList("옵션1", "옵션2","옵션3", "옵션4", "옵션5", "옵션6", "옵션7", "옵션8"),
                    Arrays.asList("샷추가", "고추"),
                    Arrays.asList("샷추가", "원샷"),
                    Arrays.asList("샷추가", "노현규"),
                    Arrays.asList("샷추가", "고기추가"),
                    Arrays.asList("샷추가", "쌈무추가"),
                    Arrays.asList("샷추가", "그르릉"),
                    Arrays.asList("샷추가", "섹스"),
                    Arrays.asList("샷추가", "변영무"),
                    Arrays.asList("샷추가", "현규영무섹스")
            );
            listOptionSelected = Arrays.asList(
                    Arrays.asList(false,false,false, false, false, false, false, false),
                    Arrays.asList(false, false),
                    Arrays.asList(false, false),
                    Arrays.asList(false, false),
                    Arrays.asList(false, false),
                    Arrays.asList(false, false),
                    Arrays.asList(false, false),
                    Arrays.asList(false, false),
                    Arrays.asList(false, false),
                    Arrays.asList(false, false)
            );
        } else {

            menuName = Arrays.asList("노이스 기타드등", "녹차 프라페", "미숫가루", "카페라떼", "망고스무디",
                    "체리스무디", "토마토주스", "자바칩프라페", "타로 버블티", "딸기 요거트 스무디");

            listIcon = Arrays.asList(
                    R.drawable.sample_ahah, R.drawable.sample_cafelette, R.drawable.sample_mangosm,
                    R.drawable.sample_javafrape, R.drawable.sample_tarobubble, R.drawable.sample_ahah, R.drawable.sample_cafelette, R.drawable.sample_mangosm,
                    R.drawable.sample_javafrape, R.drawable.sample_tarobubble
            );
            listPrice = Arrays.asList(
                    1200, 1500, 2000,1000,1000,3000,
                    1000,1000,1000,1000
            );
            options = Arrays.asList(
                    Arrays.asList("옵션1", "옵션2","옵션3", "옵션4", "옵션5", "옵션6", "옵션7", "옵션8"),
                    Arrays.asList("샷추가", "고추"),
                    Arrays.asList("샷추가", "원샷"),
                    Arrays.asList("샷추가", "노현규"),
                    Arrays.asList("샷추가", "고기추가"),
                    Arrays.asList("샷추가", "쌈무추가"),
                    Arrays.asList("샷추가", "그르릉"),
                    Arrays.asList("샷추가", "섹스"),
                    Arrays.asList("샷추가", "변영무"),
                    Arrays.asList("샷추가", "현규영무섹스")
            );
            listOptionSelected = Arrays.asList(
                    Arrays.asList(false,false,false, false, false, false, false, false),
                    Arrays.asList(false, false),
                    Arrays.asList(false, false),
                    Arrays.asList(false, false),
                    Arrays.asList(false, false),
                    Arrays.asList(false, false),
                    Arrays.asList(false, false),
                    Arrays.asList(false, false),
                    Arrays.asList(false, false),
                    Arrays.asList(false, false)
            );
        }

        for (int i = 0; i < menuName.size(); i++) {
            // 각 List의 값들을 data 객체에 set 해줍니다.
            MenuData menuData = new MenuData();
            menuData.setName(menuName.get(i));
            menuData.setImg(listIcon.get(i));
            menuData.setPrice(listPrice.get(i));
         //   menuData.setOptions((ArrayList<String>) options.get(i));
         //   menuData.setOptionSelected((ArrayList<Boolean>) listOptionSelected.get(i));
            // 각 값이 들어간 data를 adapter에 추가합니다.
            adapter2.addItem(menuData);
        }
        // adapter의 이 변경되었다는 것을 알려줍니다.
        adapter2.notifyDataSetChanged();

    }

}