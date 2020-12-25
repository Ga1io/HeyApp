package com.example.heydongju.Data;

import java.io.Serializable;
import java.util.ArrayList;

public class CustomerOrderData implements Serializable {



    private StoreData storeData = new StoreData();
    private ArrayList<MenuData> menuData = new ArrayList<>();
    private String place;
    private String request;
    private int priceSum;

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public int getPriceSum() {
        return priceSum;
    }

    public void setPriceSum(int priceSum) {
        this.priceSum = priceSum;
    }

    public CustomerOrderData() {
    }


    public StoreData getStoreData() {
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


}
