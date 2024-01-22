/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Post;

/**
 *
 * @author FPTSHOP
 */
public class PostDAO {

    public void addPost(int houseId, Post post) {
        try {
            String postSql = "INSERT INTO post (house_id, purpose_id,"
                    + " price, poster_id, house_status, admin_id, post_status)"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?)";
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            PreparedStatement stm = con.prepareStatement(postSql);

            stm.setInt(1, houseId);
            stm.setInt(2, post.getPurpose_id());
            stm.setInt(3, post.getPrice());
            stm.setInt(4, post.getPoster_id());
            stm.setInt(5, post.getHouse_status());
            stm.setInt(6, post.getAdmin_id());
            stm.setInt(7, post.getPost_status());

            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(HouseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
