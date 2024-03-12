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
import java.time.LocalDateTime;
import java.util.ArrayList;
import model.feedback;

/**
 *
 * @author luong
 */
public class FeedbackDAO {
    public void insertFeedback(int post_id, int user_id, String comment, LocalDateTime created_at, String img, String name){
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();

            // Prepare the SQL statement
            String sql = "INSERT INTO `feedback` (post_id,user_id,username,created_at,comment,image_link)"
                    + "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement st = con.prepareStatement(sql);
            st = con.prepareStatement(sql);
            st.setInt(1, post_id);
            st.setInt(2, user_id);
            st.setString(3, name);
            st.setString(3, comment);
            st.setObject(4, created_at);
            st.setString(5, img);
            // avatar ?
            int row = st.executeUpdate();
            st.close();
            con.close();
            // Any additional code or processing after inserting the user
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public ArrayList<feedback> getAllFeedbackInAPost(int post_id){
        ArrayList<feedback> list = new ArrayList<>();
        try{
        DBContext db = new DBContext();
            Connection con = db.getConnection();
            if (con != null) {
                String sql = "SELECT * from `feedback` where post_id = '" + post_id + "'";
                Statement call = con.createStatement();
                ResultSet rs = call.executeQuery(sql);
                //assign value for object items then return it
                while (rs.next()) {
                    list.add(new feedback(rs.getInt(1), rs.getInt(2),rs.getInt(3), rs.getString(4) ,rs.getObject(5, LocalDateTime.class), rs.getString(6), rs.getString(7)));
                }
                call.close();
                con.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
    public static boolean updateFeedback(int feedback_id, int user_id, String content, int post_id){
       try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            if(con != null){
                String sql = "Update `feedback` Set content = '"+ content +"'  where feedback_id ='"+ feedback_id +"' and user_id ='" + user_id + "' and post_id='" + post_id + "'";
                 Statement st = con.createStatement();
                int rows = st.executeUpdate(sql);
                if (rows < 1) {
                    throw new Exception();
                }
                return true;
            }
    }catch(Exception e){
            System.out.println(e.getMessage());
    }
        return false;
}
    public static boolean deleteFeedback(int feedback_id, int user_id, int post_id){
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            if(con != null){
                String sql = "Delete from `feedback` where feedback_id ='" + feedback_id + "' and user_id = '" + user_id + "' and post_id = '" + post_id + "'";
                 Statement st = con.createStatement();
                int rows = st.executeUpdate(sql);
                if (rows < 1) {
                    throw new Exception();
                }
                return true;
            }
    }catch(Exception e){
            System.out.println(e.getMessage());
    }
        return false;
    }
}
