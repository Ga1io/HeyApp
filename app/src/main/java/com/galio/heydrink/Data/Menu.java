package com.galio.heydrink.Data;

import java.util.HashMap;

public class Menu {
    public String name;

    // Key: 옵션이름(샷추가, 펄추가), Value: 가격(500원)
    public HashMap<String, String> options = new HashMap<>();
    public String price;
    public int img = -1;
}
