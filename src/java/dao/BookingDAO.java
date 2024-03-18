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
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Booking;
import model.PaymentMethod;
import model.Status;
import model.User;

/**
 *
 * @author FPTSHOP
 */
public class BookingDAO extends DBContext {

    public void changeCancelStatus(int bookingId, String message) {
        try {
            String sql = "UPDATE `booking`\n"
                    + "SET\n"
                    + "`response_message` = ?,\n"
                    + "`booking_status` = 3\n"
                    + "WHERE `booking_id` = ?";
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(2, bookingId);
            stm.setString(1, message);
            stm.executeUpdate();

            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(BookingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void changeAcceptedStatus(int bookingId, String message) {
        try {
            String sql = "UPDATE `booking`\n"
                    + "SET\n"
                    + "`response_message` = ?,\n"
                    + "`booking_status` = 2\n"
                    + "WHERE `booking_id` = ?;";
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, message);
            stm.setInt(2, bookingId);
            stm.executeUpdate();

            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(BookingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void addBooking(Booking booking) throws SQLException {
            String sql = "INSERT INTO `booking`\n"
                    + "(`user_id`,\n"
                    + "`booking_status`,\n"
                    + "`booking_date`,\n"
                    + "`note`,\n"
                    + "`post_id`,\n"
                    + "`check_in_date`,\n"
                    + "`check_out_date`,\n"
                    + "`payment_method`)\n"
                    + "VALUES\n"
                    + "(?,?,?,?,?,?,?,?);";
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, booking.getUser().getUser_id());
            stm.setInt(2, booking.getStatus().getStatus_id());
            stm.setObject(3, booking.getBooking_date());
            stm.setString(4, booking.getNote());
            stm.setInt(5, booking.getPost_id());
            stm.setObject(6, booking.getCheck_in_date());
            stm.setObject(7, booking.getCheck_out_date());
            stm.setInt(8, booking.getPayment_method().getMethod_id());

            stm.executeUpdate();

            con.close();

    }

    public boolean checkDuplicateBooking(int customerId, int postId) {
        boolean isExist = false;
        try {
            String sql = "SELECT COUNT(*) AS duplicates\n"
                    + "FROM booking\n"
                    + "WHERE user_id = ? AND post_id = ? AND booking_status = 1;";
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, customerId);
            stm.setInt(2, postId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                int count = rs.getInt("duplicates");
                isExist = count > 0;
            }

            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(BookingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isExist;
    }

    public Booking getBookingByPost(int post_id, int user_id) {
        Booking b = null;
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            if (con != null) {
                String sql = "Select booking.*, user.full_name,user.avatar from booking join user on user.user_id = booking.user_id where booking.post_id = '" + post_id + "' and booking.user_id = '" + user_id + "'";
                Statement call = con.createStatement();
                ResultSet rs = call.executeQuery(sql);
                while (rs.next()) {
                    User u = new User();
                    u.setUser_id(rs.getInt("user_id"));
                    u.setAvatar(rs.getString("avatar"));
                    u.setFull_name(rs.getString("full_name"));

                    Status booking_status = new Status();
                    booking_status.setStatus_id(rs.getInt("booking_status"));

                    PaymentMethod p = new PaymentMethod();
                    p.setMethod_id(rs.getInt("payment_method"));

                    b = new Booking();
                    b.setBooking_id(rs.getInt("booking_id"));
                    b.setUser(u);
                    b.setBooking_date(rs.getObject("booking_date", LocalDateTime.class));
                    b.setStatus(booking_status);
                    b.setPost_id(rs.getInt("post_id"));
                    b.setNote(rs.getString("note"));
                    b.setCheck_in_date(rs.getObject("check_in_date", LocalDateTime.class));
                    b.setCheck_out_date(rs.getObject("check_out_date", LocalDateTime.class));
                    b.setPayment_method(p);
                    b.setQuantityOfpeople(rs.getInt("quantity_of_people"));
                    b.setResponseMessage(rs.getString("response_message"));
                }
                call.close();
                rs.close();
                con.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return b;
    }
}
