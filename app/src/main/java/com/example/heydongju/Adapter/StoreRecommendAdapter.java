package com.example.heydongju.Adapter;

import android.content.Context;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.heydongju.Data.StoreRecommend;
import com.example.heydongju.R;

import java.util.ArrayList;

public class StoreRecommendAdapter extends RecyclerView.Adapter<StoreRecommendAdapter.ItemViewHolder> {

    // adapter에 들어갈 list 입니다.
    public ArrayList<StoreRecommend> listData = new ArrayList<>();

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
        holder.onBind(listData.get(position), position);
    }

    @Override
    public int getItemCount() {
        // RecyclerView의 총 개수 입니다.
        return listData.size();
    }

    public void addItem(StoreRecommend data) {
        // 외부에서 item을 추가시킬 함수입니다.
        listData.add(data);
    }
    public void deleteItem(StoreRecommend data) {
        // 외부에서 item을 추가시킬 함수입니다.
        listData.remove(data);
    }

    // RecyclerView의 핵심인 ViewHolder 입니다.
    // 여기서 subView를 setting 해줍니다.
    class ItemViewHolder extends RecyclerView.ViewHolder{

        private ImageView storeIcon;
        private TextView storeName;
        private StoreRecommend data;

        ItemViewHolder(View itemView) {
            super(itemView);

            storeIcon=itemView.findViewById(R.id.storeIcon);
            storeName=itemView.findViewById(R.id.storeName);
        }

        void onBind(StoreRecommend data, int position) {
            this.data = data;

            storeIcon.setImageResource(data.getStoreIcon());
            storeName.setText(data.getStoreName());

        }


    }
}