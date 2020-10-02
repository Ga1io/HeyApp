package com.galio.heydrink.ui.customer;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.galio.heydrink.Data.Menu;
import com.galio.heydrink.Data.Store;
import com.galio.heydrink.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.Map;

public class ShoppingCartFragment extends Fragment {

    private ShoppingCartViewModel mViewModel;
    private Menu menu;

    private ImageView menuImg;
    private TextView menuName;
    private TextView menuInfo;

    private ImageButton orderBtn;
    private Store store;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.customer_shopping_cart_fragment, container, false);

        init (root);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ShoppingCartViewModel.class);
        // TODO: Use the ViewModel
    }

    private void init(View v){
        this.menu = (Menu) getArguments().getSerializable("menu");
        this.store = (Store) getArguments().getSerializable("store");

        menuImg = v.findViewById(R.id.menuImg);
        menuName = v.findViewById(R.id.menuName);
        menuInfo = v.findViewById(R.id.menuInfo);

        orderBtn = v.findViewById(R.id.orderBtn);

        if (menu.img != Menu.NO_IMG){
            menuImg.setBackgroundResource(menu.img);
        }

        menuName.setText(menu.name);
        menuInfo.setText(menu.info);

        orderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "장바구니에 담겼습니다.", Snackbar.LENGTH_SHORT).show();
                Bundle bundle = new Bundle();
                bundle.putSerializable("store", store);
                Navigation.findNavController(view).navigate(R.id.action_customerShoppingCartFragment_to_navigation, bundle);
            }
        });

        makeOptions(v);
    }

    private void makeOptions(View v){
        if (menu.options.size() > 0){
            LinearLayout lr = v.findViewById(R.id.optionScrollLinearLayout);

            for (Map.Entry<String, String> entry : menu.options.entrySet()) {
                // TextView 2개, RadioButton 1개 생성
                TextView optionName = new TextView(v.getContext());
                TextView optionPrice = new TextView(v.getContext());
                RadioButton optionCheck = new RadioButton(v.getContext());
                RelativeLayout rl = new RelativeLayout(v.getContext());

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
                optionName.setText(entry.getKey());
                optionPrice.setText(entry.getValue() + "원");

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