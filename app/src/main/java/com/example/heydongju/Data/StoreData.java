package com.example.heydongju.Data;

import java.io.Serializable;

public class StoreData implements Serializable {
    public int icon;
    public String name="";
    public double lang;
    public double lng;
    public double rate;

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLang() {
        return lang;
    }

    public void setLang(double lang) {
        this.lang = lang;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String address;

    public StoreData(){

    }

    public StoreData(String name, int icon){
        this.name = name;
        this.icon = icon;
    }
}
