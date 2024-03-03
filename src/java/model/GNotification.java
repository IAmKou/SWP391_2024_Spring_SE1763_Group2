/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;

/**
 *
 * @author luong
 */
public class GNotification {
    private int gn_id;
    private String message;
    private LocalDateTime created_at;

    public GNotification() {
    }

    public GNotification(int gn_id, String message, LocalDateTime created_at) {
        this.gn_id = gn_id;
        this.message = message;
        this.created_at = created_at;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public int getGn_id() {
        return gn_id;
    }

    public String getMessage() {
        return message;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public void setGn_id(int gn_id) {
        this.gn_id = gn_id;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
}
