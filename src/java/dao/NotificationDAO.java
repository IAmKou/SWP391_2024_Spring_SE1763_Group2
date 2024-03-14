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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.GNotification;
import model.Notification;

/**
 *
 * @author luong
 */
public class NotificationDAO {

    public void insertNotification(int recipient_id, String message, LocalDateTime created_at) {
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();

            // Prepare the SQL statement
            String sql = "INSERT INTO `notification` (recipient_id,message,create_at)"
                    + "VALUES (?, ?, ?)";
            PreparedStatement st = con.prepareStatement(sql);
            st = con.prepareStatement(sql);
            st.setInt(1, recipient_id);
            st.setString(2, message);
            st.setObject(3, created_at);
            int row = st.executeUpdate();
            st.close();
            con.close();
            // Any additional code or processing after inserting the user
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertGlobalNotification( String message, LocalDateTime created_at) {
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();

            // Prepare the SQL statement
            String sql = "INSERT INTO `global_notification` (message,created_at)"
                    + "VALUES (?, ?)";
            PreparedStatement st = con.prepareStatement(sql);
            st = con.prepareStatement(sql);
            st.setString(1, message);
            st.setObject(2, created_at);
            int row = st.executeUpdate();
            st.close();
            con.close();
            // Any additional code or processing after inserting the user
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Notification> getAllNotificationByUserId(int id) {
        ArrayList<Notification> list = new ArrayList<>();
        Connection con = new DBContext().getConnection();
        try {
            String sql = "SELECT * FROM notification where recipient_id ='" + id + "' ORDER BY create_at DESC";

            try ( PreparedStatement stm = con.prepareStatement(sql);  ResultSet rs = stm.executeQuery();) {
                while (rs.next()) {
                    Notification u = new Notification();
                    u.setNoti_id(rs.getInt("noti_id"));
                    u.setRecipient_id(rs.getInt("recipient_id"));
                    u.setMessage(rs.getString("message"));
                    u.setCreated_at(rs.getObject("create_at", LocalDateTime.class));
                    list.add(u);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

    public ArrayList<GNotification> getAllGlobalNotification(){
        ArrayList<GNotification> list = new ArrayList<>();
        Connection con = new DBContext().getConnection();
        try {
            String sql = "SELECT * FROM global_notification ORDER BY created_at DESC";

            try ( PreparedStatement stm = con.prepareStatement(sql);  ResultSet rs = stm.executeQuery();) {
                while (rs.next()) {
                    GNotification u = new GNotification();
                    u.setGn_id(rs.getInt("gn_id"));
                    u.setMessage(rs.getString("message"));
                    u.setCreated_at(rs.getObject("created_at", LocalDateTime.class));
                    list.add(u);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }
}
