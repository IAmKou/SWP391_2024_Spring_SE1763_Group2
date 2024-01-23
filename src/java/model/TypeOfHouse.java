/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author KinNhun
 */
public class TypeOfHouse {
    private int type_of_house;
    private String type_house_name;

    public TypeOfHouse() {
    }

    public TypeOfHouse(int type_of_house, String type_house_name) {
        this.type_of_house = type_of_house;
        this.type_house_name = type_house_name;
    }

    public int getType_of_house() {
        return type_of_house;
    }

    public void setType_of_house(int type_of_house) {
        this.type_of_house = type_of_house;
    }

    public String getType_house_name() {
        return type_house_name;
    }

    public void setType_house_name(String type_house_name) {
        this.type_house_name = type_house_name;
    }

    @Override
    public String toString() {
        return "typeOfHouse{" + "type_of_house=" + type_of_house + ", type_house_name=" + type_house_name + '}';
    }
    
}
