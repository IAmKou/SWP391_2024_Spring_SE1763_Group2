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
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
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

    public void changePostStatusByAdmin(int post_id, int status, int admin_id, String message) {
        try {
            String sql = "UPDATE `post`\n"
                    + "SET\n"
                    + "\n"
                    + "`admin_id` = ?,\n"
                    + "`post_status` =?\n"
//                    + "`admin_message` =?\n"
                    + "WHERE `post_id` = ?;";
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, admin_id);
            stm.setInt(2, status);
            stm.setInt(3, post_id);
//            stm.setString(3, message);
            stm.executeUpdate();

            con.close();
        } catch (Exception e) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void DeactivePost(boolean active, int post_id) {
        try {
            String sql = "UPDATE `house_finder_project`.`post`\n"
                    + "SET\n"
                    + "`active` = ?\n"
                    + "WHERE `post_id` = ?;";
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setBoolean(1, active);
            stm.setInt(2, post_id);
            stm.executeUpdate();

            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public int getOwnerId(int post_id) {
        try {
            String sql = "SELECT poster_id from post where post_id =?;";
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, post_id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                int ownerPostId = rs.getInt("poster_id");
                return ownerPostId;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public Post getPostByHouseId(int house_id) {
        try {
            String sql = "SELECT \n"
                    + "    post.post_id, \n"
                    + "    post.house_id,\n"
                    + "    purpose.purpose_id, \n"
                    + "    purpose.purpose_name, \n"
                    + "    post.price,\n"
                    + "    house_status.status_name AS house_status, \n"
                    + "    post_status.status_name AS post_status, \n"
                    + "    type_of_house.type_of_house_name,\n"
                    + "    type_of_house.type_of_house_id, \n"
                    + "    house.location, \n"
                    + "    house.description,\n"
                    + "    house.area,\n"
                    + "    house.number_of_room,\n"
                    + "    post.poster_id,\n"
                    + "    user.full_name,\n"
                    + "    user.phone_number,\n"
                    + "    user.email\n"
                    + "FROM \n"
                    + "    post\n"
                    + "JOIN \n"
                    + "    house ON house.house_id = post.house_id\n"
                    + "JOIN \n"
                    + "    user ON post.poster_id = user.user_id\n"
                    + "JOIN \n"
                    + "    purpose ON purpose.purpose_id = post.purpose_id\n"
                    + "JOIN \n"
                    + "    request_status AS house_status ON house_status.status_id = post.house_status\n"
                    + "JOIN \n"
                    + "    request_status AS post_status ON post_status.status_id = post.post_status\n"
                    + "JOIN \n"
                    + "    type_of_house ON type_of_house.type_of_house_id = house.type_of_house_id\n"
                    + "    where post.house_id = ?\n"
                    + "    ";
            DBContext db = new DBContext();
            try ( Connection con = db.getConnection();  PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setInt(1, house_id);
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

                    con.close();

                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void deletePostByHouseID(int house_id) {
        try {
            String sql = "DELETE FROM `house_finder_project`.`post`\n"
                    + "WHERE post.house_id = ?;";
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, house_id);
            stm.executeUpdate();

            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Post> getUserPost(int user_id) {
        List<Post> posts = new ArrayList<>();
        try {
            String sql = "SELECT post.post_id, post.house_id,post.purpose_id, purpose.purpose_name, post.price, post.create_time,post.house_status,post.active_feedback,"
                    + " house_status.status_name as 'house_status_name', \n"
                    + "post_status.status_name as'post_status', "
                    + "type_of_house.type_of_house_name, house.location, "
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
                    + "    where poster_id = ? and post.post_status = 2\n"
                    + "    ";
            DBContext db = new DBContext();
            PreparedStatement stm;
            ResultSet rs;
            try ( Connection con = db.getConnection()) {
                stm = con.prepareStatement(sql);
                stm.setInt(1, user_id);
                rs = stm.executeQuery();
                while (rs.next()) {
                    TypeOfHouse type_of_house = new TypeOfHouse();
                    type_of_house.setType_of_house_name(rs.getString("type_of_house_name"));

                    Status house_status = new Status();
                    house_status.setStatus_id(rs.getInt("house_status"));
                    house_status.setStatus_name(rs.getString("house_status_name"));

                    Status post_status = new Status();
                    post_status.setStatus_name(rs.getString("post_status"));

                    Purpose purpose = new Purpose();
                    purpose.setPurpose_id(rs.getInt("purpose_id"));
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
                    post.setActive_feedback(rs.getBoolean("active_feedback"));
//                    post.setStart_time(rs.getObject("start_time", LocalDateTime.class));

                    Timestamp timeStamp = rs.getTimestamp("create_time");
                    LocalDateTime createDateTime = timeStamp.toLocalDateTime();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                    String formattedDateTime = createDateTime.format(formatter);
                    post.setCreate_time(createDateTime);
                    post.setFommated_create_time(formattedDateTime);
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
            String sql = "SELECT \n"
                    + "    post.post_id, \n"
                    + "    post.house_id, \n"
                    + "    purpose.purpose_name, \n"
                    + "    post.price, \n"
                    + "    post.admin_message, \n"
                    + "    post.post_status AS 'post_status_id', \n"
                    + "    post_status.status_name AS 'post_status_name',\n"
                    + "    house_status.status_name AS 'house_status', \n"
                    + "    type_of_house.type_of_house_name, \n"
                    + "    house.location, \n"
                    + "    house.description, \n"
                    + "    house.area,\n"
                    + "    house.number_of_room,\n"
                    + "    post.poster_id,\n"
                    + "    user.full_name, \n"
                    + "    user.date_of_birth, \n"
                    + "    user.address,\n"
                    + "    user.phone_number,\n"
                    + "    user.email\n"
                    + "FROM \n"
                    + "    post\n"
                    + "JOIN \n"
                    + "    house ON house.house_id = post.house_id\n"
                    + "JOIN \n"
                    + "    user ON post.poster_id = user.user_id\n"
                    + "JOIN \n"
                    + "    purpose ON purpose.purpose_id = post.purpose_id\n"
                    + "JOIN \n"
                    + "    request_status AS house_status ON house_status.status_id = post.house_status\n"
                    + "JOIN \n"
                    + "    request_status AS post_status ON post_status.status_id = post.post_status\n"
                    + "JOIN \n"
                    + "    type_of_house ON type_of_house.type_of_house_id = house.type_of_house_id"
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
                    post_status.setStatus_id(rs.getInt("post_status_id"));
                    post_status.setStatus_name(rs.getString("post_status_name"));

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
                    post.setAdmin_message(rs.getString("admin_message"));

                    posts.add(post);
                }
                con.close();

            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return posts;
    }

    public void addPost(int houseId, Post post) {
        try {
            String postSql = "INSERT INTO post (house_id, purpose_id,"
                    + " price, poster_id, house_status, post_status, end_time, create_time)"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            DBContext db = new DBContext();
            try ( Connection con = db.getConnection();  PreparedStatement stm = con.prepareStatement(postSql)) {

                stm.setInt(1, houseId);
                stm.setInt(2, post.getPurpose().getPurpose_id());
                stm.setInt(3, post.getPrice());
                stm.setInt(4, post.getPoster_id());
                stm.setInt(5, post.getHouse_status().getStatus_id());
                stm.setInt(6, post.getPost_status().getStatus_id());
                stm.setObject(7, post.getEnd_time());

                LocalDateTime currentDateTime = LocalDateTime.now();
                stm.setObject(8, currentDateTime);

                stm.executeUpdate();

                con.close();
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
            String sql = "SELECT \n"
                    + "    post.post_id, \n"
                    + "    post.house_id,\n"
                    + "    purpose.purpose_id, \n"
                    + "    purpose.purpose_name, \n"
                    + "    post.price,\n"
                    + "    post.active_feedback,\n"
                    + "    house_status.status_name AS house_status, \n"
                    + "    post_status.status_name AS post_status_name, \n"
                    + "    post_status.status_id AS post_status_id, \n"
                    + "    type_of_house.type_of_house_name,\n"
                    + "    type_of_house.type_of_house_id, \n"
                    + "    house.location, \n"
                    + "    house.description,\n"
                    + "    house.area,\n"
                    + "    house.number_of_room,\n"
                    + "    post.poster_id,\n"
                    + "    user.full_name,\n"
                    + "    user.phone_number,\n"
                    + "    user.email,\n"
                    + "    user.date_of_birth,\n"
                    + "    user.avatar\n"
                    + "FROM \n"
                    + "    post\n"
                    + "JOIN \n"
                    + "    house ON house.house_id = post.house_id\n"
                    + "JOIN \n"
                    + "    user ON post.poster_id = user.user_id\n"
                    + "JOIN \n"
                    + "    purpose ON purpose.purpose_id = post.purpose_id\n"
                    + "JOIN \n"
                    + "    request_status AS house_status ON house_status.status_id = post.house_status\n"
                    + "JOIN \n"
                    + "    request_status AS post_status ON post_status.status_id = post.post_status\n"
                    + "JOIN \n"
                    + "    type_of_house ON type_of_house.type_of_house_id = house.type_of_house_id\n"
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
                        post_status.setStatus_id(rs.getInt("post_status_id"));
                        post_status.setStatus_name(rs.getString("post_status_name"));

                        Purpose purpose = new Purpose();
                        purpose.setPurpose_id(rs.getInt("purpose_id"));
                        purpose.setPurpose_name(rs.getString("purpose_name"));

                        User poster = new User();
                        poster.setUser_id(rs.getInt("poster_id"));
                        poster.setFull_name(rs.getString("full_name"));
                        poster.setPhone_number(rs.getString("phone_number"));
                        poster.setEmail(rs.getString("email"));
                        poster.setDate_of_birth(rs.getDate("date_of_birth"));
                        poster.setAvatar(rs.getString("avatar"));

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
                        post.setActive_feedback(rs.getBoolean("active_feedback"));
                        return post;
                    }

                    con.close();

                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Post> getAllPost() {
        ArrayList<Post> list = new ArrayList<>();
        Connection con = new DBContext().getConnection();

        try {
            String sql = "SELECT post.post_id, post.house_id,post.purpose_id, purpose.purpose_name, post.price, post.create_time,post.end_time,"
                        + " house_status.status_name as 'house_status', \n"
                        + "post_status.status_name as'post_status', "
                        + "type_of_house.type_of_house_name, house.location, "
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
                        + "    ";

            try ( PreparedStatement stm = con.prepareStatement(sql);  ResultSet rs = stm.executeQuery();) {
                while (rs.next()) {
                    TypeOfHouse type_of_house = new TypeOfHouse();
                    type_of_house.setType_of_house_name(rs.getString("type_of_house_name"));

                    Status house_status = new Status();
                    house_status.setStatus_name(rs.getString("house_status"));

                    Status post_status = new Status();
                    post_status.setStatus_name(rs.getString("post_status"));

                    Purpose purposer = new Purpose();
                    purposer.setPurpose_id(rs.getInt("purpose_id"));
                    purposer.setPurpose_name(rs.getString("purpose_name"));

                    User u = new User();
                    u.setUser_id(rs.getInt("poster_id")); // Retrieve and set the poster_id
                    u.setFull_name(rs.getString("full_name"));
                    u.setAddress(rs.getString("address"));
                    u.setPhone_number(rs.getString("phone_number"));
                    u.setEmail(rs.getString("email"));

                    House house = new House();
                    house.setHouse_owner(u);
                    house.setHouse_id(rs.getInt("house_id"));
                    house.setLocation(rs.getString("location"));
                    house.setType_of_house(type_of_house);
                    house.setArea(rs.getInt("area"));
                    house.setDescription(rs.getString("description"));
                    house.setNumber_of_room(rs.getInt("number_of_room"));

                    Post post = new Post();
                    post.setPost_id(rs.getInt("post_id"));
                    post.setPrice(rs.getInt("price"));
                    post.setPoster_id(rs.getInt("poster_id"));
                    post.setHouse(house);
                    post.setHouse_status(house_status);
                    post.setPost_status(post_status);
                    post.setPurpose(purposer);
                    post.setStart_time(rs.getObject("create_time", LocalDateTime.class));
                    post.setEnd_time(rs.getObject("end_time", LocalDateTime.class));

                    list.add(post);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Post> allPost() {
        List<Post> posts = new ArrayList<>();

        try {
            String sql = "SELECT \n"
                    + "    post.post_id, \n"
                    + "    post.house_id, \n"
                    + "    purpose.purpose_name, \n"
                    + "    post.price, \n"
                    + "    post.post_status AS 'post_status_id', \n"
                    + "    post_status.status_name AS 'post_status_name',\n"
                    + "    house_status.status_name AS 'house_status', \n"
                    + "    type_of_house.type_of_house_name, \n"
                    + "    house.location, \n"
                    + "    house.description, \n"
                    + "    house.area,\n"
                    + "    house.number_of_room,\n"
                    + "    post.poster_id,\n"
                    + "    user.full_name, \n"
                    + "    user.date_of_birth, \n"
                    + "    user.address,\n"
                    + "    user.phone_number,\n"
                    + "    user.email,\n"
                    + "    user.avatar,\n"
                    + "    post.create_time\n"
                    + "FROM \n"
                    + "    post\n"
                    + "JOIN \n"
                    + "    house ON house.house_id = post.house_id\n"
                    + "JOIN \n"
                    + "    user ON post.poster_id = user.user_id\n"
                    + "JOIN \n"
                    + "    purpose ON purpose.purpose_id = post.purpose_id\n"
                    + "JOIN \n"
                    + "    request_status AS house_status ON house_status.status_id = post.house_status\n"
                    + "JOIN \n"
                    + "    request_status AS post_status ON post_status.status_id = post.post_status\n"
                    + "JOIN \n"
                    + "    type_of_house ON type_of_house.type_of_house_id = house.type_of_house_id"
                    + "    ";
            DBContext db = new DBContext();
            try ( Connection con = db.getConnection();  PreparedStatement stm = con.prepareStatement(sql)) {
                ResultSet rs = stm.executeQuery();
                while (rs.next()) {
                    TypeOfHouse type_of_house = new TypeOfHouse();
                    type_of_house.setType_of_house_name(rs.getString("type_of_house_name"));

                    Status house_status = new Status();
                    house_status.setStatus_name(rs.getString("house_status"));

                    Status post_status = new Status();
                    post_status.setStatus_id(rs.getInt("post_status_id"));
                    post_status.setStatus_name(rs.getString("post_status_name"));

                    Purpose purpose = new Purpose();
                    purpose.setPurpose_name(rs.getString("purpose_name"));

                    User poster = new User();
                    poster.setUser_id(rs.getInt("poster_id"));
                    poster.setFull_name(rs.getString("full_name"));
                    poster.setDate_of_birth(rs.getDate("date_of_birth"));
                    poster.setAddress(rs.getString("address"));
                    poster.setPhone_number(rs.getString("phone_number"));
                    poster.setEmail(rs.getString("email"));
                    poster.setAvatar(rs.getString("avatar"));

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

                    Timestamp timeStamp = rs.getTimestamp("create_time");
                    LocalDateTime createDateTime = timeStamp.toLocalDateTime();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                    String formattedDateTime = createDateTime.format(formatter);
                    post.setCreate_time(createDateTime);
                    post.setFommated_create_time(formattedDateTime);

                    posts.add(post);

                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return posts;
    }

    // tra ve 1 cap post va house de hien len 1 card cua list.
    // 1 = rent; 2= sell; 0 = any
    public HashMap<Post, House> getPostCard(int purpose) throws IOException {
        HashMap<Post, House> postMap = new HashMap<>();
        PostDAO pDao = new PostDAO();
        HouseDAO hDao = new HouseDAO();

        for (Post p : allPost()) {
            if (p.getPurpose().getPurpose_id() == purpose || purpose == 0) {
                postMap.put(p, hDao.getHouseByPostID(p.getPost_id()));
            }

        }
        return postMap;
    }

    public HashMap<Post, House> houseFilter() {
        return null;
    }

    public boolean changePostStatus(int post_id, int status) {
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            if (con != null) {
                String sql = "UPDATE `post` SET post_status = '" + status + "' where post_id = '" + post_id + "'";
                Statement st = con.createStatement();
                int rows = st.executeUpdate(sql);
                if (rows < 1) {
                    throw new Exception();
                }
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        PostDAO pDao = new PostDAO();
//        System.out.println(pDao.getPostCard(0));
    }
}
