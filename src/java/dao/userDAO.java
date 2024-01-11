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
import model.user;

/**
 *
 * @author ACER
 */
public class userDAO {
    public static void insertUser(user user) {
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();

            // Prepare the SQL statement
            String sql = "INSERT INTO USER (fullName, userName, passWord, roleID, location, phone, email)"
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement st = con.prepareStatement(sql);
            st = con.prepareStatement(sql);
            st.setString(1, user.getFullName());
            st.setString(2, user.getUserName());
            st.setString(3, user.getPassWord());
            st.setInt(4, user.getRoleID());
            st.setString(5, user.getLocation());
            st.setString(6, user.getPhone());
            st.setString(7, user.getEmail());

            // Execute the SQL statement
            if (st.executeUpdate() != 1) {
                System.out.println("ERROR INSERTING User");
            }
            st.close();
            con.close();
            // Any additional code or processing after inserting the user
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
        public boolean phoneIsExist(String phone) {
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            String query = "select count(*) as num from USER where phone = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, phone);
            ResultSet rslt = ps.executeQuery();
            if (rslt.next()) {
                return Integer.parseInt(rslt.getString(1)) > 0;
            }
        } catch (SQLException e) {
        }
        return false;
    }

    public boolean emailIsExist(String email) {
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            String query = "select count(*) as num from USER where email = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, email);
            ResultSet rslt = ps.executeQuery();
            if (rslt.next()) {
                return Integer.parseInt(rslt.getString(1)) > 0;
            }
        } catch (SQLException e) {
        }
        return false;
    }
     public static user LogIn(String username, String pass) {
        user user = null;
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            if (con != null) {
                String sql = "Select * from USER where userName = " + "'" + username + "'"
                        + "AND passWord = " + "'" + pass + "' limit 1";
                Statement call = con.createStatement();
                ResultSet rs = call.executeQuery(sql);
                while (rs.next()) {
                    user = new user(rs.getInt("userId"),
                            username, pass, rs.getString("fullName"), rs.getInt("roleId"),
                            rs.getString("location"), rs.getString("phone"),
                            rs.getString("email"));
                }
                call.close();
                con.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return user;
    }
}
