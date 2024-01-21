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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.House;
import model.TypeOfHouse;
import model.Order;
import model.User;

/**
 *
 * @author FPTSHOP
 */
public class OrderDAO extends DBContext {

    public ArrayList<Order> getOrderList(int userId) {
        ArrayList<Order> orderList = new ArrayList<>();
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            String sql = "select user.full_name,user.location,user.phone,user.email,house.description,house.picture,house.price_per_unit,type_of_house.description,onder_booking.time\n"
                    + " from order_booking\n"
                    + " join user on user.user_id = order_booking.user_id\n"
                    + " join house on order_booking.house_id = house.house_id\n"
                    + " join type_of_house on house.type_of_house_id = type_of_house.type_of_house_id\n"
                    + "where order_booking.user_id = ?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, userId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                House house = new House();
                User user = new User();
                TypeOfHouse tOfHouse = new TypeOfHouse();

                user.setFullName(rs.getString("full_name"));
                user.setLocation(rs.getString("location"));
                user.setPhone(rs.getInt("phone"));
                user.setEmail(rs.getString("email"));

                house.setDescription(rs.getString(5));
                house.setPicture(rs.getString("picture"));
                house.setPrice(rs.getInt("price_per_unit"));

                tOfHouse.setDescription(rs.getString(8));

                order.setHouse(house);
                order.setUser(user);
                order.setTime(rs.getTime("time"));

                orderList.add(order);
            }

        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orderList;
    }

    public Order getOrder(int userId, int houseId) {
        Order order = new Order();
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            String sql = "select user.full_name,user.location,user.phone,user.email,house.description,house.picture,house.price_per_unit,type_of_house.description,onder_booking.time\n"
                    + " from order_booking\n"
                    + " join user on user.user_id = order_booking.user_id\n"
                    + " join house on order_booking.house_id = house.house_id\n"
                    + " join type_of_house on house.type_of_house_id = type_of_house.type_of_house_id\n"
                    + "where order_booking.user_id = ? and order_booking.house_id = ?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, userId);
            stm.setInt(2, houseId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                House house = new House();
                User user = new User();
                TypeOfHouse tOfHouse = new TypeOfHouse();

                user.setFullName(rs.getString("full_name"));
                user.setLocation(rs.getString("location"));
                user.setPhone(rs.getInt("phone"));
                user.setEmail(rs.getString("email"));

                house.setDescription(rs.getString(5));
                house.setPicture(rs.getString("picture"));
                house.setPrice(rs.getInt("price_per_unit"));

                tOfHouse.setDescription(rs.getString(8));

                order.setHouse(house);
                order.setUser(user);
                order.setTime(rs.getTime("time"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return order;
    }
}
