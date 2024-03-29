/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import model.House;
import model.Post;
import model.Purpose;
import model.Status;
import model.TypeOfHouse;
import model.User;

/**
 *
 * @author luong
 */
public class SearchDAO {

    public ArrayList<Post> getAllPostByPurPose(int purpose) {
        ArrayList<Post> list = new ArrayList<>();
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            // if connection is secured, proceed to execute query and retrieve data into and return a list
            if (con != null) {
                String sql = "SELECT post.post_id, post.house_id, post.purpose_id, purpose.purpose_name, post.price, post.create_time,"
                        + " house_status.status_name AS 'house_status', \n"
                        + " post_status.status_name AS 'post_status', "
                        + " type_of_house.type_of_house_name, house.location, "
                        + " house.description,\n"
                        + " house.area, house.number_of_room, post.poster_id,"
                        + " user.full_name, user.date_of_birth, user.address, user.phone_number, user.email\n"
                        + "\n"
                        + " FROM post\n"
                        + " JOIN \n"
                        + "     house ON house.house_id = post.house_id\n"
                        + " JOIN \n"
                        + "     user ON post.poster_id = user.user_id\n"
                        + " JOIN \n"
                        + "     purpose ON purpose.purpose_id = post.purpose_id\n"
                        + " JOIN \n"
                        + "     request_status AS house_status ON house_status.status_id = post.house_status\n"
                        + " JOIN \n"
                        + "     request_status AS post_status ON post_status.status_id = post.post_status\n"
                        + " JOIN \n"
                        + "     type_of_house ON type_of_house.type_of_house_id = house.type_of_house_id\n"
                        + " WHERE post.purpose_id = ? AND post.post_status IN (2)";

                PreparedStatement stm = con.prepareStatement(sql);
                stm.setInt(1, purpose);
                ResultSet rs = stm.executeQuery();
                // run a loop to save queries into model
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
                    post.setPurpose(purposer);
                    post.setStart_time(rs.getObject("create_time", LocalDateTime.class));
                    list.add(post);
                }
                stm.close();
                rs.close();
                con.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public ArrayList<Post> getAllPosyByLocation(String address) {
        ArrayList<Post> list = new ArrayList<>();
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            // if connection is secured, proceed to execute query and retrieve data into and return a list
            if (con != null) {
                String sql = "SELECT post.post_id, post.house_id, post.purpose_id, purpose.purpose_name, post.price, post.create_time,"
                        + " house_status.status_name AS 'house_status', \n"
                        + " post_status.status_name AS 'post_status', "
                        + " type_of_house.type_of_house_name, house.location, "
                        + " house.description,\n"
                        + " house.area, house.number_of_room, post.poster_id,"
                        + " user.full_name, user.date_of_birth, user.address, user.phone_number, user.email\n"
                        + "\n"
                        + " FROM post\n"
                        + " JOIN \n"
                        + "     house ON house.house_id = post.house_id\n"
                        + " JOIN \n"
                        + "     user ON post.poster_id = user.user_id\n"
                        + " JOIN \n"
                        + "     purpose ON purpose.purpose_id = post.purpose_id\n"
                        + " JOIN \n"
                        + "     request_status AS house_status ON house_status.status_id = post.house_status\n"
                        + " JOIN \n"
                        + "     request_status AS post_status ON post_status.status_id = post.post_status\n"
                        + " JOIN \n"
                        + "     type_of_house ON type_of_house.type_of_house_id = house.type_of_house_id\n"
                        + " WHERE house.location like ? AND post.post_status IN (2)";

                PreparedStatement stm = con.prepareStatement(sql);
                stm.setString(1, "%" + address + "%");
                ResultSet rs = stm.executeQuery();
                // run a loop to save queries into model
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
                    post.setPurpose(purposer);
                    post.setStart_time(rs.getObject("create_time", LocalDateTime.class));
                    list.add(post);
                }
                stm.close();
                rs.close();
                con.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public ArrayList<Post> getAllPostByUser(String name) {
        ArrayList<Post> list = new ArrayList<>();
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            //if connection is secured, proceed to execute query and retrieve data into and return a list
            if (con != null) {
                String sql = "SELECT post.post_id, post.house_id,post.purpose_id, purpose.purpose_name, post.price, post.create_time,"
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
                        + "    where user.full_name like ? and post.post_status IN (2)\n"
                        + "    ";
                PreparedStatement stm;
                ResultSet rs;
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + name + "%");
                rs = stm.executeQuery();
                //run a loop to save queries into model
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
                    post.setHouse(house);
                    post.setHouse_status(house_status);
                    post.setPost_status(post_status);
                    post.setPurpose(purposer);
                    post.setStart_time(rs.getObject("create_time", LocalDateTime.class));
                    list.add(post);
                }
                stm.close();
                rs.close();
                con.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public ArrayList<Post> getAllPostByNumberOfRoom(int room) {
        ArrayList<Post> list = new ArrayList<>();
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            //if connection is secured, proceed to execute query and retrieve data into and return a list
            if (con != null) {
                String sql = "SELECT post.post_id, post.house_id,post.purpose_id, purpose.purpose_name, post.price, post.create_time,"
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
                        + "    where house.number_of_room = ? and post.post_status IN (2)\n"
                        + "    ";
                PreparedStatement stm;
                ResultSet rs;
                stm = con.prepareStatement(sql);
                stm.setInt(1, room);
                rs = stm.executeQuery();
                //run a loop to save queries into model
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
                    post.setHouse(house);
                    post.setHouse_status(house_status);
                    post.setPost_status(post_status);
                    post.setPurpose(purposer);
                    post.setStart_time(rs.getObject("create_time", LocalDateTime.class));
                    list.add(post);
                }
                stm.close();
                rs.close();
                con.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
    public ArrayList<Post> getAllPostByPrice(int price) {
        ArrayList<Post> list = new ArrayList<>();
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            //if connection is secured, proceed to execute query and retrieve data into and return a list
            if (con != null) {
                String sql = "SELECT post.post_id, post.house_id,post.purpose_id, purpose.purpose_name, post.price, post.create_time,"
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
                        + "    where post.price <= ? and post.post_status IN (2)\n"
                        + "    ";
                PreparedStatement stm;
                ResultSet rs;
                stm = con.prepareStatement(sql);
                stm.setInt(1, price);
                rs = stm.executeQuery();
                //run a loop to save queries into model
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
                    post.setHouse(house);
                    post.setHouse_status(house_status);
                    post.setPost_status(post_status);
                    post.setPurpose(purposer);
                    post.setStart_time(rs.getObject("create_time", LocalDateTime.class));
                    list.add(post);
                }
                stm.close();
                rs.close();
                con.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
        public ArrayList<Post> getAllPostByArea(int area) {
        ArrayList<Post> list = new ArrayList<>();
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            //if connection is secured, proceed to execute query and retrieve data into and return a list
            if (con != null) {
                String sql = "SELECT post.post_id, post.house_id,post.purpose_id, purpose.purpose_name, post.price, post.create_time,"
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
                        + "    where house.area <= ? and post.post_status IN (2)\n"
                        + "    ";
                PreparedStatement stm;
                ResultSet rs;
                stm = con.prepareStatement(sql);
                stm.setInt(1, area);
                rs = stm.executeQuery();
                //run a loop to save queries into model
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
                    post.setHouse(house);
                    post.setHouse_status(house_status);
                    post.setPost_status(post_status);
                    post.setPurpose(purposer);
                    post.setStart_time(rs.getObject("create_time", LocalDateTime.class));
                    list.add(post);
                }
                stm.close();
                rs.close();
                con.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
}
