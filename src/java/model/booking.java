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
public class booking {
    private int customer_id;
    private Date booking_date;
    private int status_id;
    private int house_id;

    public booking() {
    }

    public booking(int customer_id, Date booking_date, int status_id, int house_id) {
        this.customer_id = customer_id;
        this.booking_date = booking_date;
        this.status_id = status_id;
        this.house_id = house_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public Date getBooking_date() {
        return booking_date;
    }

    public void setBooking_date(Date booking_date) {
        this.booking_date = booking_date;
    }

    public int getStatus_id() {
        return status_id;
    }

    public void setStatus_id(int status_id) {
        this.status_id = status_id;
    }

    public int getHouse_id() {
        return house_id;
    }

    public void setHouse_id(int house_id) {
        this.house_id = house_id;
    }
    
}
