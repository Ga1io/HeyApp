package com.example.heydongju.Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class StoreData implements Serializable {

    public String name;
    public int icon;

    @SerializedName("store_icon")
    @Expose
    private int storeIcon;

    @SerializedName("store_name")
    @Expose
    private String storeName;

    @SerializedName("store_lang")
    @Expose
    private double storeLang;

    @SerializedName("store_lng")
    @Expose
    private double storeLng;

    @SerializedName("store_rate")
    @Expose
    private double storeRate;

    @SerializedName("store_address")
    @Expose
    private String storeAddress;

    public StoreData(int storeIcon, String storeName ){
        this.storeIcon = storeIcon;
        this.storeName = storeName;
//        this.storeLang = storeLang;
//        this.storeLng = storeLng;
//        this.storeRate = storeRate;
//        this.storeAddress = storeAddress;
    }


    public int getIcon() {
        return storeIcon;
    }

    public void setIcon(int icon) {
        this.storeIcon = storeIcon;
    }

    public String getName() {
        return storeName;
    }

    public void setName(String name) {
        this.storeName = storeName;
    }

    public double getLang() {
        return storeLang;
    }

    public void setLang(double lang) {
        this.storeLang = storeLang;
    }

    public double getLng() {
        return storeLng;
    }

    public void setLng(double lng) {
        this.storeLng = storeLng;
    }

    public double getRate() {
        return storeRate;
    }

    public void setRate(double rate) {
        this.storeRate = storeRate;
    }

    public String getAddress() {
        return storeAddress;
    }

    public void setAddress(String address) {
        this.storeAddress = storeAddress;
    }

}
