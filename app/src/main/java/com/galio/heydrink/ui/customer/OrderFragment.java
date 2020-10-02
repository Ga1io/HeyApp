package com.galio.heydrink.ui.customer;

import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.galio.heydrink.Data.Me;
import com.galio.heydrink.Data.Menu;
import com.galio.heydrink.R;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public class OrderFragment extends Fragment {
    private TextView address;
    private TextView totalPriceView;
    private ImageButton orderBtn;

    private int totalPrice = 0;

    public OrderFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.customer_order, container, false);

        init(root);
        return root;
    }

    private void init(View v){
        address = v.findViewById(R.id.address);
        orderBtn = v.findViewById(R.id.orderBtn);
        totalPriceView = v.findViewById(R.id.totalPrice);

        address.setText(Me.getInstance().address);

        addMenu(v);

        totalPriceView.setText(NumberFormat.getInstance().format(totalPrice) + "원");

        orderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_customer_order_to_customer_payment);
            }
        });
    }

    private void addMenu(View v){
        LinearLayout orderLinearLayout = v.findViewById(R.id.orderLinearLayout);
        LinearLayout.LayoutParams orderParam = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        HashMap<Menu, ArrayList<Menu.Option>> cart = Me.getInstance().cart;

        int topId = R.id.foodScheme;

        for (Map.Entry<Menu, ArrayList<Menu.Option>> item : cart.entrySet()) {
            RelativeLayout frame = new RelativeLayout(v.getContext());

            // 메뉴 이름
            TextView menuName = new TextView(v.getContext());
            RelativeLayout.LayoutParams nameParam = new RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            menuName.setText(item.getKey().name);
            int nameId = v.generateViewId();
            menuName.setId(nameId);
            menuName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 17);
            menuName.setTypeface(null, Typeface.BOLD);

            // 이름 Layout Parameter 설정
            int dpValue = 10;
            float d = v.getContext().getResources().getDisplayMetrics().density;
            int marginTop = (int) (dpValue * d);

            nameParam.addRule(RelativeLayout.BELOW, topId);
            nameParam.setMargins(0, marginTop, 0, 0);
            menuName.setLayoutParams(nameParam);

            topId = nameId;

            // 메뉴 가격
            TextView menuPrice = new TextView(v.getContext());
            RelativeLayout.LayoutParams priceParam = new RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            menuPrice.setText(item.getKey().price + "원");
            int priceId = v.generateViewId();
            menuPrice.setId(priceId);

            menuPrice.setTextSize(TypedValue.COMPLEX_UNIT_SP, 17);
            menuPrice.setTypeface(null, Typeface.BOLD);

            // 가격 Layout Parameter 설정
            priceParam.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            priceParam.addRule(RelativeLayout.ALIGN_TOP, nameId);
            menuPrice.setLayoutParams(nameParam);

            // 메뉴 가격 더하기
            NumberFormat format = NumberFormat.getInstance(Locale.US);
            Number number = null;
            try {
                number = format.parse(item.getKey().price);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            int price = number.intValue();

            totalPrice += price;

            // 옵션 입력
            if (item.getValue() != null){
                ArrayList<Menu.Option> options = item.getValue();
                for (int i =0; i<options.size(); i++){
                    // 옵션 이름 설정
                    TextView optionName = new TextView(v.getContext());

                    RelativeLayout.LayoutParams optionNameParam = new RelativeLayout.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                    int optionId = v.generateViewId();
                    optionName.setText("   " + options.get(i).name);
                    optionName.setTextSize(TypedValue.COMPLEX_UNIT_SP, 17);
                    optionName.setId(optionId);

                    // 옵션 이름 Layout Parameter 설정
                    optionNameParam.addRule(RelativeLayout.BELOW, topId);
                    optionName.setLayoutParams(optionNameParam);
                    topId = optionId;

                    // 옵션 가격 설정
                    TextView optionPrice = new TextView(v.getContext());

                    RelativeLayout.LayoutParams optionPriceParam = new RelativeLayout.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                    // 가격 입력
                    optionPrice.setText(options.get(i).price + "원");
                    optionPrice.setTextSize(TypedValue.COMPLEX_UNIT_SP, 17);

                    // 가격 Layout Parameter 설정
                    optionPriceParam.addRule(RelativeLayout.ALIGN_TOP, optionId);
                    optionPriceParam.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                    optionPrice.setLayoutParams(optionPriceParam);

                    frame.addView(optionName, optionNameParam);
                    frame.addView(optionPrice, optionPriceParam);
                }
            }

            frame.addView(menuName, nameParam);
            frame.addView(menuPrice, priceParam);
            orderLinearLayout.addView(frame, orderParam);
        }
    }
}