package com.example.androidshopping.entity;

import org.litepal.crud.LitePalSupport;

public class ShopItemBean extends LitePalSupport {
    private String  name;
    private int  img;
    private String shopDetail;
    private String price;

    public String getShopDetail() {
        return shopDetail;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setShopDetail(String shopDetail) {
        this.shopDetail = shopDetail;
    }

    public ShopItemBean(String name, int img, String shopDetail, String price) {
        this.name = name;
        this.img = img;
        this.shopDetail = shopDetail;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
