package com.galio.heydrink.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.galio.heydrink.Data.Store;
import com.galio.heydrink.R;

import java.util.ArrayList;

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.StoreViewHolder> {
    private ArrayList<Store> store;

    private Integer[] iconIds = {R.drawable.amasvin, R.drawable.angel_in_us, R.drawable.bebridge, R.drawable.burkerking,
            R.drawable.coffeebin, R.drawable.ediya, R.drawable.mcdonald, R.drawable.starbucks, R.drawable.tomtom,
            R.drawable.venti, R.drawable.yogerpresso, R.drawable.coffeenamu};
    private String[] storeNames = {"아마스빈", "엔젤리너스", "베브릿지", "버거킹", "커피빈",
            "이디야", "맥도날드", "스타벅스", "탐앤탐스", "더 벤티", "요거프레소", "커피나무"};

    public StoreAdapter(){
        store = new ArrayList<>();

        for (int i=0; i<iconIds.length; i++){
            store.add(new Store(storeNames[i], iconIds[i]));
        }
    }

    public StoreAdapter(ArrayList<Store> store) {
        this.store = store;
    }

    @NonNull
    @Override
    public StoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.store_item, parent, false);
        StoreViewHolder holder = new StoreViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull StoreViewHolder holder, int position) {
        String storeName = store.get(position).name;
        int storeIcon = store.get(position).icon;

        holder.bind(storeIcon, storeName);
    }

    @Override
    public int getItemCount() {
        return store.size();
    }

    public class StoreViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private CardView cardView;
        private ImageView icon;
        private TextView name;

        private int currentIcon;
        private String currentName;

        public StoreViewHolder(@NonNull View itemView) {
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
                    Store store = new Store(currentName, currentIcon);
                    Bundle data = new Bundle();
                    data.putSerializable("store", store);
                    Navigation.findNavController(view).navigate(R.id.action_nav_customer_select_store_to_nav_customer_select_menu, data);
                    break;
            }
        }

        public void bind(int icon, String name){
            this.icon.setBackgroundResource(icon);
            this.name.setText(name);

            this.currentName = name;
            this.currentIcon = icon;
        }
    }
}
