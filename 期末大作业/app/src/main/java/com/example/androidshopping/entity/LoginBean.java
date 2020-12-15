package com.example.androidshopping.entity;

import org.litepal.crud.LitePalSupport;

public class LoginBean extends LitePalSupport {
    private  String accout;
    private  String password;

    public String getAccout() {
        return accout;
    }

    public void setAccout(String accout) {
        this.accout = accout;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
