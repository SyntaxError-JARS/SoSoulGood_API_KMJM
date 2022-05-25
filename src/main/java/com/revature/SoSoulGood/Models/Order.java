package com.revature.SoSoulGood.Models;

public class Order {

    private String username;
    private int id;
    private String menuItem;
    private String comment;
    private String orderDate;
    private boolean isfavorite;


    public Order(String username, int id, String menuItem, String comment, String orderDate, boolean favorite) {
        super();
        this.username = username;
        this.id = id;
        this.menuItem = menuItem;
        this.comment = comment;
        this.orderDate = orderDate;
        this.isfavorite = favorite;
    }


    @Override
    public String toString() {
        return "OrderData{" +
                "username='" + username + '\'' +
                ", id='" + id + '\'' +
                ", menuItem='" + menuItem + '\'' +
                ", comment='" + comment + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", isfavorite='" + isfavorite + '\'' +
                '}';
    }

    // Getters and Setters

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(String menuItem) {
        this.menuItem = menuItem;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public boolean isFavorite() {
        return isfavorite;
    }

    public void setFavorite(boolean favorite) {
        this.isfavorite = favorite;
    }
}
