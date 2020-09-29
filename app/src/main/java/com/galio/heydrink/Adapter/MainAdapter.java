package com.galio.heydrink.Adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.galio.heydrink.Data.Order;

import java.io.Serializable;
import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainViewHolder> implements Serializable {
    protected ArrayList<Order> orders = new ArrayList<>();

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public void addOrder(Order deliverOrder){
        orders.add(deliverOrder);
    }

    public void addOrders(ArrayList<Order> deliverOrders){
        this.orders.addAll(deliverOrders);
    }
}
