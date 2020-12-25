package com.example.heydongju.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.heydongju.Data.MenuData;
import com.example.heydongju.Data.StoreData;
import com.example.heydongju.R;

import java.util.ArrayList;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {
    public ArrayList<MenuData> data = new ArrayList<>();
    private Context mContext;
    private StoreData store;
    private int prePosition = -1;

    public MenuAdapter() {
    }

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
        holder.onBind(data.get(position), position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setMenu(ArrayList<MenuData> data) {
        this.data = data;
    }
    public void addItem(MenuData menu) {
        // 외부에서 item을 추가시킬 함수입니다.
        data.add(menu);
    }
    public class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView menuImg;
        private TextView menuName;
        private TextView menuPrice;
        private int position;

        private Context context;
        private RelativeLayout relativemenu;

        private MenuData currentMenu = null;
        private StoreData store;

        public MenuViewHolder(@NonNull View itemView, Context context, StoreData store) {
            super(itemView);
            this.context = context;
            this.store = store;

            menuImg = itemView.findViewById(R.id.menuImg);
            menuName = itemView.findViewById(R.id.menuName);
            menuPrice = itemView.findViewById(R.id.menuPrice);
   //         relativeLayout = itemView.findViewById(R.id.menuItemRelativeLayout);
            relativemenu = itemView.findViewById(R.id.menu);

            relativemenu.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.menu:
                    if(currentMenu.selected){
                        currentMenu.selected=false;

                    }else{
                        currentMenu.selected=true;

                    }

                    //Bundle data = new Bundle();
                    //data.putSerializable("store", store);
                    //data.putSerializable("menu", currentMenu);
                    notifyDataSetChanged();
                    //Navigation.findNavController(view).navigate(R.id.action_nav_customer_select_menu_to_customerMenuDetailFragment, data);
                    break;
            }
        }

        public void onBind(MenuData menu, int position) {
            this.currentMenu = menu;
            this.position = position;

            // 사진 있으면 사진 등록
            if (menu.img != MenuData.NO_IMG) {
                menuImg.setBackgroundResource(menu.img);
            }

            // 기타 메뉴 정보 설정
            menuName.setText(menu.name);
            menuPrice.setText(menu.price + "원");


            if(menu.selected==true){
                relativemenu.setSelected(true);
            }else{
                relativemenu.setSelected(false);
            }


        }
    }
}
