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
import model.account;

/**
 *
 * @author FPTSHOP
 */
public class AccountDAO extends DBContext {
    public account getAccountByUserId(int user_id){
        try {
            String sql="Select * from `account` where `user_id` = ?;";
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, user_id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {                
                account acc = new account();
                acc.setUser_id(rs.getInt("user_id"));
                acc.setUser_name(rs.getString("user_name"));
                acc.setPass_word(rs.getString("pass_word"));
                acc.setRole_id(rs.getInt("role_id"));
                return acc;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    return null;
    }
}
