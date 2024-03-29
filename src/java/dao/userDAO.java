/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Context.DBContext;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.User;

/**
 *
 * @author ACER
 */
public class UserDAO {
    
    public void insertUser(String fullname, Date dob, String address, String phone, String email) {
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();

            // Prepare the SQL statement
            String sql = "INSERT INTO `user` (full_name,date_of_birth,address,phone_number,email)"
                    + "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement st = con.prepareStatement(sql);
            st = con.prepareStatement(sql);
            st.setString(1, fullname);
            st.setDate(2, dob);
            st.setString(3, address);
            st.setString(4, phone);
            st.setString(5, email);
            // avatar ?
            int row = st.executeUpdate();
            st.close();
            con.close();
            // Any additional code or processing after inserting the user
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean phoneIsExist(String phone) {
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            String query = "select count(*) as num from `user` where `phone_number` like ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, phone);
            ResultSet rslt = ps.executeQuery();
            if (rslt.next()) {
                return Integer.parseInt(rslt.getString(1)) > 0;
            }
        } catch (SQLException e) {
        }
        return false;
    }

    public boolean emailIsExist(String email) {
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            String query = "select count(*) as num from `user` where `email` = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, email);
            ResultSet rslt = ps.executeQuery();
            if (rslt.next()) {
                return Integer.parseInt(rslt.getString(1)) > 0;
            }
        } catch (SQLException e) {
        }
        return false;
    }

    public static Account LogIn(String username, String pass) {
        Account account = null;
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            if (con != null) {
                String sql = "Select * from `account` where `user_name` = '" + username + "' AND `pass_word` = '" + pass + "'";
                Statement call = con.createStatement();
                ResultSet rs = call.executeQuery(sql);
                while (rs.next()) {
                    account = new Account(rs.getInt("user_id"), username, pass, rs.getInt("role_id"),rs.getBoolean("active"));
                }
                call.close();
                con.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return account;
    }

    public static boolean ChangePassword(int user_id, String password) {
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            if (con != null) {
                String sql = "UPDATE `account` SET `pass_word` = '" + password + "' where `user_id` = '" + user_id + "'";
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

    public static User getUserInformation(int id) {
        User user = null;
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            if (con != null) {
                String sql = "Select * from `user` where `user_id`=" + id;
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    user = new User();
                    user.setUser_id(rs.getInt(1));
                    user.setFull_name(rs.getString(2));
                    user.setDate_of_birth(rs.getDate(3));
                    user.setAddress(rs.getString(4));
                    user.setPhone_number(rs.getString(5));
                    user.setEmail(rs.getString(6));
                    user.setAvatar(rs.getString(7));
                }
                rs.close();
                st.close();
                con.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return user;
    }

    public static User getUserByEmail(String email) {
        User user = new User();
        String sql = "Select * from `user` where `email` = '" + email + "';";
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            PreparedStatement ps = con.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user.setUser_id(rs.getInt("user_id"));
                user.setFull_name(rs.getString("full_name"));
                user.setDate_of_birth(rs.getDate("date_of_birth"));
                user.setAddress(rs.getString("address"));
                user.setPhone_number(rs.getString("phone_number"));
                user.setEmail(rs.getString("email"));

                return user;

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void insertAccount(int uid, String uname, String pass, int rid, boolean active) {
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();

            // Prepare the SQL statement
            String sql = "INSERT INTO `account` (user_id,user_name,pass_word,role_id,active)"
                    + "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement st = con.prepareStatement(sql);
            st = con.prepareStatement(sql);
            st.setInt(1, uid);
            st.setString(2, uname);
            st.setString(3, pass);
            st.setInt(4, rid);
            st.setBoolean(5, active);
            int row = st.executeUpdate();
            st.close();
            con.close();
            // Any additional code or processing after inserting the user
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static Account getAccount(int id) {
        Account acc = new Account();
        String sql = "Select * from `account` where `user_id` = '" + id + "';";
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            PreparedStatement ps = con.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                acc.setUser_id(rs.getInt("user_id"));
                acc.setUser_name(rs.getString("user_name"));
                acc.setPass_word(rs.getString("pass_word"));
                acc.setRole_id(rs.getInt("role_id"));
                acc.setActive(rs.getBoolean("active"));
                return acc;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void updateUser(User user) {
        try {
            String sql = "UPDATE user\n"
                    + "SET\n"
                    + "    full_name = ?,\n"
                    + "    date_of_birth = ?,\n"
                    + "    address = ?,\n"
                    + "    phone_number = ?,\n"
                    + "    email = ?,\n"
                    + "    avatar = ?\n"
                    + "WHERE user_id = ?;";

            DBContext db = new DBContext();
            try ( Connection con = db.getConnection();  PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setString(1, user.getFull_name());
                stm.setDate(2, user.getDate_of_birth());
                stm.setString(3, user.getAddress());
                stm.setString(4, user.getPhone_number());
                stm.setString(5, user.getEmail());
                stm.setString(6, user.getAvatar());
                stm.setInt(7, user.getUser_id());
                stm.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<User> allUserList() {
        List<User> list = new ArrayList<>();
        Connection con = new DBContext().getConnection();
        try {
            String sql = "SELECT * FROM user";

            try ( PreparedStatement stm = con.prepareStatement(sql);  ResultSet resultSet = stm.executeQuery();) {
                while (resultSet.next()) {
                    User u = new User();
                    u.setAddress(resultSet.getString("address"));
                    u.setUser_id(resultSet.getInt("user_id"));
                    u.setFull_name(resultSet.getString("full_name"));
                    u.setPhone_number(resultSet.getString("phone_number"));
                    u.setEmail(resultSet.getString("email"));
                    u.setDate_of_birth(resultSet.getDate("date_of_birth"));
                    list.add(u);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public User getUserByID(int uID) {
        Connection con = new DBContext().getConnection();
        User u = null;
        try {
            String sql = "SELECT * FROM account "
                    + "JOIN user ON user.user_id = account.user_id "
                    + "WHERE user.user_id = ? "
                    + "LIMIT 1"; // Giới hạn kết quả trả về chỉ là 1 dòng
            try ( PreparedStatement stm = con.prepareStatement(sql)) {
                stm.setInt(1, uID);
                ResultSet resultSet = stm.executeQuery();
                if (resultSet.next()) {
                    u = new User(); // Khởi tạo đối tượng User
                    u.setAddress(resultSet.getString("address"));
                    u.setUser_id(resultSet.getInt("user_id"));
                    u.setFull_name(resultSet.getString("full_name"));
                    u.setPhone_number(resultSet.getString("phone_number"));
                    u.setEmail(resultSet.getString("email"));
                    u.setDate_of_birth(resultSet.getDate("date_of_birth"));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
    }

    public int getRoleForUID(int uID) {
        Connection con = new DBContext().getConnection();
        int role = 0;
        try {

            String sql = "select account.role_id from account\n"
                    + "join house_finder.user on user.user_id = account.user_id\n"
                    + "where user.user_id = ?";

            try (
                     PreparedStatement stm = con.prepareStatement(sql);) {
                stm.setInt(1, uID);

                ResultSet resultSet = stm.executeQuery();
                while (resultSet.next()) {
                    role = resultSet.getInt("role_id");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return role;
    }
        public static boolean uploadAvatar(int user_id, String imgurl) {
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            if (con != null) {
                String sql = "UPDATE `user` SET `avatar` = '" + imgurl + "' where `user_id` = '" + user_id + "'";
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
}
