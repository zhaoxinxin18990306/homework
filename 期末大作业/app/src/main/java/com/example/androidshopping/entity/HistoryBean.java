package com.example.androidshopping.entity;

import org.litepal.crud.LitePalSupport;

public class HistoryBean extends LitePalSupport {
    private  String  shopname;
    private  String  price;
    private  String  date;

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
