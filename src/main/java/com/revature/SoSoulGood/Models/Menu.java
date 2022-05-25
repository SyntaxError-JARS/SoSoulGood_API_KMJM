package com.revature.SoSoulGood.Models;

public class Menu {

    private String menuItem;
    private int price;
    private int calories;
    private boolean Changeable;


    @Override
    public String toString() {
        return "Menu{" +
                "menuItem='" + menuItem + '\'' +
                ", price=" + price +
                ", protein=" + calories +
                ", Changeable=" + Changeable +
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

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public boolean getChangeable() {
        return Changeable;
    }

    public void setChangeable(boolean changeable) {
        this.Changeable = Changeable;
    }
}
