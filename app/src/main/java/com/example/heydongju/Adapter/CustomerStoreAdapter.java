package com.example.heydongju.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.heydongju.Data.MenuData;
import com.example.heydongju.Data.StoreData;
import com.example.heydongju.MainActivity;
import com.example.heydongju.R;

import java.util.ArrayList;
import java.util.Collections;

public class CustomerStoreAdapter extends RecyclerView.Adapter<CustomerStoreAdapter.StoreViewHolder> {
    public ArrayList<StoreData> store;
    private Context context;
    public StoreData storeData;
    public CustomerStoreAdapter(){
        store = new ArrayList<>();
    }

    public CustomerStoreAdapter(ArrayList<StoreData> store) {
        this.store = store;
    }

    public void setStores(ArrayList<StoreData> store){
        this.store = store;
    }

    @NonNull
    @Override
    public StoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        storeData= new StoreData();
        View view = inflater.inflate(R.layout.item_store, parent, false);
        StoreViewHolder holder = new StoreViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull StoreViewHolder holder, int position) {
        String storeName = store.get(position).name;
        int storeIcon = store.get(position).icon;

        holder.bind(store.get(position),storeIcon, storeName,position);
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
        return store.size();
    }
    public void addItem(StoreData data) {
        // 외부에서 item을 추가시킬 함수입니다.
        store.add(data);
    }
    public class StoreViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private CardView cardView;
        private ImageView icon;
        private TextView name;
        private int position;
        private StoreData data;

        private RelativeLayout wow;
        private int currentIcon;
        private String currentName;

        public StoreViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.storeGridItem);
            icon = itemView.findViewById(R.id.storeIcon);
            name = itemView.findViewById(R.id.storeName);

            wow = itemView.findViewById(R.id.wow);
            cardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.storeGridItem:
                    storeData.setName(currentName);
                    storeData.setIcon(currentIcon);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("store", storeData);
                    bundle.putSerializable("stores", store);

                    MainActivity activity = (MainActivity) context;
                    if(activity.navController.getCurrentDestination().getId()==R.id.nav_train) {
                        Navigation.findNavController(view).navigate(R.id.self_train, bundle);

                    }else if(activity.navController.getCurrentDestination().getId()==R.id.nav_recommend) {
                        Navigation.findNavController(view).navigate(R.id.self_recommend, bundle);
                    }
                    else{
                        Navigation.findNavController(view).navigate(R.id.self, bundle);
                       // Collections.swap(store,position, 0);
                    }
                    break;
            }
        }

        public void bind(StoreData data,int icon, String name, int poisiton){
            this.icon.setBackgroundResource(icon);
            this.name.setText(name);
            this.position = position;

            this.data=data;
            this.currentName = name;
            this.currentIcon = icon;

            if(data.isSelected()){
                wow.setSelected(true);
            }


        }
    }
}