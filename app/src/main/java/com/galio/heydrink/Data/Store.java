package com.galio.heydrink.Data;

import java.io.Serializable;

public class Store implements Serializable {
    public int icon;
    public String name;
    public double lang;
    public double lng;
    public double rate;
    public String address;

    public Store(){

    }

    public Store(String name, int icon){
        this.name = name;
        this.icon = icon;
    }
}
