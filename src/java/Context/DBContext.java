package Context;

import java.sql.Connection;
import java.sql.DriverManager;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ACER
 */
public class DBContext {
        public  Connection getConnection(){
        String db = "house_finder_project";
        String url = "jdbc:mysql://localhost:3306/"+db;
        String user = "root";
        String password = "12345678";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, user, password);
        } catch (Exception ex) {
            System.out.println("loi"+ex.getMessage());
        } 
        return null;
    }
}
