/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Booking;
import model.Meeting;
import model.Order;
import model.PaymentMethod;
import model.Status;
import model.User;

/**
 *
 * @author FPTSHOP
 */
public class OrderDAO extends DBContext {

    public List<Order> getOrderListByCustomer(int customerId) {
        List<Order> orders = new ArrayList<>();
        try {
            String sql = "SELECT \n"
                    + "    appointment.appointment_id, \n"
                    + "    appointment.customer_id, \n"
                    + "    appointment.post_id, \n"
                    + "    appointment.booking_date AS appointment_booking_date, \n"
                    + "    appointment.meeting_date, \n"
                    + "    appointment.note AS appointment_note, \n"
                    + "    appointment.appointment_status AS appointment_status_id, \n"
                    + "    appointment.response_message AS appointment_response_message, \n"
                    + "    meeting_status.status_name as appointment_status_name,\n"
                    + "    NULL AS booking_id,\n"
                    + "    NULL AS booking_user_id,\n"
                    + "    NULL AS booking_status_id,\n"
                    + "    NULL AS booking_status_name,\n"
                    + "    NULL AS booking_booking_date,\n"
                    + "    NULL AS booking_note,\n"
                    + "    NULL AS booking_post_id,\n"
                    + "    NULL AS booking_check_in_date,\n"
                    + "    NULL AS booking_check_out_date,\n"
                    + "    NULL AS booking_payment_method_id,\n"
                    + "    NULL AS booking_payment_method_name,\n"
                    + "    NULL AS quantity_of_people,\n"
                    + "    NULL AS booking_response_message\n"
                    + "FROM \n"
                    + "    appointment\n"
                    + "JOIN \n"
                    + "	request_status as meeting_status on meeting_status.status_id = appointment_status \n"
                    + "WHERE\n"
                    + "    appointment.customer_id = ?\n"
                    + "UNION\n"
                    + "\n"
                    + "SELECT \n"
                    + "    NULL AS appointment_id,\n"
                    + "    NULL AS appointment_customer_id,\n"
                    + "    NULL AS post_id,\n"
                    + "    NULL AS appointment_booking_date,\n"
                    + "    NULL AS meeting_date,\n"
                    + "    NULL AS appointment_note,\n"
                    + "	NULL AS appointment_response_message, \n"
                    + "	NULL AS appointment_status, \n"
                    + "    NULL AS appointment_status_name,\n"
                    + "    booking.booking_id, \n"
                    + "    booking.user_id AS booking_user_id, \n"
                    + "    booking.booking_status AS booking_status_id,\n"
                    + "    booking_status.status_name AS booking_status_name,\n"
                    + "    booking.booking_date AS booking_booking_date, \n"
                    + "    booking.note AS booking_note, \n"
                    + "    booking.post_id AS booking_post_id,\n"
                    + "    booking.check_in_date AS booking_check_in_date, \n"
                    + "    booking.check_out_date AS booking_check_out_date, \n"
                    + "    booking.payment_method AS booking_payment_method_id,\n"
                    + "    booking_payment_method.method_name AS booking_payment_method_name,\n"
                    + "    booking.quantity_of_people,\n"
                    + "    booking.response_message AS booking_response_message\n"
                    + "FROM \n"
                    + "    booking\n"
                    + "JOIN \n"
                    + "	request_status as booking_status on booking_status.status_id = booking_status \n"
                    + "JOIN \n"
                    + "	payment_method as booking_payment_method on booking_payment_method.method_id = booking.payment_method \n"
                    + "WHERE\n"
                    + "    booking.user_id = ?";
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, customerId);
            stm.setInt(2, customerId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Booking booking = new Booking();
                Meeting meeting = new Meeting();

                if (rs.getInt("appointment_id") != 0) {
                    User meeting_user = new User();
                    meeting_user.setUser_id(rs.getInt("customer_id"));

                    Status meetingStatus = new Status();
                    meetingStatus.setStatus_id(rs.getInt("appointment_status_id"));
                    meetingStatus.setStatus_name(rs.getString("appointment_status_name"));

                    Timestamp meetingBookingTimestamp = rs.getTimestamp("appointment_booking_date");
                    Timestamp meetingTimestamp = rs.getTimestamp("meeting_date");
                    LocalDateTime meetingBookingDateTime = meetingBookingTimestamp.toLocalDateTime();
                    LocalDateTime meetingDateTime = meetingTimestamp.toLocalDateTime();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                    String fommattedMeetingBookingDateTime = meetingBookingDateTime.format(formatter);
                    String fommattedMeetingDateTime = meetingDateTime.format(formatter);
                    meeting.setFommattedBookingDate(fommattedMeetingBookingDateTime);
                    meeting.setFommattedMeetingDate(fommattedMeetingDateTime);
                    meeting.setBookingDate(meetingBookingDateTime);
                    meeting.setMeetingDate(meetingDateTime);
                    meeting.setResponeMessage(rs.getString("appointment_response_message"));
                    
                    meeting.setMeeting_id(rs.getInt("appointment_id"));
                    meeting.setCustomer(meeting_user);
                    meeting.setPostId(rs.getInt("post_id"));
                    meeting.setNote(rs.getString("appointment_note"));
                    meeting.setMeetingStatus(meetingStatus);

                } else {
                    User booking_user = new User();
                    booking_user.setUser_id(rs.getInt("booking_user_id"));

                    Status bookingStatus = new Status();
                    bookingStatus.setStatus_id(rs.getInt("booking_status_id"));
                    bookingStatus.setStatus_name(rs.getString("booking_status_name"));

                    PaymentMethod bookingPaymentMethod = new PaymentMethod();
                    bookingPaymentMethod.setMethod_id(rs.getInt("booking_payment_method_id"));
                    bookingPaymentMethod.setMethod_name(rs.getString("booking_payment_method_name"));

                    booking.setBooking_id(rs.getInt("booking_id"));
                    booking.setPost_id(rs.getInt("booking_post_id"));
                    booking.setNote(rs.getString("booking_note"));
                    booking.setUser(booking_user);
                    booking.setPayment_method(bookingPaymentMethod);
                    booking.setQuantityOfpeople(rs.getInt("quantity_of_people"));
                    booking.setStatus(bookingStatus);
                    booking.setResponseMessage(rs.getString("booking_response_message"));
                    
                    Timestamp bookingBookingTimestamp = rs.getTimestamp("booking_booking_date");
                    Timestamp checkInTimestamp = rs.getTimestamp("booking_check_in_date");
                    Timestamp checkOutTimestamp = rs.getTimestamp("booking_check_out_date");
                    LocalDateTime bookingBookingDateTime = bookingBookingTimestamp.toLocalDateTime();
                    LocalDateTime checkIntDateTime = checkInTimestamp.toLocalDateTime();
                    LocalDateTime checkOutDateTime = checkOutTimestamp.toLocalDateTime();

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                    String fommattedBookingBookingDateTime = bookingBookingDateTime.format(formatter);
                    String fommattedCheckIntDateTime = checkIntDateTime.format(formatter);
                    String fommattedCheckOutDateTime = checkOutDateTime.format(formatter);

                    booking.setFommatted_booking_date(fommattedBookingBookingDateTime);
                    booking.setFomatted_check_in_date(fommattedCheckIntDateTime);
                    booking.setFomatted_check_out_date(fommattedCheckOutDateTime);
                    booking.setBooking_date(bookingBookingDateTime);
                    booking.setCheck_in_date(checkIntDateTime);
                    booking.setCheck_out_date(checkOutDateTime);

                }
                Order order = new Order(booking, meeting);

                orders.add(order);
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orders;
    }

    public List<Order> getOrderListByOwnerHouse(int poster) {
        List<Order> orders = new ArrayList<>();
        try {
            String sql = "SELECT \n"
                    + "    appointment.appointment_id, \n"
                    + "    appointment.customer_id, \n"
                    + "    user1.full_name AS appointment_customer_name,\n"
                    + "    user1.email AS appointment_customer_email,\n"
                    + "    user1.phone_number AS appointment_customer_phone,\n"
                    + "    user1.avatar AS appointment_customer_avatar,\n"
                    + "    appointment.post_id, \n"
                    + "    appointment.booking_date AS appointment_booking_date, \n"
                    + "    appointment.meeting_date, \n"
                    + "    appointment.note AS appointment_note, \n"
                    + "    appointment.appointment_status AS appointment_status_id, \n"
                    + "    meeting_status.status_name as appointment_status_name,\n"
                    + "    NULL AS booking_id,\n"
                    + "    NULL AS booking_user_id,\n"
                    + "    NULL AS booking_full_name,\n"
                    + "    NULL AS booking_email,\n"
                    + "    NULL AS booking_phone_number,\n"
                    + "    NULL AS booking_avatar,\n"
                    + "    NULL AS booking_status_id,\n"
                    + "    NULL AS booking_status_name,\n"
                    + "    NULL AS booking_booking_date,\n"
                    + "    NULL AS booking_note,\n"
                    + "    NULL AS booking_post_id,\n"
                    + "    NULL AS booking_check_in_date,\n"
                    + "    NULL AS booking_check_out_date,\n"
                    + "    NULL AS booking_payment_method_id,\n"
                    + "    NULL AS booking_payment_method_name,\n"
                    + "    NULL AS quantity_of_people\n"
                    + "FROM \n"
                    + "    appointment\n"
                    + "JOIN\n"
                    + "    post ON appointment.post_id = post.post_id\n"
                    + "JOIN\n"
                    + "    user AS user1 ON appointment.customer_id = user1.user_id\n"
                    + "JOIN \n"
                    + "	request_status as meeting_status on meeting_status.status_id = appointment_status \n"
                    + "\n"
                    + "WHERE\n"
                    + "    post.poster_id = ? and appointment.appointment_status = 1\n"
                    + "UNION\n"
                    + "\n"
                    + "SELECT \n"
                    + "    NULL AS appointment_id,\n"
                    + "    NULL AS customer_id,\n"
                    + "    NULL AS post_id,\n"
                    + "    NULL AS appointment_booking_date,\n"
                    + "    NULL AS meeting_date,\n"
                    + "    NULL AS appointment_note,\n"
                    + "	NULL AS appointment_status, \n"
                    + "    NULL AS appointment_status_name,\n"
                    + "    NULL AS appointment_customer_name,\n"
                    + "    NULL AS appointment_customer_email,\n"
                    + "    NULL AS appointment_customer_phone,\n"
                    + "    NULL AS appointment_customer_avatar,\n"
                    + "    booking.booking_id, \n"
                    + "    booking.user_id AS booking_user_id, \n"
                    + "    user2.full_name AS booking_full_name,\n"
                    + "    user2.email AS booking_email,\n"
                    + "    user2.phone_number AS booking_phone_number,\n"
                    + "    user2.avatar AS booking_avatar,\n"
                    + "    booking.booking_status AS booking_status_id,\n"
                    + "    booking_status.status_name AS booking_status_name,\n"
                    + "    booking.booking_date AS booking_booking_date, \n"
                    + "    booking.note AS booking_note, \n"
                    + "    booking.post_id AS booking_post_id,\n"
                    + "    booking.check_in_date AS booking_check_in_date, \n"
                    + "    booking.check_out_date AS booking_check_out_date, \n"
                    + "    booking.payment_method AS booking_payment_method_id,\n"
                    + "    booking_payment_method.method_name AS booking_payment_method_name,\n"
                    + "    booking.quantity_of_people\n"
                    + "FROM \n"
                    + "    booking\n"
                    + "JOIN\n"
                    + "    post ON booking.post_id = post.post_id\n"
                    + "JOIN \n"
                    + "    user AS user2 ON booking.user_id = user2.user_id\n"
                    + "JOIN \n"
                    + "	request_status as booking_status on booking_status.status_id = booking_status \n"
                    + "JOIN \n"
                    + "	payment_method as booking_payment_method on booking_payment_method.method_id = booking.payment_method \n"
                    + "WHERE\n"
                    + "    post.poster_id = ? and booking.booking_status = 1";
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, poster);
            stm.setInt(2, poster);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Booking booking = new Booking();
                Meeting meeting = new Meeting();

                if (rs.getInt("appointment_id") != 0) {
                    User meeting_user = new User();

                    meeting_user.setUser_id(rs.getInt("customer_id"));
                    meeting_user.setFull_name(rs.getString("appointment_customer_name"));
                    meeting_user.setEmail(rs.getString("appointment_customer_email"));
                    meeting_user.setPhone_number(rs.getString("appointment_customer_phone"));
                    meeting_user.setAvatar(rs.getString("appointment_customer_avatar"));
                    Status meetingStatus = new Status();
                    meetingStatus.setStatus_id(rs.getInt("appointment_status_id"));
                    meetingStatus.setStatus_name(rs.getString("appointment_status_name"));
                    Timestamp meetingBookingTimestamp = rs.getTimestamp("appointment_booking_date");
                    Timestamp meetingTimestamp = rs.getTimestamp("meeting_date");
                    LocalDateTime meetingBookingDateTime = meetingBookingTimestamp.toLocalDateTime();
                    LocalDateTime meetingDateTime = meetingTimestamp.toLocalDateTime();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                    String fommattedMeetingBookingDateTime = meetingBookingDateTime.format(formatter);
                    String fommattedMeetingDateTime = meetingDateTime.format(formatter);
                    meeting.setFommattedBookingDate(fommattedMeetingBookingDateTime);
                    meeting.setFommattedMeetingDate(fommattedMeetingDateTime);
                    meeting.setBookingDate(meetingBookingDateTime);
                    meeting.setMeetingDate(meetingDateTime);

                    meeting.setMeeting_id(rs.getInt("appointment_id"));
                    meeting.setCustomer(meeting_user);
                    meeting.setPostId(rs.getInt("post_id"));
                    meeting.setNote(rs.getString("appointment_note"));
                    meeting.setMeetingStatus(meetingStatus);
                } else {
                    User booking_user = new User();
                    booking_user.setUser_id(rs.getInt("booking_user_id"));
                    booking_user.setFull_name(rs.getString("booking_full_name"));
                    booking_user.setEmail(rs.getString("booking_email"));
                    booking_user.setPhone_number(rs.getString("booking_phone_number"));
                    booking_user.setAvatar(rs.getString("booking_avatar"));

                    Status bookingStatus = new Status();
                    bookingStatus.setStatus_id(rs.getInt("booking_status_id"));
                    bookingStatus.setStatus_name(rs.getString("booking_status_name"));

                    PaymentMethod bookingPaymentMethod = new PaymentMethod();
                    bookingPaymentMethod.setMethod_id(rs.getInt("booking_payment_method_id"));
                    bookingPaymentMethod.setMethod_name(rs.getString("booking_payment_method_name"));

                    booking.setBooking_id(rs.getInt("booking_id"));
                    booking.setPost_id(rs.getInt("booking_post_id"));
                    booking.setNote(rs.getString("booking_note"));
                    booking.setUser(booking_user);
                    booking.setPayment_method(bookingPaymentMethod);
                    booking.setQuantityOfpeople(rs.getInt("quantity_of_people"));
                    booking.setStatus(bookingStatus);

                    Timestamp bookingBookingTimestamp = rs.getTimestamp("booking_booking_date");
                    Timestamp checkInTimestamp = rs.getTimestamp("booking_check_in_date");
                    Timestamp checkOutTimestamp = rs.getTimestamp("booking_check_out_date");
                    LocalDateTime bookingBookingDateTime = bookingBookingTimestamp.toLocalDateTime();
                    LocalDateTime checkIntDateTime = checkInTimestamp.toLocalDateTime();
                    LocalDateTime checkOutDateTime = checkOutTimestamp.toLocalDateTime();

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                    String fommattedBookingBookingDateTime = bookingBookingDateTime.format(formatter);
                    String fommattedCheckIntDateTime = checkIntDateTime.format(formatter);
                    String fommattedCheckOutDateTime = checkOutDateTime.format(formatter);

                    booking.setFommatted_booking_date(fommattedBookingBookingDateTime);
                    booking.setFomatted_check_in_date(fommattedCheckIntDateTime);
                    booking.setFomatted_check_out_date(fommattedCheckOutDateTime);
                    booking.setBooking_date(bookingBookingDateTime);
                    booking.setCheck_in_date(checkIntDateTime);
                    booking.setCheck_out_date(checkOutDateTime);

                }
                Order order = new Order(booking, meeting);

                orders.add(order);
            }

            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orders;
    }
}
