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
    private int active_feedback;
    private float feedback_point;
    private Status post_status;
    private LocalDateTime start_time;
    private LocalDateTime end_time;
    private LocalDateTime create_time;
    private String fommated_create_time;
    private String admin_message;

    public LocalDateTime getCreate_time() {
        return create_time;
    }

    public void setCreate_time(LocalDateTime create_time) {
        this.create_time = create_time;
    }

    public String getFommated_create_time() {
        return fommated_create_time;
    }

    public void setFommated_create_time(String fommated_create_time) {
        this.fommated_create_time = fommated_create_time;
    }
    public Post() {
    }

    public Post(int post_id, House house, Purpose purpose, int price, int poster_id, Status house_status, int admin_id, int active_feedback, float feedback_point, Status post_status, LocalDateTime start_time, LocalDateTime end_time, LocalDateTime create_time, String fommated_create_time, String admin_message) {
        this.post_id = post_id;
        this.house = house;
        this.purpose = purpose;
        this.price = price;
        this.poster_id = poster_id;
        this.house_status = house_status;
        this.admin_id = admin_id;
        this.active_feedback = active_feedback;
        this.feedback_point = feedback_point;
        this.post_status = post_status;
        this.start_time = start_time;
        this.end_time = end_time;
        this.create_time = create_time;
        this.fommated_create_time = fommated_create_time;
        this.admin_message = admin_message;
    }
    
    public void setAdmin_message(String admin_message) {
        this.admin_message = admin_message;
    }

    public String getAdmin_message() {
        return admin_message;
    }

    public void setStart_time(LocalDateTime start_time) {
        this.start_time = start_time;
    }

    public void setPurpose(Purpose purpose) {
        this.purpose = purpose;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setPoster_id(int poster_id) {
        this.poster_id = poster_id;
    }

    public void setPost_status(Status post_status) {
        this.post_status = post_status;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public void setHouse_status(Status house_status) {
        this.house_status = house_status;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public void setFeedback_point(float feedback_point) {
        this.feedback_point = feedback_point;
    }

    public void setEnd_time(LocalDateTime end_time) {
        this.end_time = end_time;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    public void setActive_feedback(int active_feedback) {
        this.active_feedback = active_feedback;
    }

    public LocalDateTime getStart_time() {
        return start_time;
    }

    public int getPrice() {
        return price;
    }

    public Purpose getPurpose() {
        return purpose;
    }

    public int getPoster_id() {
        return poster_id;
    }

    public int getPost_id() {
        return post_id;
    }

    public Status getPost_status() {
        return post_status;
    }

    public Status getHouse_status() {
        return house_status;
    }

    public House getHouse() {
        return house;
    }

    public float getFeedback_point() {
        return feedback_point;
    }

    public LocalDateTime getEnd_time() {
        return end_time;
    }

    public int getAdmin_id() {
        return admin_id;
    }

    public int getActive_feedback() {
        return active_feedback;
    }


}