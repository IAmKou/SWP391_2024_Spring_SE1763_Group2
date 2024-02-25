/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 *
 * @author ACER
 */
public class User { 

    private int user_id;
    private String full_name;
    private boolean verified_email;
    private Date date_of_birth;
    private String address;
    private String phone_number;
    private String email;
    private String avatar;

    public User() {
    }

    public User(int user_id, String full_name, boolean verified_email, Date date_of_birth, String address, String phone_number, String email, String avatar) {
        this.user_id = user_id;
        this.full_name = full_name;
        this.verified_email = verified_email;
        this.date_of_birth = date_of_birth;
        this.address = address;
        this.phone_number = phone_number;
        this.email = email;
        this.avatar = avatar;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public boolean isVerified_email() {
        return verified_email;
    }

    public void setVerified_email(boolean verified_email) {
        this.verified_email = verified_email;
    }

    public Date getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
public String toString() {
    return "User{" +
            "user_id=" + user_id +
            ", full_name='" + full_name + '\'' +
            ", verified_email=" + verified_email +
            ", date_of_birth='" + date_of_birth + '\'' +
            ", address='" + address + '\'' +
            ", phone_number='" + phone_number + '\'' +
            ", email='" + email + '\'' +
            ", avatar='" + avatar + '\'' +
            '}';
}
    
}
    