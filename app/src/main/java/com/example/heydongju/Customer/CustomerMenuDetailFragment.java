package com.example.heydongju.Customer;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.heydongju.Data.Me;
import com.example.heydongju.Data.MenuData;
import com.example.heydongju.Data.StoreData;
import com.example.heydongju.MainActivity;
import com.example.heydongju.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CustomerMenuDetailFragment extends Fragment {

    private MenuData menu;

    private ImageView menuImg;
    private TextView menuName;
    private TextView menuInfo;
    private MainActivity activity;
    private ImageButton orderBtn;
    private StoreData store;
    private RelativeLayout home;
    private RelativeLayout mylist;
    private RelativeLayout search;
    private RelativeLayout mypage;


    // 체크박스와 옵션 이름 매핑해주는 변수
    private HashMap<CheckBox, TextView> optionNameMap = new HashMap<>();

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.customer_menu_detail, container, false);
        activity=(MainActivity) getActivity();
        init (root);
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
                    Navigation.findNavController(root).navigate(R.id.action_customerMenuDetailFragment_to_nav_customer_home);

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

                    Navigation.findNavController(root).navigate(R.id.action_customerMenuDetailFragment_to_nav_customer_mylist);

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

                    Navigation.findNavController(root).navigate(R.id.action_customerMenuDetailFragment_to_nav_customer_mypage);


                }
            }
        });
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void init(View v){
        this.menu = (MenuData) getArguments().getSerializable("menu");
        this.store = (StoreData) getArguments().getSerializable("store");

        menuImg = v.findViewById(R.id.menuImg);
        menuName = v.findViewById(R.id.menuName);
        menuInfo = v.findViewById(R.id.menuInfo);

        orderBtn = v.findViewById(R.id.orderBtn);

        if (menu.img != MenuData.NO_IMG){
            menuImg.setBackgroundResource(menu.img);
        }

        menuName.setText(menu.name);
        menuInfo.setText(menu.info);

        orderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "장바구니에 담겼습니다.", Snackbar.LENGTH_SHORT).show();

                ArrayList<MenuData.Option> options = new ArrayList<>();

                for (Map.Entry<CheckBox, TextView> optionEntry : optionNameMap.entrySet()){
                    if (optionEntry.getKey().isChecked()){
                        MenuData.Option selectedOption = MenuData.Option.findByName(menu, optionEntry.getValue().getText().toString());

                        if (selectedOption != null){
                            options.add(selectedOption);
                        }
                    }
                }

                Me.getInstance().cart.put(menu, options);

                Bundle bundle = new Bundle();
                bundle.putSerializable("store", store);
                Navigation.findNavController(view).navigate(R.id.action_customerMenuDetailFragment_to_navigation, bundle);
            }
        });

        makeOptions(v);




    }



    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void makeOptions(View v){
        if (menu.options.size() > 0){
            LinearLayout lr = v.findViewById(R.id.optionScrollLinearLayout);

            for (int i =0; i<menu.options.size(); i++) {
                MenuData.Option op = menu.options.get(i);

                // TextView 2개, RadioButton 1개 생성
                TextView optionName = new TextView(v.getContext());
                TextView optionPrice = new TextView(v.getContext());
                CheckBox optionCheck = new CheckBox(v.getContext());
                RelativeLayout rl = new RelativeLayout(v.getContext());

                optionNameMap.put(optionCheck, optionName);

                // LayoutParameter 생성
                RelativeLayout.LayoutParams nameParam = new RelativeLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                RelativeLayout.LayoutParams priceParam = new RelativeLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                RelativeLayout.LayoutParams checkParam = new RelativeLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                // ID 생성
                int nameID = v.generateViewId();
                int priceID = v.generateViewId();
                int checkID = v.generateViewId();

                optionName.setId(nameID);
                optionPrice.setId(priceID);
                optionCheck.setId(checkID);

                // 텍스트 입력
                optionName.setText(op.name);
                optionPrice.setText(op.price + "원");

                // LayoutParameter 설정
                nameParam.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                checkParam.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                priceParam.addRule(RelativeLayout.LEFT_OF, checkID);

                // 등록
                optionName.setLayoutParams(nameParam);
                rl.addView(optionName, nameParam);

                optionCheck.setLayoutParams(checkParam);
                rl.addView(optionCheck, checkParam);

                optionPrice.setLayoutParams(priceParam);
                rl.addView(optionPrice, priceParam);

                lr.addView(rl);
            }
        }
    }
}