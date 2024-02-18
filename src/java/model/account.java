/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ACER
 */
public class Account {
    private int user_id;
    private String user_name;
    private String pass_word;
    private int role_id;

    public Account() {
    }

    public Account(int user_id, String user_name, String pass_word, int role_id) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.pass_word = pass_word;
        this.role_id = role_id;
    }

    public String getPass_word() {
        return pass_word;
    }

    public int getRole_id() {
        return role_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setPass_word(String pass_word) {
        this.pass_word = pass_word;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
    
}
