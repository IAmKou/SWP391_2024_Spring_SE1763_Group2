/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;

/**
 *
 * @author FPTSHOP
 */
public class House {

    int house_id;
    User house_owner;
    TypeOfHouse type_of_house;
    String location;
    String description;
    int area;
    int number_of_room;
    List<Image> image;

    public House() {
    }

    public House(int house_id, User house_owner, TypeOfHouse type_of_house, String location, String description, int area, int number_of_room, List<Image> image) {
        this.house_id = house_id;
        this.house_owner = house_owner;
        this.type_of_house = type_of_house;
        this.location = location;
        this.description = description;
        this.area = area;
        this.number_of_room = number_of_room;
        this.image = image;
    }

    public int getHouse_id() {
        return house_id;
    }

    public void setHouse_id(int house_id) {
        this.house_id = house_id;
    }

    public User getHouse_owner() {
        return house_owner;
    }

    public void setHouse_owner(User house_owner) {
        this.house_owner = house_owner;
    }

    public TypeOfHouse getType_of_house() {
        return type_of_house;
    }

    public void setType_of_house(TypeOfHouse type_of_house) {
        this.type_of_house = type_of_house;
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

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public int getNumber_of_room() {
        return number_of_room;
    }

    public void setNumber_of_room(int number_of_room) {

        this.number_of_room = number_of_room;
    }

    public List<Image> getImage() {
        return image;
    }

    public void setImage(List<Image> image) {
        this.image = image;
    }

}
