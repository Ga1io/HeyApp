package com.example.heydongju.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.heydongju.Data.StoreData;
import com.example.heydongju.Data.StoreRecommend;
import com.example.heydongju.MainActivity;
import com.example.heydongju.R;

import java.util.ArrayList;

public class StoreRecommendAdapter extends RecyclerView.Adapter<StoreRecommendAdapter.ItemViewHolder> {

    // adapter에 들어갈 list 입니다.
    public ArrayList<StoreRecommend> store = new ArrayList<>();

    private Context context;
    // Item의 클릭 상태를 저장할 array 객체
    // 직전에 클릭됐던 Item의 position
    private int prePosition = -1;

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // LayoutInflater를 이용하여 전 단계에서 만들었던 item.xml을 inflate 시킵니다.
        // return 인자는 ViewHolder 입니다.
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_store, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        // Item을 하나, 하나 보여주는(bind 되는) 함수입니다.
        String storeName = store.get(position).name;
        int storeIcon = store.get(position).icon;

        holder.onBind(storeIcon, storeName);
    }

    @Override
    public int getItemCount() {
        // RecyclerView의 총 개수 입니다.
        return store.size();
    }

    public void addItem(StoreRecommend data) {
        // 외부에서 item을 추가시킬 함수입니다.
        store.add(data);
    }
    public void deleteItem(StoreRecommend data) {
        // 외부에서 item을 추가시킬 함수입니다.
        store.remove(data);
    }

    // RecyclerView의 핵심인 ViewHolder 입니다.
    // 여기서 subView를 setting 해줍니다.
    class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private CardView cardView;
        private ImageView icon;
        private TextView name;

        private int currentIcon;
        private String currentName;

        ItemViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.storeGridItem);
            icon = itemView.findViewById(R.id.storeIcon);
            name = itemView.findViewById(R.id.storeName);

            cardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.storeGridItem:
                    StoreData store = new StoreData(currentName, currentIcon);
                    Bundle data = new Bundle();
                    data.putSerializable("store", store);
                    MainActivity activity = (MainActivity) context;

                    if(activity.navController.getCurrentDestination().getId()==R.id.nav_store){
                        Navigation.findNavController(view).navigate(R.id.action_nav_customer_select_store_to_nav_customer_select_menu, data);
                    }
                    else{
                        Navigation.findNavController(view).navigate(R.id.action_nav_customer_home_to_nav_customer_select_menu, data);
                    }


                    break;
            }
        }

        void onBind(int icon, String name) {
            this.icon.setBackgroundResource(icon);
            this.name.setText(name);

            this.currentName = name;
            this.currentIcon = icon;


        }


    }
}