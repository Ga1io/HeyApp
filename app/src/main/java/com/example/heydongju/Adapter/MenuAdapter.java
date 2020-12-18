package com.example.heydongju.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;


import com.example.heydongju.Customer.CustomerMenuFragment;
import com.example.heydongju.Data.MenuData;
import com.example.heydongju.Data.StoreData;
import com.example.heydongju.MainActivity;
import com.example.heydongju.R;

import java.util.ArrayList;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {
    private ArrayList<MenuData> data = new ArrayList<>();
    private Context mContext;
    private StoreData store;

    public MenuAdapter(StoreData store) {
        this.store = store;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        mContext = parent.getContext();

        View view = inflater.inflate(R.layout.menu_item, parent, false);
        MenuViewHolder holder = new MenuViewHolder(view, mContext, store);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        holder.onBind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setMenu(ArrayList<MenuData> data) {
        this.data = data;
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView menuImg;
        private TextView menuName;
        private TextView menuPrice;

        private Context context;
        private RelativeLayout relativeLayout;

        private MenuData currentMenu = null;
        private StoreData store;

        public MenuViewHolder(@NonNull View itemView, Context context, StoreData store) {
            super(itemView);
            this.context = context;
            this.store = store;

            menuImg = itemView.findViewById(R.id.menuImg);
            menuName = itemView.findViewById(R.id.menuName);
            menuPrice = itemView.findViewById(R.id.menuPrice);
            relativeLayout = itemView.findViewById(R.id.menuItemRelativeLayout);

            relativeLayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.menuItemRelativeLayout:
                    CustomerMenuFragment.currentFragment="selected";

                    Bundle data = new Bundle();
                    data.putSerializable("store", store);
                    data.putSerializable("menu", currentMenu);
                    Navigation.findNavController(view).navigate(R.id.action_nav_customer_select_menu_to_customerMenuDetailFragment, data);
                    break;
            }
        }

        public void onBind(MenuData menu) {
            this.currentMenu = menu;

            // 사진 있으면 사진 등록
            if (menu.img != MenuData.NO_IMG) {
                menuImg.setBackgroundResource(menu.img);
            }

            // 기타 메뉴 정보 설정
            menuName.setText(menu.name);
            menuPrice.setText(menu.price + "원");

            // 옵션 동적 생성
            int textId = R.id.menuName;
            for (int i = 0; i < currentMenu.options.size(); i++) {
                TextView optionText = new TextView(context);
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                MenuData.Option op = currentMenu.options.get(i);

                String option = op.name + " " + op.price + "원";
                optionText.setText(option);

                params.addRule(RelativeLayout.BELOW, textId);
                params.addRule(RelativeLayout.ALIGN_LEFT, R.id.menuName);

                textId = View.generateViewId();
                optionText.setId(textId);
                optionText.setLayoutParams(params);

                relativeLayout.addView(optionText, params);
            }

            // 옵션 생성 후 가격 위치를 옵션 밑으로 설정
            if (menu.options.size() > 0) {
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                params.addRule(RelativeLayout.BELOW, textId);
                params.addRule(RelativeLayout.ALIGN_LEFT, R.id.menuName);

                menuPrice.setLayoutParams(params);
            }
        }
    }
}
