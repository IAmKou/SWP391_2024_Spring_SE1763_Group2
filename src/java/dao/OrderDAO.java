/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Context.DBContext;
import java.sql.Connection;
import model.Order;

/**
 *
 * @author FPTSHOP
 */
public class OrderDAO extends DBContext{
    public Order getOder(){
        DBContext db = new DBContext();
        Connection con = db.getConnection();
        String sql ="";
        
        
        
        return null;
    }
}
