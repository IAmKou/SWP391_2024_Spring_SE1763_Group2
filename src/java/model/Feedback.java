/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author trant
 */
public class Feedback {
    private User user_id;
    private Post post_id;
    private int number_of_star;
    private String comment;

    public Feedback() {
    }

    public Feedback(User user_id, Post post_id, int number_of_star, String comment) {
        this.user_id = user_id;
        this.post_id = post_id;
        this.number_of_star = number_of_star;
        this.comment = comment;
    }

    public User getUser_id() {
        return user_id;
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }

    public Post getPost_id() {
        return post_id;
    }

    public void setPost_id(Post post_id) {
        this.post_id = post_id;
    }

    public int getNumber_of_star() {
        return number_of_star;
    }

    public void setNumber_of_star(int number_of_star) {
        this.number_of_star = number_of_star;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Feedback{" + "user_id=" + user_id + ", post_id=" + post_id + ", number_of_star=" + number_of_star + ", comment=" + comment + '}';
    }
    
}
