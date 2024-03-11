/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Context.DBContext;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.House;

/**
 *
 * @author FPTSHOP
 */
public class HouseDAO extends DBContext {

    public void updateHouseStatus(int postId, int statusId) {
        try {
            String sql = "UPDATE `house_finder_project`.`post`\n"
                    + "SET\n"
                    + "`house_status` = ?\n"
                    + "WHERE `post_id` = ?;";
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, statusId);
            stm.setInt(2, postId);
            stm.executeUpdate();

            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(HouseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public House getHouseByPostID(int post_id) throws IOException {
        House house = new House();
        try {
            String sql
                    = "select * from post join house on post.house_id = house.house_id where post_id= ?";

            DBContext db = new DBContext();
            Connection con = db.getConnection();
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, post_id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {

                //     House test = new House(post_id, house_owner, type_of_house, sql, sql, post_id, post_id, image);
                house.setArea(rs.getInt("area"));
                house.setDescription(rs.getString("description"));
                house.setHouse_id(rs.getInt("house_id"));
                house.setLocation(rs.getString("location"));

                house.setNumber_of_room(rs.getInt("number_of_room"));
                house.setType_of_house(TypeOfHouseDAO.getType_of_House_Name_byID(rs.getInt("type_of_house_id")));
                house.setHouse_owner(UserDAO.getUserInformation(rs.getInt("house_owner_id")));
                house.setImage(ImageDAO.getImages(house.getHouse_id()));

                rs.close();
                stm.close();
                con.close();

            }

        } catch (SQLException ex) {
            Logger.getLogger(HouseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return house;
    }

    // Not done
    public House getHouseByID(int house_id) {
        try {
            String sql = "SELECT \n"
                    + "    `house`.`house_owner_id`\n"
                    + "FROM `house_finder_project`.`house`"
                    + " join post on post.house_id = house_id"
                    + "where house_id = ?;";

            DBContext db = new DBContext();
            Connection con = db.getConnection();
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, house_id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                int house_owner_id = rs.getInt("house_owner_id");

                rs.close();
                stm.close();
                con.close();

                return null;
            }

        } catch (SQLException ex) {
            Logger.getLogger(HouseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void updateHouse(House house) {

        try {
            String sql = "UPDATE `house_finder_project`.`house`\n"
                    + "SET\n"
                    + "`type_of_house_id` = ?,\n"
                    + "`location` = ?,\n"
                    + "`description` = ?,\n"
                    + "`area` = ?,\n"
                    + "`number_of_room` = ?\n"
                    + "WHERE `house_id` = ?;";
            DBContext db = new DBContext();
            try ( Connection con = db.getConnection();  PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setInt(1, house.getType_of_house().getType_of_house_id());
                stm.setString(2, house.getLocation());
                stm.setString(3, house.getDescription());
                stm.setInt(4, house.getArea());
                stm.setInt(5, house.getNumber_of_room());
                stm.setInt(6, house.getHouse_id());

                stm.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(HouseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public int addHouse(House house) {
        try {
            String sql = "INSERT INTO house (house_owner_id, type_of_house_id,"
                    + " location, description, area, number_of_room) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";
            DBContext db = new DBContext();

            try ( Connection con = db.getConnection()) {
                // Thêm tham số Statement.RETURN_GENERATED_KEYS vào prepareStatement
                PreparedStatement stm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

                stm.setInt(1, house.getHouse_owner().getUser_id());
                stm.setInt(2, house.getType_of_house().getType_of_house_id());
                stm.setString(3, house.getLocation());
                stm.setString(4, house.getDescription());
                stm.setInt(5, house.getArea());
                stm.setInt(6, house.getNumber_of_room());

                stm.executeUpdate();

                // Lấy house_id từ ResultSet
                ResultSet generatedKeys = stm.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int houseId = generatedKeys.getInt(1);
                    return houseId;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(HouseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public int numberOfHouse() {
        int n = 0;
        try {
            String sql
                    = "select * from post join house on post.house_id = house.house_id ";

            DBContext db = new DBContext();
            Connection con = db.getConnection();
            PreparedStatement stm = con.prepareStatement(sql);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                n++;
            }

            rs.close();
            stm.close();
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(HouseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

}
