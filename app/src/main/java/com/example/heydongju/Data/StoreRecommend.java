package com.example.heydongju.Data;

public class StoreRecommend {

    public int icon;
    public String name;
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

    public StoreRecommend(){

    }

    public StoreRecommend(String name, int icon){
        this.name = name;
        this.icon = icon;
    }
}
