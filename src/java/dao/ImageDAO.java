/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Context.DBContext;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Image;

/**
 *
 * @author FPTSHOP
 */
public class ImageDAO extends DBContext {

    public void deleteImages(int house_id) {
        try {
            String sql = "DELETE FROM `house_finder_project`.`image`\n"
                    + "WHERE house_id = ?;";
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, house_id);
            stm.executeUpdate();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ImageDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void addImages(int house_id, byte[] image) {
        try {
            String sql = "INSERT INTO image (house_id, image_link) VALUES (?, ?)";
            DBContext db = new DBContext();
            try ( Connection con = db.getConnection()) {
                PreparedStatement stm = con.prepareStatement(sql);
                stm.setInt(1, house_id);
                stm.setBytes(2, image);
                stm.executeUpdate();

            }
        } catch (SQLException ex) {
            Logger.getLogger(ImageDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Image> getImages(int house_id) {
        List<Image> images = new ArrayList<>();
        try {

            String sql = "SELECT image_link, image_id FROM house_finder_project.image where house_id = ?;";
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, house_id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                
                Blob blob = rs.getBlob("image_link");
                byte[] imageData = blob.getBytes(1, (int) blob.length());

                int imageId = rs.getInt("image_id");

                Image image = new Image();
                image.setImageData(imageData);
                image.setImage_id(imageId);
                images.add(image);
            }
            rs.close();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ImageDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return images;
    }
}
