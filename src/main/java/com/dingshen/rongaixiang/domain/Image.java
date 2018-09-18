package com.dingshen.rongaixiang.domain;

public class Image {
    private Integer id;

    private String imgName;

    private String imgUrl;

    private String imgInfomation;

    private String imgType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getImgInfomation() {
        return imgInfomation;
    }

    public void setImgInfomation(String imgInfomation) {
        this.imgInfomation = imgInfomation;
    }

    public String getImgType() {
        return imgType;
    }

    public void setImgType(String imgType) {
        this.imgType = imgType;
    }
}