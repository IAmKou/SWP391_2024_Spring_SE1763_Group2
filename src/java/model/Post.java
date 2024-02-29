/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;


/**
 *
 * @author FPTSHOP
 */
public class Post {
    private int post_id;
    private House house;
    private Purpose purpose;
    private int price;
    private int poster_id;
    private Status house_status;
    private int admin_id;
    private Status post_status;
    private LocalDateTime start_time;
    private LocalDateTime end_time;
    private LocalDateTime create_time;
    public Post() {
    }

    public Post(int post_id, House house, Purpose purpose, int price, int poster_id, Status house_status, int admin_id, Status post_status, LocalDateTime start_time, LocalDateTime end_time) {
        this.post_id = post_id;
        this.house = house;
        this.purpose = purpose;
        this.price = price;
        this.poster_id = poster_id;
        this.house_status = house_status;
        this.admin_id = admin_id;
        this.post_status = post_status;
        this.start_time = start_time;
        this.end_time = end_time;
    }

    public void setStart_time(LocalDateTime start_time) {
        this.start_time = start_time;
    }

    public void setEnd_time(LocalDateTime end_time) {
        this.end_time = end_time;
    }

    public LocalDateTime getStart_time() {
        return start_time;
    }

    public LocalDateTime getEnd_time() {
        return end_time;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public Purpose getPurpose() {
        return purpose;
    }

    public void setPurpose(Purpose purpose) {
        this.purpose = purpose;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPoster_id() {
        return poster_id;
    }

    public void setPoster_id(int poster_id) {
        this.poster_id = poster_id;
    }

    public Status getHouse_status() {
        return house_status;
    }

    public void setHouse_status(Status house_status) {
        this.house_status = house_status;
    }

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    public Status getPost_status() {
        return post_status;
    }

    public void setPost_status(Status post_status) {
        this.post_status = post_status;
    }

    public LocalDateTime getCreate_time() {
        return create_time;
    }

    public void setCreate_time(LocalDateTime create_time) {
        this.create_time = create_time;
    }

    
    
}
