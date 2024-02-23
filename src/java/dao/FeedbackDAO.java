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
import model.Feedback;
import model.Post;
import model.User;

/**
 *
 * @author trant
 */
public class FeedbackDAO extends DBContext{

    public void addFeedback(int postId, int userId,  int number_of_star ,String comment) {
        try {
            String feedbackSql = "INSERT INTO [dbo].[feedback] ([post_id], [user_id], [number_of_star], [comment]) VALUES (?, ?, ?, ?)";
            DBContext db = new DBContext();
            try (Connection con = db.getConnection(); PreparedStatement stm = con.prepareStatement(feedbackSql)) {

                stm.setInt(1, postId);
                stm.setInt(2, userId);
                stm.setString(4, comment);
                stm.setInt(3, number_of_star);

                stm.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // ... other methods ...

  

  
    public List<Feedback> getAllFeedbackByUserId(int userId) {
        List<Feedback> feedbackList = new ArrayList<>();

        try {
            String selectQuery = "SELECT * FROM [dbo].[feedback] WHERE [user_id] = ?";
            DBContext db = new DBContext();

            try (Connection con = db.getConnection(); PreparedStatement stm = con.prepareStatement(selectQuery)) {
                stm.setInt(1, userId);

                try (ResultSet rs = stm.executeQuery()) {
                    while (rs.next()) {
                        Feedback feedback = new Feedback();
                        feedback.setPost_id(new Post(rs.getInt("post_id")));
                        feedback.setUser_id(new User(rs.getInt("user_id")));
                        feedback.setNumber_of_star(rs.getInt("number_of_star"));
                        feedback.setComment(rs.getString("comment"));

                        feedbackList.add(feedback);
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(FeedbackDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return feedbackList;
    }
    
    public static void main(String[] args) {
    FeedbackDAO feedbackDAO = new FeedbackDAO();

    // Assuming you have a user ID available
    int userId = 2;

    List<Feedback> feedbackList = feedbackDAO.getAllFeedbackByUserId(userId);

    if (feedbackList.isEmpty()) {
        System.out.println("No feedback found for user with ID: " + userId);
    } else {
        System.out.println("Feedback for user with ID " + userId + ":");
        for (Feedback feedback : feedbackList) {
            System.out.println("Post ID: " + feedback.getPost_id().getPost_id());
            System.out.println("User ID: " + feedback.getUser_id().getUser_id());
            System.out.println("Number of Stars: " + feedback.getNumber_of_star());
            System.out.println("Comment: " + feedback.getComment());
            System.out.println("-----------");
        }
    }
}

}
