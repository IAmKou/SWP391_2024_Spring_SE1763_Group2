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
    private Date date_of_birth;
    private String address;
    private int phone_number;
    private String email;

    public User() {
    }

    public User(int user_id, String full_name, Date date_of_birth, String address, int phone_number, String email) {
        this.user_id = user_id;
        this.full_name = full_name;
        this.date_of_birth = date_of_birth;
        this.address = address;
        this.phone_number = phone_number;
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public Date getDate_of_birth() {
        return date_of_birth;
    }

    public String getEmail() {
        return email;
    }

    public String getFull_name() {
        return full_name;
    }

    public int getPhone_number() {
        return phone_number;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public void setPhone_number(int phone_number) {
        this.phone_number = phone_number;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    
}