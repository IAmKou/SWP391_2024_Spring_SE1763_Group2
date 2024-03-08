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
public class Meeting {
    private int meeting_id;
    private User customer;
    private int postId;
    private LocalDateTime bookingDate;
    private String fommattedBookingDate;
    private LocalDateTime meetingDate;
    private String fommattedMeetingDate;
    private String note;
    private Status meetingStatus;

    public Meeting() {
    }

    public LocalDateTime getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDateTime bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getFommattedBookingDate() {
        return fommattedBookingDate;
    }

    public void setFommattedBookingDate(String fommattedBookingDate) {
        this.fommattedBookingDate = fommattedBookingDate;
    }
    
    
    public int getMeeting_id() {
        return meeting_id;
    }

    public void setMeeting_id(int meeting_id) {
        this.meeting_id = meeting_id;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    

    public LocalDateTime getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingDate(LocalDateTime meetingDate) {
        this.meetingDate = meetingDate;
    }

    public String getFommattedMeetingDate() {
        return fommattedMeetingDate;
    }

    public void setFommattedMeetingDate(String fommattedMeetingDate) {
        this.fommattedMeetingDate = fommattedMeetingDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Status getMeetingStatus() {
        return meetingStatus;
    }

    public void setMeetingStatus(Status meetingStatus) {
        this.meetingStatus = meetingStatus;
    }
    
    
}
