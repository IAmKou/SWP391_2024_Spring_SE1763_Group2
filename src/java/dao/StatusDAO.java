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
import model.Status;

/**
 *
 * @author FPTSHOP
 */
public class StatusDAO extends DBContext {

    public List<Status> getStatus() {
        List<Status> statuses = new ArrayList<>();
        
        try {
            String sql = "SELECT * FROM house_finder_project.request_status\n"
                    + "WHERE status_id NOT IN (4, 5);";
            DBContext db = new DBContext();
            try (Connection con = db.getConnection(); PreparedStatement stm = con.prepareStatement(sql)) {
                ResultSet rs = stm.executeQuery();
                
                while (rs.next()) {
                    Status status = new Status();
                    status.setStatus_id(rs.getInt(1));
                    status.setStatus_name(rs.getString(2));
                    
                    statuses.add(status);
                }
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(StatusDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return statuses;
    }
}
