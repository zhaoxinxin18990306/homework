package com.example.androidshopping.entity;

import org.litepal.crud.LitePalSupport;

public class ShopCartBean extends LitePalSupport {
    private String  name;
    private String  price;
    private int  img;
    private int  id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
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
