package com.galio.heydrink.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Menu implements Serializable {
    public static final int NO_IMG = -1;

    public String name;

    // Key: 옵션이름(샷추가, 펄추가), Value: 가격(500원)
    public ArrayList<Option> options = new ArrayList<>();
    public String price;
    public int img = -1;
    public String info = "";

    public static class Option{
        public String name;
        public String price;

        public Option(){

        }

        public Option(String name, String price){
            this.name = name;
            this.price = price;
        }
    }
}
