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
import model.TypeOfHouse;

/**
 *
 * @author FPTSHOP
 */
public class TypeOfHouseDAO extends DBContext {

    public List<TypeOfHouse> getType() {
        List<TypeOfHouse> types = new ArrayList<>();

        try {
            String sql = "SELECT * FROM house_finder_project.type_of_house;";
            DBContext db = new DBContext();
            Connection con = db.getConnection();
            PreparedStatement stm = con.prepareStatement(sql);
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    TypeOfHouse type = new TypeOfHouse();
                    type.setType_of_house_id(rs.getInt(1));
                    type.setType_of_house_name(rs.getString(2));
                    
                    types.add(type);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(TypeOfHouseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return types;
    }
}
