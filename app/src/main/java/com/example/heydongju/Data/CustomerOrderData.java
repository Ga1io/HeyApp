package com.example.heydongju.Data;

import java.util.ArrayList;

public class CustomerOrderData {
    public CustomerOrderData() {
    }

    private StoreData storeData = new StoreData();
    private ArrayList<MenuData> menuData = new ArrayList<>();

    public StoreData getStoreData77777777() {
        return storeData;
    }

    public void setStoreData(StoreData storeData) {
        this.storeData = storeData;
    }

    public ArrayList<MenuData> getMenuData() {
        return menuData;
    }

    public void setMenuData(ArrayList<MenuData> menuData) {
        this.menuData = menuData;
    }

    public ArrayList<Integer> getAmount() {
        return amount;
    }

    public void setAmount(ArrayList<Integer> amount) {
        this.amount = amount;
    }

    private ArrayList<Integer> amount = new ArrayList<>();
}
