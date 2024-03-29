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
public class Booking {

    private int booking_id;
    private User user;
    private LocalDateTime booking_date;
    private String fommatted_booking_date;
    private Status status;
    private int post_id;
    private String note;
    private LocalDateTime check_in_date;
    private String fomatted_check_in_date;
    private LocalDateTime check_out_date;
    private String fomatted_check_out_date;
    private PaymentMethod payment_method;
    private int quantityOfpeople;
    private String responseMessage;

    public Booking(int booking_id, User user, LocalDateTime booking_date, String fommatted_booking_date, Status status, int post_id, String note, LocalDateTime check_in_date, String fomatted_check_in_date, LocalDateTime check_out_date, String fomatted_check_out_date, PaymentMethod payment_method, int quantityOfpeople, String responseMessage) {
        this.booking_id = booking_id;
        this.user = user;
        this.booking_date = booking_date;
        this.fommatted_booking_date = fommatted_booking_date;
        this.status = status;
        this.post_id = post_id;
        this.note = note;
        this.check_in_date = check_in_date;
        this.fomatted_check_in_date = fomatted_check_in_date;
        this.check_out_date = check_out_date;
        this.fomatted_check_out_date = fomatted_check_out_date;
        this.payment_method = payment_method;
        this.quantityOfpeople = quantityOfpeople;
        this.responseMessage = responseMessage;
    }

    
    public int getQuantityOfpeople() {
        return quantityOfpeople;
    }

    public void setQuantityOfpeople(int quantityOfpeople) {
        this.quantityOfpeople = quantityOfpeople;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public Booking() {
    }

    public int getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(int booking_id) {
        this.booking_id = booking_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getBooking_date() {
        return booking_date;
    }

    public void setBooking_date(LocalDateTime booking_date) {
        this.booking_date = booking_date;
    }

    public String getFommatted_booking_date() {
        return fommatted_booking_date;
    }

    public void setFommatted_booking_date(String fommatted_booking_date) {
        this.fommatted_booking_date = fommatted_booking_date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LocalDateTime getCheck_in_date() {
        return check_in_date;
    }

    public void setCheck_in_date(LocalDateTime check_in_date) {
        this.check_in_date = check_in_date;
    }

    public LocalDateTime getCheck_out_date() {
        return check_out_date;
    }

    public void setCheck_out_date(LocalDateTime check_out_date) {
        this.check_out_date = check_out_date;
    }

    public PaymentMethod getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(PaymentMethod payment_method) {
        this.payment_method = payment_method;
    }

    public String getFomatted_check_in_date() {
        return fomatted_check_in_date;
    }

    public void setFomatted_check_in_date(String fomatted_check_in_date) {
        this.fomatted_check_in_date = fomatted_check_in_date;
    }

    public String getFomatted_check_out_date() {
        return fomatted_check_out_date;
    }

    public void setFomatted_check_out_date(String fomatted_check_out_date) {
        this.fomatted_check_out_date = fomatted_check_out_date;
    }

    
    
    
    
}
