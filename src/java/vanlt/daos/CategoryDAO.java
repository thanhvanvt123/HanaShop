/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vanlt.daos;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;
import vanlt.conn.MyConnection;
import vanlt.dtos.CategoryDto;

/**
 *
 * @author AVITA
 */
public class CategoryDAO implements Serializable {

    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

    public CategoryDAO() {
    }

    public void closeConnection() throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (preStm != null) {
            preStm.close();
        }
        if (conn != null) {
            conn.close();
        }
    }

    public ArrayList<CategoryDto> getAllCategory() throws SQLException, NamingException { 
        ArrayList<CategoryDto> listCate = new ArrayList<>();
        try {
            String sql = "SELECT DISTINCT c.Id, c.Name FROM Category c";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("Id");
                String cateName = rs.getString("Name");
                listCate.add(new CategoryDto(id, cateName));
            }
        } finally {
            closeConnection();
        }
        return listCate;
    }
}
