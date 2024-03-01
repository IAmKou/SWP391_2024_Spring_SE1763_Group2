/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;
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
    private int status_id;
    private House house;
    private String message;
    private Date meeting_date;
    
    public Booking(int booking_id, User user, LocalDateTime booking_date, int status_id, House house, String fommatted_booking_date) {
        this.booking_id = booking_id;
        this.user = user;
        this.booking_date = booking_date;
        this.status_id = status_id;
        this.house = house;
        this.fommatted_booking_date = fommatted_booking_date;
    }

    public Booking() {
    }

    public Date getMeeting_date() {
        return meeting_date;
    }

    public void setMeeting_date(Date meeting_date) {
        this.meeting_date = meeting_date;
    }
    
    public String getFommatted_booking_date() {
        return fommatted_booking_date;
    }

    public void setFommatted_booking_date(String fommatted_booking_date) {
        this.fommatted_booking_date = fommatted_booking_date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public int getStatus_id() {
        return status_id;
    }

    public void setStatus_id(int status_id) {
        this.status_id = status_id;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    
}
