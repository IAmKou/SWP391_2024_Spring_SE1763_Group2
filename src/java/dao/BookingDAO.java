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
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author FPTSHOP
 */
public class BookingDAO extends DBContext {

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
            if(rs.next()){
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void addBooking(int customer_id, int house_id, String booking_date, int status_id) {
        try {
            String sql = "INSERT INTO `house_finder_project`.`booking`\n"
                    + "(\n"
                    + "`customer_id`,\n"
                    + "`booking_date`,\n"
                    + "`status_id`,\n"
                    + "`house_id`)\n"
                    + "VALUES\n"
                    + "(?,?,?,?);";
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, customer_id);
            stm.setString(2, booking_date);
            stm.setInt(3, status_id);
            stm.setInt(4, house_id);

            stm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(BookingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
