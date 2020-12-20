package com.example.heydongju.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.heydongju.Data.AdvertiseData;
import com.example.heydongju.R;

import java.util.ArrayList;

public class AdvertiseAdapter extends RecyclerView.Adapter<AdvertiseAdapter.ItemViewHolder> {

    // adapter에 들어갈 list 입니다.
    private ArrayList<AdvertiseData> listData = new ArrayList<>();
    Context context;
    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // LayoutInflater를 이용하여 전 단계에서 만들었던 item.xml을 inflate 시킵니다.
        // return 인자는 ViewHolder 입니다.
        context=parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_adver, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        // Item을 하나, 하나 보여주는(bind 되는) 함수입니다.
        holder.onBind(listData.get(position));
    }

    @Override
    public int getItemCount() {
        // RecyclerView의 총 개수 입니다.
        return listData.size();
    }

    public void addItem(AdvertiseData data) {
        // 외부에서 item을 추가시킬 함수입니다.
        listData.add(data);
    }

    // RecyclerView의 핵심인 ViewHolder 입니다.
    // 여기서 subView를 setting 해줍니다.
    class ItemViewHolder extends RecyclerView.ViewHolder {


        private ImageView recommendIcon;
        private TextView content;
        private TextView title;
        private TextView content2;

        ItemViewHolder(View itemView) {
            super(itemView);

            recommendIcon = itemView.findViewById(R.id.image);
        }

        void onBind(AdvertiseData data) {
            recommendIcon.setImageResource(data.getRecommendImg());
        }
    }



}