package com.galio.heydrink.Data;

public class Store {
    private int icon;
    private String name;

    public Store(int icon, String name){
        this.icon = icon;
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public String getName() {
        return name;
    }
}
