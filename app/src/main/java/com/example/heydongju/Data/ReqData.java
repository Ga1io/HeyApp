package com.example.heydongju.Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReqData {

    @SerializedName("id")
    @Expose
    private String userId;
    @SerializedName("password")
    @Expose
    private String userPw;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPw() {
        return userPw;
    }

    public void setUserPw(String userPw) {
        this.userPw = userPw;
    }

    public ReqData(String userId, String userPw) {
        this.userPw = userPw;
        this.userId = userId;
    }

}
