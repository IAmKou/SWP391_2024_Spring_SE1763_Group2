/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Booking;

/**
 *
 * @author FPTSHOP
 */
public class BookingDAO extends DBContext {

    public void addBooking(Booking booking) {
        try {
            String sql = "INSERT INTO `house_finder_project`.`booking`\n"
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
        } catch (SQLException ex) {
            Logger.getLogger(BookingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
