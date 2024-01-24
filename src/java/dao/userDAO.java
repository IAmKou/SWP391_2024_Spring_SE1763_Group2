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
import java.util.ArrayList;
import model.account;
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
            String sql = "INSERT INTO `user` (full_name,date_of_birth,address,phone_number,email)"
                    + "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement st = con.prepareStatement(sql);
            st = con.prepareStatement(sql);
            st.setString(1, user.getFull_name());
            st.setDate(2, user.getDate_of_birth());
            st.setString(3, user.getAddress());
            st.setInt(4, user.getPhone_number());
            st.setString(5, user.getEmail());

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
        public boolean phoneIsExist(int phone) {
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            String query = "select count(*) as num from `user` where `phone_number` = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, phone);
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
            String query = "select count(*) as num from `user` where `email` = ?";
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
     public static account LogIn(String username, String pass) {
        account account = null;
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            if (con != null) {
                String sql = "Select * from `account` where `user_name` = '" + username + "' AND `pass_word` = '" + pass + "'";
                Statement call = con.createStatement();
                ResultSet rs = call.executeQuery(sql);
                while (rs.next()) {
                    account = new account(rs.getInt("user_id"),username, pass, rs.getInt("role_id"));
                }
                call.close();
                con.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return account;
    }
      public static boolean ChangePassword(int user_id, String password) {
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            if (con != null) {
                String sql = "UPDATE `account` SET `pass_word` = '" + password + "' where `user_id` = '" + user_id + "'";
                Statement st = con.createStatement();
                int rows = st.executeUpdate(sql);
                if (rows < 1) {
                    throw new Exception();
                }
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
      public static user getUserInformation(int id) {
        user user = null;
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            if (con != null) {
                String sql = "Select * from `user` where `user_id`=" + id;
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    user = new user();
                    user.setUser_id(rs.getInt(1));
                    user.setFull_name(rs.getString(2));
                    user.setDate_of_birth(rs.getDate(3));
                    user.setAddress(rs.getString(4));
                    user.setPhone_number(rs.getInt(5));
                    user.setEmail(rs.getString(6));
                }
                rs.close();
                st.close();
                con.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return user;
    }
       public static user getUserByEmail(String email) {
        user user = new user();
        String sql = "Select * from `user` where `email` = '" + email + "';";
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            PreparedStatement ps = con.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user.setUser_id(rs.getInt("user_id"));
                user.setFull_name(rs.getString("full_name"));
                user.setDate_of_birth(rs.getDate("date_of_birth"));
                user.setAddress(rs.getString("address"));
                user.setPhone_number(rs.getInt("phone_number"));
                user.setEmail(rs.getString("email"));
                
                return user;

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
      public static void insertAccount(account account) {
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();

            // Prepare the SQL statement
            String sql = "INSERT INTO `account` (`user_id`,`user_name`, `pass_word`,`role_id`)"
                    + "VALUES (?, ?, ?, ?)";
            PreparedStatement st = con.prepareStatement(sql);
            st = con.prepareStatement(sql);
            st.setInt(1, account.getUser_id());
            st.setString(2, account.getUser_name());
            st.setString(3, account.getPass_word());
            st.setInt(4, account.getRole_id());
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
}
