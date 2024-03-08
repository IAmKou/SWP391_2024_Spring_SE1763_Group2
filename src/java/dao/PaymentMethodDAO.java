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
import model.PaymentMethod;

/**
 *
 * @author FPTSHOP
 */
public class PaymentMethodDAO extends DBContext {

    public List<PaymentMethod> getPaymentMethods() {
        List<PaymentMethod> methods = new ArrayList<>();
        
        try {
            String sql = "SELECT * FROM house_finder_project.payment_method;";
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            PreparedStatement stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {                
                PaymentMethod method = new PaymentMethod();
                method.setMethod_id(rs.getInt("method_id"));
                method.setMethod_name(rs.getString("method_name"));
                methods.add(method);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PaymentMethodDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return methods;
    }
}
