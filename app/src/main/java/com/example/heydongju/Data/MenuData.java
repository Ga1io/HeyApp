package com.example.heydongju.Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class MenuData implements Serializable {
    public static final int NO_IMG = -1;

    public String name;

    // Key: 옵션이름(샷추가, 펄추가), Value: 가격(500원)
    public ArrayList<Option> options = new ArrayList<>();
    public String price;
    public int img = -1;
    public String info = "";

    @SerializedName("menu_name")
    @Expose
    private String menuName;

    @SerializedName("menu_price")
    @Expose
    private String menuPrice;

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuPrice() {return menuPrice;}

    public void setMenuPrice(String menuPrice) {this.menuPrice = menuPrice;}


    public static class Option{
        private static HashMap<MenuData, ArrayList<Option>> optionHashMap = new HashMap<>();

        private MenuData menu;
        public String menuName;
        public String menuPrice;

        public Option(MenuData menu, String menuName, String menuPrice){
            this.menu = menu;
            this.menuName = menuName;
            this.menuPrice = menuPrice;

            if (optionHashMap.containsKey(menu)){
                optionHashMap.get(menu).add(this);
            }else{
                ArrayList<Option> ops = new ArrayList<>();
                ops.add(this);

                optionHashMap.put(menu, ops);
            }
        }

        public static Option findByName(MenuData menu, String name){
            Option op = null;

            ArrayList<Option> ops = optionHashMap.get(menu);

            if (ops != null){
                for (int i =0; i<ops.size(); i++){
                    if (ops.get(i).menuName.equals(name)){
                        op = ops.get(i);
                    }
                }
            }

            return op;
        }
    }
}