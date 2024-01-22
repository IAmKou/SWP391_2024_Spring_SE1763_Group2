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
import java.util.List;
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

        try {
            String sql = "UPDATE `house_finder_project`.`image`\n"
                    + "SET\n"
                    + "`house_id` = ?,\n"
                    + "`image_link` = ?\n"
                    + "WHERE `house_id` = ?;";
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            PreparedStatement stm = con.prepareStatement(sql);

            for (String imageLink : house.getImage_URL()) {

                stm.setInt(1, house.getHouse_id());
                stm.setString(2, imageLink);
                stm.setInt(3, house.getHouse_id());

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
            Connection con = db.getConnection();

            // Thêm tham số Statement.RETURN_GENERATED_KEYS vào prepareStatement
            PreparedStatement stm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stm.setInt(1, house.getHouse_owner().getUser_id());
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

                    // Thêm hình ảnh cho nhà vừa thêm
                    //addImagesForHouse(houseId, house.getImage_URL());

                    // Thêm thông tin Post
                    PostDAO postDAO = new PostDAO();
                    postDAO.addPost(houseId, post);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(HouseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void addImagesForHouse(int houseId, List<String> imageLinks) {
    try {
        String insertImageSQL = "INSERT INTO image (house_id, image_link) VALUES (?, ?)";
        DBContext db = new DBContext();
        Connection con = db.getConnection();
        PreparedStatement stm = con.prepareStatement(insertImageSQL);

        for (String imageLink : imageLinks) {
            stm.setInt(1, houseId);
            stm.setString(2, imageLink);

            // Thêm vào batch
            stm.addBatch();
        }

        // Thực hiện batch update
        stm.executeBatch();
    } catch (SQLException ex) {
        Logger.getLogger(HouseDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
}

}
