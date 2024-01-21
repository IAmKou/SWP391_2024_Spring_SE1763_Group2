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
import model.TypeOfHouse;
import model.User;

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
            while (rs.next()) {
                House house = new House();

                TypeOfHouse tOfHouse = new TypeOfHouse();
                tOfHouse.setType_of_house_id(rs.getInt("type_of_house_id"));

                User user = new User();
                user.setUser_id(rs.getInt("house_owner_id"));

                house.setHouse_id(rs.getInt("house_id"));
                house.setHouse_owner(user);
                house.setType_of_house(tOfHouse);
                house.setLocation(rs.getString("address"));
                house.setDescription(rs.getString("description"));
                return house;
            }
        } catch (SQLException ex) {
            Logger.getLogger(HouseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void updateHouse(House house) {
        try {
            String sql = "UPDATE house\n"
                    + "SET house.house_owner_id = ?,\n"
                    + "    house.type_of_house_id = ?,\n"
                    + "    house.address = ?,\n"
                    + "    house.description = ?,\n"
                    + "    house.area = ?,\n"
                    + "    house.number_of_room = ?,\n"
                    + "WHERE house.house_id = ?;";
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, house.getHouse_owner().getUser_id());
            stm.setInt(2, house.getType_of_house().getType_of_house_id());
            stm.setString(3, house.getLocation());
            stm.setString(4, house.getDescription());
            stm.setInt(5, house.getArea());
            stm.setInt(6, house.getNumber_of_room());
            stm.setInt(7, house.getHouse_id());

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
            stm.setInt(2, house.getType().getTypeOfHouseId());
            stm.setString(3, house.getDescription());
            stm.setInt(4, house.getHouseOwnerId().getUserId());
            stm.setInt(5, house.getPrice());
            stm.setString(6, house.getPicture());
            stm.setBoolean(7, house.isStatus());

            stm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(HouseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
