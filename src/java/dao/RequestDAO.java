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
import model.Request;
import model.Status;
import model.TypeOfHouse;
import model.User;

/**
 *
 * @author trant
 */
public class RequestDAO extends DBContext {

    public List<Request> getAllRequests() {
        List<Request> requests = new ArrayList<>();

        try {
            String sql = "SELECT \n"
                    + "    \n"
                    + "    request.house_id, \n"
                    + "    request.seller_id, \n"
                    + "    request.customer_id, \n"
                    + "    request.purpose_id, \n"
                    + "    request.request_status, \n"
                    + "    request.date_time,\n"
                    + "    post.post_id, \n"
                    + "    post.price, \n"
                    + "    house.house_id , \n"
                    + "	house.address, \n"
                    + "    house.type_of_house_id, \n"
                    + "    house.area, \n"
                    + "    house.description, \n"
                    + "    house.number_of_room,\n"
                    + "    [user].user_id  ,\n"
                    + "    [user].full_name, \n"
                    + "    [user].date_of_birth, \n"
                    + "    [user].address, \n"
                    + "    [user].phone_number, \n"
                    + "    [user].email,\n"
                    + "    purpose.purpose_id, \n"
                    + "    purpose.purpose_name,\n"
                    + "	request_status.status_id,\n"
                    + "    request_status.status_name \n"
                    + "FROM \n"
                    + "    request\n"
                    + "JOIN \n"
                    + "    post ON request.house_id = post.house_id  \n"
                    + "JOIN \n"
                    + "    house ON request.house_id = house.house_id\n"
                    + "JOIN \n"
                    + "    [user] ON request.seller_id = [user].user_id\n"
                    + "JOIN \n"
                    + "    purpose ON request.purpose_id = purpose.purpose_id\n"
                    + "JOIN \n"
                    + "    request_status ON request.request_status = request_status.status_id;";

            try ( Connection con = getConnection();  PreparedStatement stm = con.prepareStatement(sql);  ResultSet rs = stm.executeQuery()) {

                while (rs.next()) {
                    TypeOfHouse type_of_house = new TypeOfHouse();
                    type_of_house.setType_of_house_id(rs.getInt("type_of_house_id"));

                    Status request_status = new Status();
                    request_status.setStatus_id(rs.getInt("status_id"));
                    request_status.setStatus_name(rs.getString("status_name"));

                    Purpose purpose = new Purpose();
                    purpose.setPurpose_id(rs.getInt("purpose_id"));
                    purpose.setPurpose_name(rs.getString("purpose_name"));

                    User seller = new User();
                    seller.setUser_id(rs.getInt("seller_id"));
                    //seller.setUser_name(rs.getString("user_name"));  // assuming you have a getter for user_name
                    seller.setFull_name(rs.getString("full_name"));
                    seller.setDate_of_birth(rs.getDate("date_of_birth"));
                    seller.setAddress(rs.getString("address"));
                    seller.setPhone_number(rs.getString("phone_number"));
                    seller.setEmail(rs.getString("email"));

                    User customer = new User();
                    customer.setUser_id(rs.getInt("customer_id"));
                    // customer.setUser_name(rs.getString("user_name"));  // assuming you have a getter for user_name
                    customer.setFull_name(rs.getString("full_name"));
                    customer.setDate_of_birth(rs.getDate("date_of_birth"));
                    customer.setAddress(rs.getString("address"));
                    customer.setPhone_number(rs.getString("phone_number"));
                    customer.setEmail(rs.getString("email"));

                    House house = new House();
                    house.setHouse_id(rs.getInt("house_id"));
                    house.setLocation(rs.getString("address"));
                    house.setType_of_house(type_of_house);
                    house.setArea(rs.getInt("area"));
                    house.setDescription(rs.getString("description"));
                    house.setNumber_of_room(rs.getInt("number_of_room"));
                    house.setHouse_owner(seller);

                    Post post = new Post();
                    post.setPost_id(rs.getInt("post_id"));
                    post.setPrice(rs.getInt("price"));
                    post.setHouse(house);

                    Request request = new Request();
                    request.setHouse_id(house);
                    request.setSeller_id(seller);
                    request.setCustomer_id(customer);
                    request.setPurpose_id(purpose);
                    request.setRequest_status(request_status);
                    request.setDate_time(rs.getTimestamp("date_time"));

                    requests.add(request);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(RequestDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return requests;
    }

    public static void main(String[] args) {
        // Create an instance of RequestDAO
        RequestDAO requestDAO = new RequestDAO();

        try {
            // Call the getAllRequests function
            List<Request> requests = requestDAO.getAllRequests();

            // Display the results
            for (Request request : requests) {
                System.out.println(request);
            }
        } catch (Exception ex) {
            // Handle other exceptions
            Logger.getLogger(RequestDAO.class.getName()).log(Level.SEVERE, "An unexpected error occurred", ex);
        }
        // Handle SQL exceptions

    }

}
