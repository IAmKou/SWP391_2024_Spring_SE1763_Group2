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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.House;
import model.Post;

/**
 *
 * @author FPTSHOP
 */
public class HouseDAO extends DBContext {

    public void updateHouse(House house) {

        try {
            String sql = "UPDATE `house_finder_project`.`house`\n"
                    + "SET\n"
                    + "`type_of_house_id` = ?,\n"
                    + "`address` = ?,\n"
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

    public void addHouse(House house, Post post) {
        try {
            String sql = "INSERT INTO house (house_owner_id, type_of_house_id,"
                    + " address, description, area, number_of_room) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";
            DBContext db = new DBContext();

            try ( Connection con = db.getConnection()) {
                // Thêm tham số Statement.RETURN_GENERATED_KEYS vào prepareStatement
                PreparedStatement stm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

                stm.setInt(1, post.getPoster_id());
                stm.setInt(2, house.getType_of_house().getType_of_house_id());
                stm.setString(3, house.getLocation());
                stm.setString(4, house.getDescription());
                stm.setInt(5, house.getArea());
                stm.setInt(6, house.getNumber_of_room());

                // Thực hiện INSERT và lấy house_id được tạo tự động
                int rowsAffected = stm.executeUpdate();
                if (rowsAffected > 0) {
                    ResultSet generatedKeys = stm.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        int houseId = generatedKeys.getInt(1);
                        PostDAO postDAO = new PostDAO();
                        postDAO.addPost(houseId, post);
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(HouseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
