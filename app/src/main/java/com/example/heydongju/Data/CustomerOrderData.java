package com.example.heydongju.Data;

import java.util.ArrayList;

public class CustomerOrderData {
    public ArrayList<StoreData> getListStore() {
        return listStore;
    }

    public void setListStore(ArrayList<StoreData> listStore) {
        this.listStore = listStore;
    }

    private ArrayList<StoreData> listStore = new ArrayList<>();

}
