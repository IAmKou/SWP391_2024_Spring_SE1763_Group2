/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Context.DBContext;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Booking;
import model.House;
import model.User;

/**
 *
 * @author FPTSHOP
 */
public class BookingDAO extends DBContext {

    public List<Booking> getCustomerBooking(int user_id) {
        List<Booking> bookings = new ArrayList<>();
        try {
            String sql = "SELECT * FROM house_finder_project.booking\n"
                    + "where customer_id = ?";
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, user_id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Booking order = new Booking();
                order.setBooking_id(rs.getInt("booking_id"));
                order.setStatus_id(rs.getInt("status_id"));
                order.setMessage(rs.getString("message"));

                House house = new House();
                house.setHouse_id(rs.getInt("house_id"));
                order.setHouse(house);

                User user = new User();
                user.setUser_id(rs.getInt("customer_id"));
                order.setUser(user);

                Timestamp timeStamp = rs.getTimestamp("booking_date");
                LocalDateTime booking_date = timeStamp.toLocalDateTime();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String formattedDateTime = booking_date.format(formatter);
                order.setBooking_date(booking_date);
                order.setFommatted_booking_date(formattedDateTime);
                order.setMeeting_date(rs.getDate("meeting_date"));
                
                bookings.add(order);
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(BookingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bookings;
    }

    public void deleteBookingByHouseID(int house_id) {
        try {
            String sql = "DELETE FROM `house_finder_project`.`booking`\n"
                    + "WHERE house_id = ?;";
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, house_id);
            stm.executeUpdate();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(BookingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<Booking> getListBookingInformation(int user_id) {
        List<Booking> bookings = new ArrayList<>();
        try {

            String sql = "SELECT *\n"
                    + "FROM `house_finder_project`.`booking`\n"
                    + "join house on house.house_id = booking.house_id\n"
                    + "join user on user.user_id = booking.customer_id\n"
                    + "where house.house_owner_id = ? and booking.status_id = 1;";
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, user_id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setFull_name(rs.getString("full_name"));
                user.setEmail(rs.getString("email"));
                user.setPhone_number(rs.getString("phone_number"));

                House house = new House();
                house.setLocation(rs.getString("house.address"));

                Booking order = new Booking();
                order.setBooking_id(rs.getInt("booking_id"));
                order.setMessage(rs.getString("message"));
                
                Timestamp timeStamp = rs.getTimestamp("booking_date");
                LocalDateTime booking_date = timeStamp.toLocalDateTime();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String formattedDateTime = booking_date.format(formatter);
                order.setBooking_date(booking_date);
                order.setFommatted_booking_date(formattedDateTime);
                order.setMeeting_date(rs.getDate("meeting_date"));
                
                order.setUser(user);
                order.setHouse(house);

                bookings.add(order);
            }

            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(BookingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bookings;
    }

    public boolean checkDuplicateBooking(int house_id, int customer_id) {
        try {
            String sql = "SELECT COUNT(*) AS count\n"
                    + "FROM Booking\n"
                    + "WHERE customer_id = ?\n"
                    + "  AND house_id = ?\n"
                    + "  AND status_id = 1;";
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, customer_id);
            stm.setInt(2, house_id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                int count = rs.getInt("count");
                return count > 0; // Trả về true nếu count > 0, ngược lại trả về false
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(BookingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void addBooking(Booking order) {
        try {
            String sql = "INSERT INTO `house_finder_project`.`booking`\n"
                    + "(\n"
                    + "`customer_id`,\n"
                    + "`booking_date`,\n"
                    + "`status_id`,\n"
                    + "`house_id`,\n"
                    + "`message`,\n"
                    + "`meeting_date`\n"
                    + ")\n"
                    + "VALUES\n"
                    + "(?,?,?,?,?,?);";

            DBContext db = new DBContext();
            Connection con = db.getConnection();
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, order.getUser().getUser_id());
            stm.setObject(2, order.getBooking_date());
            stm.setInt(3, order.getStatus_id());
            stm.setInt(4, order.getHouse().getHouse_id());
            stm.setString(5, order.getMessage());
            stm.setObject(6, order.getMeeting_date());
            stm.executeUpdate();

            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(BookingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
