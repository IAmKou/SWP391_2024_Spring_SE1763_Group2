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
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Report;
import model.User;
import model.feedback;

/**
 *
 * @author luong
 */
public class FeedbackDAO {

    public void deactivateFeedback(int post_id, boolean status) {
        try {
            String sql = "UPDATE `house_finder_project`.`post`\n"
                    + "SET\n"
                    + "`active_feedback` = ?\n"
                    + "WHERE `post_id` = ? ;";
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setBoolean(1, status);
            stm.setInt(2, post_id);
            stm.executeUpdate();

            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(FeedbackDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void insertFeedback(int post_id, int user_id, String name, LocalDateTime created_at, String comment, String img) {
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();

            // Prepare the SQL statement
            String sql = "INSERT INTO `feedback` (post_id,user_id,username,created_at,content,image_link)"
                    + "VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement st = con.prepareStatement(sql);
            st = con.prepareStatement(sql);
            st.setInt(1, post_id);
            st.setInt(2, user_id);
            st.setString(3, name);
            st.setObject(4, created_at);
            st.setString(5, comment);
            st.setString(6, img);
            // avatar ?
            int row = st.executeUpdate();
            st.close();
            con.close();
            // Any additional code or processing after inserting the user
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<feedback> getAllFeedbackInAPost(int post_id) {
        ArrayList<feedback> list = new ArrayList<>();
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            if (con != null) {
                String sql = "SELECT * from `feedback` where post_id = '" + post_id + "'";
                Statement call = con.createStatement();
                ResultSet rs = call.executeQuery(sql);
                //assign value for object items then return it
                while (rs.next()) {
                    list.add(new feedback(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getObject(5, LocalDateTime.class), rs.getString(6), rs.getString(7)));
                }
                call.close();
                con.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public static boolean updateFeedback(int feedback_id, int user_id, String content, int post_id) {
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            if (con != null) {
                String sql = "Update `feedback` Set content = '" + content + "'  where feedback_id ='" + feedback_id + "' and user_id ='" + user_id + "' and post_id='" + post_id + "'";
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

    public static boolean deleteFeedback(int feedback_id) {
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            if (con != null) {
                String sql = "Delete from `feedback` where feedback_id ='" + feedback_id + "'";
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
    
    public static boolean deleteReport(int report_id){
        try{
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            if (con != null) {
                String sql = "Delete from `report` where report_id ='" + report_id + "'";
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
    

    public void insertReport(int fid, int pid, int uid, LocalDateTime report_time, String fcontent) {
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();

            String sql = "Insert into `report` (fid,uid,pid,report_time,fcontent)"
                    + "values (?,?,?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);
            st = con.prepareStatement(sql);
            st.setInt(1, fid);
            st.setInt(2, uid);
            st.setInt(3, pid);
            st.setObject(4, report_time);
            st.setString(5, fcontent);
            int row = st.executeUpdate();
            st.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Report> getAllReport() {
        ArrayList<Report> list = new ArrayList<>();
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            if (con != null) {
                String sql = "SELECT report.*, user.user_id, user.full_name, feedback.feedback_id  "
                        + "FROM `report` "
                        + "JOIN `user` ON report.uid = user.user_id "
                        + "JOIN `feedback` ON report.fid = feedback.feedback_id";
                PreparedStatement stm;
                ResultSet rs;
                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                //assign value for object items then return it
                while (rs.next()) {
                    User u = new User();
                    u.setUser_id(rs.getInt("user_id"));
                    u.setFull_name(rs.getString("full_name"));

                    feedback fb = new feedback();
                    fb.setUser_id(rs.getInt("user_id"));
                    fb.setFeedback_id(rs.getInt("feedback_id"));

                    Report r = new Report();
                    r.setReport_id(rs.getInt("report_id"));
                    r.setFb(fb);
                    r.setUser(u);
                    r.setReport_time(rs.getObject("report_time", LocalDateTime.class));
                    r.setPid(rs.getInt("pid"));
                    r.setFcontent(rs.getString("fcontent"));
                    list.add(r);
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

    public boolean alreadyReported(int fid) {
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            String query = "select count(*) as num from `report` where `fid` = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, fid);
            ResultSet rslt = ps.executeQuery();
            if (rslt.next()) {
                return Integer.parseInt(rslt.getString(1)) > 0;
            }
        } catch (SQLException e) {
        }
        return false;
    }

    public List<Report> getReportDetail(int rid) {
        List<Report> reports = new ArrayList<>();
        try {
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            if (con != null) {
                String sql = "SELECT report.report_id, report.report_time, report.fcontent, report.fid, report.uid, report.pid, user.full_name, feedback.user_id, feedback.feedback_id "
                        + "FROM report "
                        + "JOIN user ON user.user_id = report.uid "
                        + "JOIN feedback ON feedback.feedback_id = report.fid "
                        + "WHERE report.report_id = ?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, rid);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Report r = new Report();
                    r.setReport_id(rs.getInt("report_id"));
                    r.setFcontent(rs.getString("fcontent"));
                    r.setPid(rs.getInt("pid"));
                    r.setReport_time(rs.getTimestamp("report_time").toLocalDateTime());

                    User u = new User();
                    u.setFull_name(rs.getString("full_name"));
                    r.setUser(u);

                    feedback fb = new feedback();
                    fb.setUser_id(rs.getInt("user_id"));
                    fb.setFeedback_id(rs.getInt("feedback_id"));
                    r.setFb(fb);

                    reports.add(r);
                }
                ps.close();
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace(); // Better error handling
        }
        return reports;
    }
}
