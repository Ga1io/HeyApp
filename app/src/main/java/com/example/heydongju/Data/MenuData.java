package com.example.heydongju.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class MenuData implements Serializable {
    public static final int NO_IMG = -1;
    public boolean selected=false;
    public int amount;
    public String name;
    public int price=0;
    public int img = -1;

    public ArrayList<String> options=new ArrayList<>();

    public ArrayList<Boolean> getOptionSelected() {
        return optionSelected;
    }

    public void setOptionSelected(ArrayList<Boolean> optionSelected) {
        this.optionSelected = optionSelected;
    }

    public ArrayList<Boolean> optionSelected=new ArrayList<>();

    public ArrayList<String> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<String> options) {
        this.options = options;
    }


    public boolean isSelected() {
        return selected;
    }

    public static int getNoImg() {
        return NO_IMG;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }



    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    // Key: 옵션이름(샷추가, 펄추가), Value: 가격(500원)



}