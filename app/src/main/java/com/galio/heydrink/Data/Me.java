package com.galio.heydrink.Data;

import java.util.ArrayList;
import java.util.HashMap;

public class Me extends User {
    private static final Me ourInstance = new Me();

    // 장바구니
    public HashMap<Menu, ArrayList<Menu.Option>> cart = new HashMap();

    // 요구사항
    public String demand;

    // 주소 ex, 중앙대학교
    public String address;

    // 장소 ex, 310관
    public ArrayList<String> building = new ArrayList<>();

    public static Me getInstance() {
        return ourInstance;
    }

    private Me() {
    }
}
