package com.revature.SoSoulGood.Models;

import javax.persistence.*;

@Entity
@Table(name = "Customer")
public class Customer {

    private String username;
    private String fname;
    private String lname;

    private String password;
    private int balance;
    private boolean isAdmin;


    @Override
    public String toString() {
        return "Customer{" +
                "username='" + username + '\'' +
                ", fname=" + fname +
                ", lname=" + lname +
                ", password=" + password +
                ", balance=" + balance +
                ", isAdmin=" + isAdmin +
                '}';
    }


    // Getter and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getfname() {
        return fname;
    }

    public void setfname(String fname) {
        this.fname = fname;
    }

    public String getlname() {
        return lname;
    }

    public void setlname(String lname) {
        this.lname = lname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
