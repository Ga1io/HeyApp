package com.galio.heydrink.Data;

import java.util.ArrayList;
import java.util.HashMap;

public class Me extends User {
    private static final Me ourInstance = new Me();

    // 장바구니
    private HashMap<Menu, ArrayList<Menu.Option>> cart = new HashMap();

    // 요구사항
    private String demand;

    // 주소 ex, 중앙대학교
    private String address;

    // 장소 ex, 310관
    private ArrayList<String> building = new ArrayList<>();

    public static Me getInstance() {
        return ourInstance;
    }

    private Me() {
    }
}
