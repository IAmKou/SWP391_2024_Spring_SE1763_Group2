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
import model.House;

/**
 *
 * @author FPTSHOP
 */
public class HouseDAO extends DBContext {

    public House getHouse(int houseId) {
        try {
            String sql = "SELECT * FROM house_finder.house\n"
                    + "where House_id = ?";
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, houseId);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                House house = new House();
                house.setHouseId(rs.getInt("House_id"));
                house.setLocation(rs.getString("location"));
                house.setType(rs.getInt("type_of_house_id"));
                house.setDescription(rs.getString("description"));
                house.setHouseOwnerId(rs.getInt("house_owner_id"));
                house.setPicture(rs.getString("picture"));
                house.setPrice(rs.getInt("price_per_unit"));
                house.setStatus(rs.getBoolean("status"));
                return house;
            }
        } catch (SQLException ex) {
            Logger.getLogger(HouseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void updateHouse(House house) {
        try {
            String sql = "UPDATE `house_finder`.`house`\n"
                    + "SET\n"
                    + "`location` = ?,\n"
                    + "`type_of_house_id` = ?,\n"
                    + "`description` = ?,\n"
                    + "`price_per_unit` = ?,\n"
                    + "`picture` = ?,\n"
                    + "`status` = ?\n"
                    + "WHERE `house_id` = ?;";
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, house.getLocation());
            stm.setInt(2, house.getType());
            stm.setString(3, house.getDescription());
            stm.setInt(4, house.getPrice());
            stm.setString(5, house.getPicture());
            stm.setBoolean(6, house.isStatus());
            stm.setInt(7, house.getHouseId());

            stm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(HouseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addHouse(House house) {
        try {
            String sql = "INSERT INTO house_finder.house (location, type_of_house_id, description, house_owner_id, price_per_unit, picture, status) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)";

            DBContext db = new DBContext();
            Connection con = db.getConnection();
            PreparedStatement stm = con.prepareStatement(sql);

            stm.setString(1, house.getLocation());
            stm.setInt(2, house.getType());
            stm.setString(3, house.getDescription());
            stm.setInt(4, house.getHouseOwnerId());
            stm.setInt(5, house.getPrice());
            stm.setString(6, house.getPicture());
            stm.setBoolean(7, house.isStatus());

            stm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(HouseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
