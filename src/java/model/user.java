/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ACER
 */
public class User {
    private int userID;
    private String fullName;
    private String userName;
    private String passWord;
    private int roleID;
    private String location;
    private String phone;
    private String email;

    public User(int userID, String fullName, String userName, String passWord, int roleID, String location, String phone, String email) {
        this.userID = userID;
        this.fullName = fullName;
        this.userName = userName;
        this.passWord = passWord;
        this.roleID = roleID;
        this.location = location;
        this.phone = phone;
        this.email = email;
    }

    public User() {
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return fullName;
    }

    public String getLocation() {
        return location;
    }

    public String getPassWord() {
        return passWord;
    }

    public String getPhone() {
        return phone;
    }

    public int getRoleID() {
        return roleID;
    }

    public int getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }
    
}
