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
import model.Account;
import model.User;

/**
 *
 * @author FPTSHOP
 */
public class AccountDAO extends DBContext {

    public Account getAccountByUserId(int user_id) {
        try {
            String sql = "Select * from `account` where `user_id` = ?;";
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, user_id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Account acc = new Account();
                acc.setUser_id(rs.getInt("user_id"));
                acc.setUser_name(rs.getString("user_name"));
                acc.setPass_word(rs.getString("pass_word"));
                acc.setRole_id(rs.getInt("role_id"));
                acc.setActive(rs.getBoolean("active"));
                return acc;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public void changeStatus(int user_id) {
        try {
            String sql = "UPDATE `house_finder_project`.`account` SET `active` = ? WHERE (`user_id` = ?)";
            UserDAO uDAO = new UserDAO();

            User uObj = uDAO.getUserByID(user_id);
            Account aObj = uDAO.getAccount(user_id);

            DBContext db = new DBContext();
            Connection con = db.getConnection();

            PreparedStatement stm = con.prepareStatement(sql);
            Boolean updateStatus = true;
            if (aObj.isActive()) {
                updateStatus = false;
            } else {
                updateStatus = true;
            }

            stm.setBoolean(1, updateStatus);
            stm.setInt(2, user_id);
            // Thực thi câu lệnh SQL UPDATE
            int rowsAffected = stm.executeUpdate();

            if (rowsAffected > 0) {
                System.err.println("Trạng thái tài khoản đã được cập nhật thành công.");
            } else {
                System.err.println("Không có bản ghi nào được cập nhật.");
            }
            stm.close();
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        AccountDAO aDAO = new AccountDAO();
        System.out.println(aDAO.getAccountByUserId(3));
        aDAO.changeStatus(3);
        System.out.println("---------");
        System.out.println(aDAO.getAccountByUserId(3));

    }

}
