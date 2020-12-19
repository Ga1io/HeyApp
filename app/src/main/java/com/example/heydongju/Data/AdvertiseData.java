package com.example.heydongju.Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AdvertiseData {
//    String content;
//    String title;
//    Integer recommendImg;
//    String content2;

    @SerializedName("content")
    @Expose
    private String content;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("recommendImg")
    @Expose
    private Integer recommendImg;

    @SerializedName("content2")
    @Expose
    private String content2;

    public String getContent2() {
        return content2;
    }

    public void setContent2(String content2) {
        this.content2 = content2;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getRecommendImg() {
        return recommendImg;
    }

    public void setRecommendImg(Integer recommendImg) {
        this.recommendImg = recommendImg;
    }
}
