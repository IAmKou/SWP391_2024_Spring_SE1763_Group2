/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.House;

import model.User.User;

/**
 *
 * @author FPTSHOP
 */
public class House {

    int houseId;
    String location;
    String description;
    User houseOwnerId;
    int price;
    String picture;
    boolean status;
    TypeOfHouse type;
    public House() {
    }

    public House(int houseId, String location, String description, User houseOwnerId, int price, String picture, boolean status, TypeOfHouse type) {
        this.houseId = houseId;
        this.location = location;
        this.description = description;
        this.houseOwnerId = houseOwnerId;
        this.price = price;
        this.picture = picture;
        this.status = status;
        this.type = type;
    }

    public TypeOfHouse getType() {
        return type;
    }

    public void setType(TypeOfHouse type) {
        this.type = type;
    }

    public int getHouseId() {
        return houseId;
    }

    public void setHouseId(int houseId) {
        this.houseId = houseId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getHouseOwnerId() {
        return houseOwnerId;
    }

    public void setHouseOwnerId(User houseOwnerId) {
        this.houseOwnerId = houseOwnerId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
