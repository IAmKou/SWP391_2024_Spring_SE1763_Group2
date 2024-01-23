/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


public class Image {
    private int house_id;
    private String image_link;
    private int image_id;

    public Image() {
    }

    public Image(int house_id, String image_link, int image_id) {
        this.house_id = house_id;
        this.image_link = image_link;
        this.image_id = image_id;
    }

    public int getHouse_id() {
        return house_id;
    }

    public void setHouse_id(int house_id) {
        this.house_id = house_id;
    }

    public String getImage_link() {
        return image_link;
    }

    public void setImage_link(String image_link) {
        this.image_link = image_link;
    }

    public int getImage_id() {
        return image_id;
    }

    public void setImage_id(int image_id) {
        this.image_id = image_id;
    }

    @Override
    public String toString() {
        return "imge{" + "house_id=" + house_id + ", image_link=" + image_link + ", image_id=" + image_id + '}';
    }
    
}
