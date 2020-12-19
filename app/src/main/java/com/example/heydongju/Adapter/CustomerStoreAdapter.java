package com.example.heydongju.Adapter;

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

import com.example.heydongju.Customer.CustomerMenuFragment;
import com.example.heydongju.Data.CustomerOrderData;
import com.example.heydongju.Data.StoreData;
import com.example.heydongju.MainActivity;
import com.example.heydongju.R;

import java.util.ArrayList;

public class CustomerStoreAdapter extends RecyclerView.Adapter<CustomerStoreAdapter.StoreViewHolder> {
    private ArrayList<StoreData> store;
    private Context context;

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

        View view = inflater.inflate(R.layout.item_store, parent, false);
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
                    StoreData store = new StoreData(currentIcon, currentName);
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

        public void bind(int icon, String name){
            this.icon.setBackgroundResource(icon);
            this.name.setText(name);

            this.currentName = name;
            this.currentIcon = icon;
        }
    }
}