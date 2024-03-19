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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Purpose;

/**
 *
 * @author FPTSHOP
 */
public class PurposeDAO extends DBContext {

    public List<Purpose> getPurpose() {
        List<Purpose> purposes = new ArrayList<>();

        try {
            String sql = "SELECT * FROM purpose;";
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            PreparedStatement stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Purpose purpose = new Purpose();
                purpose.setPurpose_id(rs.getInt(1));
                purpose.setPurpose_name(rs.getString(2));

                purposes.add(purpose);
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(PurposeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return purposes;
    }

    public Purpose getPurposeById(int id) {

        try {
            String sql = "SELECT * FROM purpose where purpose_id = ? limit 1;";
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Purpose purpose = new Purpose();
                purpose.setPurpose_id(rs.getInt(1));
                purpose.setPurpose_name(rs.getString(2));
                return purpose;
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(PurposeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
