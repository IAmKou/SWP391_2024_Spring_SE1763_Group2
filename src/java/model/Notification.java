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
public class Notification {
    private int noti_id;
    private String type;
    private int recipient_id;
    private String message;
    private LocalDateTime created_at;

    public Notification() {
    }

    public Notification(int noti_id, String type, int recipient_id, String message, LocalDateTime created_at) {
        this.noti_id = noti_id;
        this.type = type;
        this.recipient_id = recipient_id;
        this.message = message;
        this.created_at = created_at;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public String getMessage() {
        return message;
    }

    public int getNoti_id() {
        return noti_id;
    }

    public int getRecipient_id() {
        return recipient_id;
    }

    public String getType() {
        return type;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setNoti_id(int noti_id) {
        this.noti_id = noti_id;
    }

    public void setRecipient_id(int recipient_id) {
        this.recipient_id = recipient_id;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    
}
