/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.House;

/**
 *
 * @author FPTSHOP
 */
public class TypeOfHouse {
    int typeOfHouseId;
    String description;

    public TypeOfHouse() {
    }

    public TypeOfHouse(int typeOfHouseId, String description) {
        this.typeOfHouseId = typeOfHouseId;
        this.description = description;
    }

    public int getTypeOfHouseId() {
        return typeOfHouseId;
    }

    public void setTypeOfHouseId(int typeOfHouseId) {
        this.typeOfHouseId = typeOfHouseId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
