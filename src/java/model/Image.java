/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


/**
 *
 * @author FPTSHOP
 */
public class Image {

    int image_id;
    byte[] imageData;
    String imageDataBase64;

    public String getImageDataAsBase64() {
        return imageDataBase64;
    }

    public void setImageDataAsBase64(String imageDataBase64) {
        this.imageDataBase64 = imageDataBase64;
    }

    public Image(int imageId, byte[] imageData) {
        this.image_id = imageId;
        this.imageData = imageData;

    }

    public int getImage_id() {
        return image_id;
    }

    public void setImage_id(int image_id) {
        this.image_id = image_id;
    }


    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }


}
