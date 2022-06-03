package com.revature.SoSoulGood.Models;

import javax.persistence.*;

@Entity
@Table(name = "CreditCard")
public class CreditCard {

    private String username;
    private String Cc_Name;
    private int Cc_No;
    private int cvv;
    private String expDate;
    private int Zip;

    private int limit;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCc_Name() {
        return Cc_Name;
    }

    public void setCc_Name(String Cc_Name) {
        this.Cc_Name = Cc_Name;
    }

    public int getCc_No() {
        return Cc_No;
    }

    public void setCc_No(int Cc_No) {
        this.Cc_No = Cc_No;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setexpDate(String expDate) {
        this.expDate = expDate;
    }

    public int getZip() {
        return Zip;
    }

    public void setZip(int Zip) {
        this.Zip = Zip;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
