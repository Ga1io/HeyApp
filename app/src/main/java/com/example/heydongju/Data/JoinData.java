package com.example.heydongju.Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.$Gson$Preconditions;

public class JoinData {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("passwordconfirm")
    @Expose
    private String repassword;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("cauid")
    @Expose
    private String cauid;
    @SerializedName("birth")
    @Expose
    private String birth;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("phonenum")
    @Expose
    private String phonenum;

    public JoinData(String id, String password, String repassword, String name, String cauid, String birth, String email, String phonenum){
        this.id=id;
        this.password=password;
        this.repassword=repassword;
        this.name=name;
        this.cauid=cauid;
        this.birth=birth;
        this.email=email;
        this.phonenum=phonenum;
    }

    public String getJoinId() {return id;}
    public void setJoinId(String id) { this.id=id;}

    public String getJoinPassword() {return password;}
    public void setJoinPassword(String password) { this.password=password; }

    public String getJoinRePassword() {return repassword; }
    public void setJoinRePassword(String repassword) {this.repassword=repassword; }

    public String getJoinName() {return name;}
    public void setJoinName(String name) {this.name=name; }

    public String getJoinCauId() {return cauid; }
    public void setJoinCauId(String cauid) { this.cauid=cauid; }

    public String getJoinBirth() {return birth; }
    public void setJoinBirth(String birth) { this.birth=birth;}

    public String getJoinEmail() {return email;}
    public void setJoinEmail(String email) {this.email=email;}

    public String getJoinPhoneNum() {return phonenum;}
    public void setJoinPhoneNum(String phonenum) {this.phonenum=phonenum; }





}
