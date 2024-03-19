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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Meeting;

/**
 *
 * @author FPTSHOP
 */
public class MeetingDAO extends DBContext {

    public void ChangeCancelStatus(int meetingId, String message) {
        try {
            String sql = "UPDATE `appointment`\n"
                    + "SET\n"
                    + "`appointment_status` = 3,\n"
                    + "`response_message` = ?\n"
                    + "WHERE `appointment_id` = ?;";
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(2, meetingId);
            stm.setString(1, message);
            stm.executeUpdate();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(MeetingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void ChangeAcceptedStatus(int meetingId, String message) {
        try {
            String sql = "UPDATE `appointment`\n"
                    + "SET\n"
                    + "`response_message` = ?,\n"
                    + "`appointment_status` = 2\n"
                    + "WHERE `appointment_id` = ?;";
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, message);
            stm.setInt(2, meetingId);
            stm.executeUpdate();

            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(MeetingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addMeeting(Meeting meeting) {
        try {
            String sql = "INSERT INTO `appointment`\n"
                    + "(`customer_id`,\n"
                    + "`post_id`,\n"
                    + "`booking_date`,\n"
                    + "`meeting_date`,\n"
                    + "`note`,\n"
                    + "`status`)\n"
                    + "VALUES\n"
                    + "(?,?,?,?,?,?);";
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, meeting.getCustomer().getUser_id());
            stm.setInt(2, meeting.getPostId());
            stm.setObject(3, meeting.getBookingDate());
            stm.setObject(4, meeting.getMeetingDate());
            stm.setString(5, meeting.getNote());
            stm.setInt(6, meeting.getMeetingStatus().getStatus_id());

            stm.executeUpdate();

            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(MeetingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public boolean checkDuplicate(int customerId, int postId) {
        Boolean isDuplicate = false;
        try {
            String sql = "SELECT COUNT(*) AS count_duplicates\n"
                    + "FROM appointment\n"
                    + "WHERE customer_id = ? AND post_id = ? AND status = 1;";
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, customerId);
            stm.setInt(2, postId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                int count = rs.getInt("count_duplicates");
                isDuplicate = count > 0;
            }
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(MeetingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isDuplicate;
    }

}
