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
public class feedback {
    private int feedback_id;
    private int post_id;
    private int user_id;
    private LocalDateTime created_at;
    private String content;
    private String image_link;
    
    public feedback() {
    }

    public feedback(int feedback_id, int post_id, int user_id, LocalDateTime created_at, String content, String image_link) {
        this.feedback_id = feedback_id;
        this.post_id = post_id;
        this.user_id = user_id;
        this.created_at = created_at;
        this.content = content;
        this.image_link = image_link;
    }

    public void setImage_link(String image_link) {
        this.image_link = image_link;
    }

    public String getImage_link() {
        return image_link;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public int getFeedback_id() {
        return feedback_id;
    }

    public int getPost_id() {
        return post_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public void setFeedback_id(int feedback_id) {
        this.feedback_id = feedback_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    
    
}
