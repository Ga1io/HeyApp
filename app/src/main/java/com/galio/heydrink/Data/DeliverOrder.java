package com.galio.heydrink.Data;

import java.util.ArrayList;
import java.util.HashMap;

public class DeliverOrder implements Order {
    private User deliver;

    private ArrayList<String> destinations = new ArrayList<>();
    private HashMap<String, ArrayList<User>> destUser;
    private HashMap<String, String> destTime;
    private HashMap<User, ArrayList<String>> menu;

    public DeliverOrder(User deliver){
        this.deliver = deliver;
    }

    public User getDeliver() {
        return deliver;
    }

    public ArrayList<String> getDestinations(){
        return destinations;
    }

    public HashMap<String, ArrayList<User>> getDestUser() {
        return destUser;
    }

    public HashMap<String, String> getDestTime() {
        return destTime;
    }

    public HashMap<User, ArrayList<String>> getMenu() {
        return menu;
    }

    public void setDestinations(ArrayList<String> destinations) {
        this.destinations = destinations;
    }

    public void setDestUser(HashMap<String, ArrayList<User>> destUser) {
        this.destUser = destUser;
    }

    public void setDestTime(HashMap<String, String> destTime) {
        this.destTime = destTime;
    }

    public void setMenu(HashMap<User, ArrayList<String>> menu) {
        this.menu = menu;
    }
}
