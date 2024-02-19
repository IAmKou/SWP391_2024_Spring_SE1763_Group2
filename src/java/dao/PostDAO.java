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
import model.House;
import model.Post;
import model.Purpose;
import model.Status;
import model.TypeOfHouse;
import model.User;

/**
 *
 * @author FPTSHOP
 */
public class PostDAO {

    public List<Post> getUserPost(int user_id) {
        List<Post> posts = new ArrayList<>();
        try {
            String sql = "SELECT post.post_id, post.house_id, purpose.purpose_name, post.price,"
                    + " house_status.status_name as 'house_status', \n"
                    + "post_status.status_name as'post_status', "
                    + "type_of_house.type_of_house_name, house.address as 'location', "
                    + "house.description,\n"
                    + "house.area,house.number_of_room,post.poster_id,"
                    + "user.full_name, user.date_of_birth, user.address,user.phone_number,user.email\n"
                    + "\n"
                    + " FROM post\n"
                    + "join \n"
                    + "	house on house.house_id = post.house_id\n"
                    + "join \n"
                    + "	user on post.poster_id = user.user_id\n"
                    + "join \n"
                    + "	purpose on purpose.purpose_id = post.purpose_id\n"
                    + "JOIN \n"
                    + "    request_status AS house_status ON house_status.status_id = post.house_status\n"
                    + "JOIN \n"
                    + "    request_status AS post_status ON post_status.status_id = post.post_status\n"
                    + "join \n"
                    + "	type_of_house on type_of_house.type_of_house_id = house.type_of_house_id\n"
                    + "    where poster_id = ? and post.post_status IN (2, 3)\n"
                    + "    ";
            DBContext db = new DBContext();
            PreparedStatement stm;
            ResultSet rs;
            try (Connection con = db.getConnection()) {
                stm = con.prepareStatement(sql);
                stm.setInt(1, user_id);
                rs = stm.executeQuery();
                while (rs.next()) {
                    TypeOfHouse type_of_house = new TypeOfHouse();
                    type_of_house.setType_of_house_name(rs.getString("type_of_house_name"));
                    
                    Status house_status = new Status();
                    house_status.setStatus_name(rs.getString("house_status"));
                    
                    Status post_status = new Status();
                    post_status.setStatus_name(rs.getString("post_status"));
                    
                    Purpose purpose = new Purpose();
                    purpose.setPurpose_name(rs.getString("purpose_name"));
                    
                    User user = new User();
                    user.setFull_name(rs.getString("full_name"));
                    user.setAddress(rs.getString("address"));
                    user.setPhone_number(rs.getString("phone_number"));
                    user.setEmail(rs.getString("email"));
                    
                    House house = new House();
                    house.setHouse_owner(user);
                    house.setHouse_id(rs.getInt("house_id"));
                    house.setLocation(rs.getString("location"));
                    house.setType_of_house(type_of_house);
                    house.setArea(rs.getInt("area"));
                    house.setDescription(rs.getString("description"));
                    house.setNumber_of_room(rs.getInt("number_of_room"));
                    
                    Post post = new Post();
                    post.setPost_id(rs.getInt("post_id"));
                    post.setPrice(rs.getInt("price"));
                    post.setHouse(house);
                    post.setHouse_status(house_status);
                    post.setPost_status(post_status);
                    post.setPurpose(purpose);
                    
                    posts.add(post);
                }
            }
            stm.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return posts;
    }

    public List<Post> getPostByStatus(int user_id, int status_id) {
        List<Post> posts = new ArrayList<>();

        try {
            String sql = "SELECT post.post_id, post.house_id, purpose.purpose_name, post.price,"
                    + " house_status.status_name as 'house_status', \n"
                    + "post_status.status_name as'post_status', "
                    + "type_of_house.type_of_house_name, house.address as 'location', "
                    + "house.description,\n"
                    + "house.area,house.number_of_room,post.poster_id,"
                    + "user.full_name, user.date_of_birth, user.address,user.phone_number,user.email\n"
                    + "\n"
                    + " FROM post\n"
                    + "join \n"
                    + "	house on house.house_id = post.house_id\n"
                    + "join \n"
                    + "	user on post.poster_id = user.user_id\n"
                    + "join \n"
                    + "	purpose on purpose.purpose_id = post.purpose_id\n"
                    + "JOIN \n"
                    + "    request_status AS house_status ON house_status.status_id = post.house_status\n"
                    + "JOIN \n"
                    + "    request_status AS post_status ON post_status.status_id = post.post_status\n"
                    + "join \n"
                    + "	type_of_house on type_of_house.type_of_house_id = house.type_of_house_id\n"
                    + "    where poster_id = ? and post.post_status = ?\n"
                    + "    ";
            DBContext db = new DBContext();
            try ( Connection con = db.getConnection();  PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setInt(1, user_id);
                stm.setInt(2, status_id);
                ResultSet rs = stm.executeQuery();
                while (rs.next()) {
                    TypeOfHouse type_of_house = new TypeOfHouse();
                    type_of_house.setType_of_house_name(rs.getString("type_of_house_name"));

                    Status house_status = new Status();
                    house_status.setStatus_name(rs.getString("house_status"));

                    Status post_status = new Status();
                    post_status.setStatus_name(rs.getString("post_status"));

                    Purpose purpose = new Purpose();
                    purpose.setPurpose_name(rs.getString("purpose_name"));

                    User poster = new User();
                    poster.setUser_id(rs.getInt("poster_id"));
                    poster.setFull_name(rs.getString("full_name"));
                    poster.setDate_of_birth(rs.getDate("date_of_birth"));
                    poster.setAddress(rs.getString("address"));
                    poster.setPhone_number(rs.getString("phone_number"));
                    poster.setEmail(rs.getString("email"));

                    House house = new House();
                    house.setHouse_id(rs.getInt("house_id"));
                    house.setLocation(rs.getString("location"));
                    house.setType_of_house(type_of_house);
                    house.setArea(rs.getInt("area"));
                    house.setDescription(rs.getString("description"));
                    house.setNumber_of_room(rs.getInt("number_of_room"));
                    house.setHouse_owner(poster);

                    Post post = new Post();
                    post.setPost_id(rs.getInt("post_id"));
                    post.setPrice(rs.getInt("price"));
                    post.setHouse(house);
                    post.setHouse_status(house_status);
                    post.setPost_status(post_status);
                    post.setPurpose(purpose);

                    posts.add(post);
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return posts;
    }

    public List<Post> getAllPost(int user_id) {
        List<Post> posts = new ArrayList<>();

        try {
            String sql = "SELECT post.post_id, post.house_id, purpose.purpose_name, post.price,"
                    + " house_status.status_name as 'house_status', \n"
                    + "post_status.status_name as'post_status', "
                    + "type_of_house.type_of_house_name, house.address as 'location', "
                    + "house.description,\n"
                    + "house.area,house.number_of_room,post.poster_id,"
                    + "user.full_name, user.date_of_birth, user.address,user.phone_number,user.email\n"
                    + "\n"
                    + " FROM post\n"
                    + "join \n"
                    + "	house on house.house_id = post.house_id\n"
                    + "join \n"
                    + "	user on post.poster_id = user.user_id\n"
                    + "join \n"
                    + "	purpose on purpose.purpose_id = post.purpose_id\n"
                    + "JOIN \n"
                    + "    request_status AS house_status ON house_status.status_id = post.house_status\n"
                    + "JOIN \n"
                    + "    request_status AS post_status ON post_status.status_id = post.post_status\n"
                    + "join \n"
                    + "	type_of_house on type_of_house.type_of_house_id = house.type_of_house_id\n"
                    + "    where poster_id = ?\n"
                    + "    ";
            DBContext db = new DBContext();
            try ( Connection con = db.getConnection();  PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setInt(1, user_id);
                ResultSet rs = stm.executeQuery();
                while (rs.next()) {

                    TypeOfHouse type_of_house = new TypeOfHouse();
                    type_of_house.setType_of_house_name(rs.getString("type_of_house_name"));

                    Status house_status = new Status();
                    house_status.setStatus_name(rs.getString("house_status"));

                    Status post_status = new Status();
                    post_status.setStatus_name(rs.getString("post_status"));

                    Purpose purpose = new Purpose();
                    purpose.setPurpose_name(rs.getString("purpose_name"));

                    User poster = new User();
                    poster.setUser_id(rs.getInt("poster_id"));
                    poster.setFull_name(rs.getString("full_name"));
                    poster.setDate_of_birth(rs.getDate("date_of_birth"));
                    poster.setAddress(rs.getString("address"));
                    poster.setPhone_number(rs.getString("phone_number"));
                    poster.setEmail(rs.getString("email"));

                    House house = new House();
                    house.setHouse_id(rs.getInt("house_id"));
                    house.setLocation(rs.getString("location"));
                    house.setType_of_house(type_of_house);
                    house.setArea(rs.getInt("area"));
                    house.setDescription(rs.getString("description"));
                    house.setNumber_of_room(rs.getInt("number_of_room"));
                    house.setHouse_owner(poster);

                    Post post = new Post();
                    post.setPost_id(rs.getInt("post_id"));
                    post.setPrice(rs.getInt("price"));
                    post.setHouse(house);
                    post.setHouse_status(house_status);
                    post.setPost_status(post_status);
                    post.setPurpose(purpose);

                    posts.add(post);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return posts;
    }

    public void addPost(int houseId, Post post) {
        try {
            String postSql = "INSERT INTO post (house_id, purpose_id,"
                    + " price, poster_id, house_status, post_status)"
                    + " VALUES (?, ?, ?, ?, ?, ?)";
            DBContext db = new DBContext();
            try ( Connection con = db.getConnection();  PreparedStatement stm = con.prepareStatement(postSql)) {

                stm.setInt(1, houseId);
                stm.setInt(2, post.getPurpose().getPurpose_id());
                stm.setInt(3, post.getPrice());
                stm.setInt(4, post.getPoster_id());
                stm.setInt(5, post.getHouse_status().getStatus_id());
                stm.setInt(6, post.getPost_status().getStatus_id());

                stm.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(HouseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updatePost(Post post) {
        try {
            String sql = "UPDATE `house_finder_project`.`post`\n"
                    + "SET\n"
                    + "`purpose_id` = ?,\n"
                    + "`price` = ?,\n"
                    + "`post_status` = ?\n"
                    + "WHERE `post_id` = ?;";
            DBContext db = new DBContext();
            try ( Connection con = db.getConnection();  PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setInt(1, post.getPurpose().getPurpose_id());
                stm.setInt(2, post.getPrice());
                stm.setInt(3, post.getPost_status().getStatus_id());
                stm.setInt(4, post.getPost_id());

                stm.executeUpdate();
                stm.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Post getPost(int post_id) {
        try {
            String sql = "SELECT post.post_id, post.house_id,purpose.purpose_id, purpose.purpose_name, post.price,"
                    + " house_status.status_name as 'house_status', \n"
                    + "post_status.status_name as'post_status', "
                    + "type_of_house.type_of_house_name,type_of_house.type_of_house_id, house.address as 'location', "
                    + "house.description,\n"
                    + "house.area,house.number_of_room,post.poster_id,"
                    + "user.full_name, user.date_of_birth, user.address,user.phone_number,user.email\n"
                    + "\n"
                    + " FROM post\n"
                    + "join \n"
                    + "	house on house.house_id = post.house_id\n"
                    + "join \n"
                    + "	user on post.poster_id = user.user_id\n"
                    + "join \n"
                    + "	purpose on purpose.purpose_id = post.purpose_id\n"
                    + "JOIN \n"
                    + "    request_status AS house_status ON house_status.status_id = post.house_status\n"
                    + "JOIN \n"
                    + "    request_status AS post_status ON post_status.status_id = post.post_status\n"
                    + "join \n"
                    + "	type_of_house on type_of_house.type_of_house_id = house.type_of_house_id\n"
                    + "    where post_id = ?\n"
                    + "    ";
            DBContext db = new DBContext();
            try ( Connection con = db.getConnection();  PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setInt(1, post_id);
                try ( ResultSet rs = stm.executeQuery()) {
                    while (rs.next()) {
                        TypeOfHouse type_of_house = new TypeOfHouse();
                        type_of_house.setType_of_house_id(rs.getInt("type_of_house_id"));
                        type_of_house.setType_of_house_name(rs.getString("type_of_house_name"));

                        Status house_status = new Status();
                        house_status.setStatus_name(rs.getString("house_status"));

                        Status post_status = new Status();
                        post_status.setStatus_name(rs.getString("post_status"));

                        Purpose purpose = new Purpose();
                        purpose.setPurpose_id(rs.getInt("purpose_id"));
                        purpose.setPurpose_name(rs.getString("purpose_name"));

                        User poster = new User();
                        poster.setUser_id(rs.getInt("poster_id"));
                        poster.setFull_name(rs.getString("full_name"));
                        poster.setDate_of_birth(rs.getDate("date_of_birth"));
                        poster.setAddress(rs.getString("address"));
                        poster.setPhone_number(rs.getString("phone_number"));
                        poster.setEmail(rs.getString("email"));

                        House house = new House();
                        house.setHouse_id(rs.getInt("house_id"));
                        house.setLocation(rs.getString("location"));
                        house.setType_of_house(type_of_house);
                        house.setArea(rs.getInt("area"));
                        house.setDescription(rs.getString("description"));
                        house.setNumber_of_room(rs.getInt("number_of_room"));
                        house.setHouse_owner(poster);

                        Post post = new Post();
                        post.setPost_id(rs.getInt("post_id"));
                        post.setPrice(rs.getInt("price"));
                        post.setHouse(house);
                        post.setHouse_status(house_status);
                        post.setPost_status(post_status);
                        post.setPurpose(purpose);

                        return post;
                    }

                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
