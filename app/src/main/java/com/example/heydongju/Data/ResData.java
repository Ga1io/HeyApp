package com.example.heydongju.Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResData {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("cauid")
    @Expose
    private String cauid;
    @SerializedName("permitLevel")
    @Expose
    private Integer permitLevel;

    public ResData(String id, String password, String name, String email, String phone, String cauid, Integer permitLevel) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.cauid = cauid;
        this.permitLevel = permitLevel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCauid() {
        return cauid;
    }

    public void setCauid(String cauid) {
        this.cauid = cauid;
    }

    public Integer getPermitLevel() {
        return permitLevel;
    }

    public void setPermitLevel(Integer permitLevel) {
        this.permitLevel = permitLevel;
    }



}