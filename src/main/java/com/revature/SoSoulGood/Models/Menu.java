package com.revature.SoSoulGood.Models;

import javax.persistence.*;

@Entity
@Table(name = "Menu")
public class Menu {

    private String menuItem;
    private int price;
    private int protein;
    private boolean Substitutable;


    @Override
    public String toString() {
        return "Menu{" +
                "menuItem='" + menuItem + '\'' +
                ", price=" + price +
                ", protein=" + protein +
                ", Substitutable=" + Substitutable +
                '}';
    }


    // Getters and Setters

    public String getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(String menuItem) {
        this.menuItem = menuItem;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public boolean getSubstitutable() {
        return Substitutable;
    }

    public void setSubstitutable(boolean Substitutable) {
        this.Substitutable = Substitutable;
    }
}
