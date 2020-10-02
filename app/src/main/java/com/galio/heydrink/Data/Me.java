package com.galio.heydrink.Data;

import java.util.ArrayList;

public class Me extends User {
    private static final Me ourInstance = new Me();

    private ArrayList<Menu> cart = new ArrayList<>();
    private String demand;

    public static Me getInstance() {
        return ourInstance;
    }

    private Me() {
    }
}
