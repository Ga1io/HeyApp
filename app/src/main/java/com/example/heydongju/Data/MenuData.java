package com.example.heydongju.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class MenuData implements Serializable {
    public static final int NO_IMG = -1;
    public boolean selected=false;

    public String name;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    // Key: 옵션이름(샷추가, 펄추가), Value: 가격(500원)
    public ArrayList<Option> options = new ArrayList<>();
    public String price;
    public int img = -1;
    public String info = "";


    public static class Option{
        private static HashMap<MenuData, ArrayList<Option>> optionHashMap = new HashMap<>();

        private MenuData menu;
        public String name;
        public String price;

        public Option(MenuData menu, String name, String price){
            this.menu = menu;
            this.name = name;
            this.price = price;

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
                    if (ops.get(i).name.equals(name)){
                        op = ops.get(i);
                    }
                }
            }

            return op;
        }
    }
}