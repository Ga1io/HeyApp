package com.example.heydongju.Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeliverInfoData {
//    public String time;
//    public String deliverName;
//    public Float deliverCups;
//    public Float deliverStar;

    @SerializedName("deliver_time")
    @Expose
    private String deliverTime;

    @SerializedName("deliver_name")
    @Expose
    private String deliverName;

    @SerializedName("deliver_cups")
    @Expose
    private Float deliverCups;

    @SerializedName("deliver_star")
    @Expose
    private Float deliverStar;

    public String getTime() {
        return deliverTime;
    }

    public void setTime(String time) {
        this.deliverTime = deliverTime;
    }

    public String getDeliverName() {
        return deliverName;
    }

    public void setDeliverName(String deliverName) {
        this.deliverName = deliverName;
    }

    public Float getDeliverCups() {
        return deliverCups;
    }

    public void setDeliverCups(Float deliverCups) {
        this.deliverCups = deliverCups;
    }

    public Float getDeliverStar() {
        return deliverStar;
    }

    public void setDeliverStar(Float deliverStar) {
        this.deliverStar = deliverStar;
    }
}

