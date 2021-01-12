/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vanlt.bookingitem;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import vanlt.conn.MyConnection;

/**
 *
 * @author AVITA
 */
public class BookingItemDAO implements Serializable {

    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

    public BookingItemDAO() {
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

    public int countTotalBookedFood(int foodId) throws SQLException, NamingException {
        //String sql = "SELECT COUNT(b.Id) as TotalBooked FROM BookingDetail b WHERE b.FoodId = ? ";
        String sql = "SELECT SUM(b.Amount) as TotalBooked FROM BookingDetail b WHERE b.FoodId = ? ";
        int totalBooked = 0;
        try {
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, foodId);

            rs = preStm.executeQuery();
            if (rs.next()) {
                totalBooked = rs.getInt("TotalBooked");
            }
        } finally {
            closeConnection();
        }
        return totalBooked;
    }

    public boolean insertBookingItem(BookingItemDTO dto) throws SQLException, NamingException {
        boolean result = false;
        try {
            String sql = "INSERT INTO BookingDetail (BookingId, FoodId, Amount) VALUES(?, ?, ?)";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, dto.getBookingId());
            preStm.setInt(2, dto.getFoodId());
            preStm.setInt(3, dto.getAmount());
            result = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }

    public List<BookingItemDTO> itemByBookingID(int bookingID) throws SQLException, NamingException {
        ArrayList<BookingItemDTO> list = new ArrayList<>();
        try {
            String sql = "  select b.FoodId , b.Amount as soLuong "
                    + " from BookingDetail b ,Food f "
                    + " where f.foodId = b.FoodId and b.BookingId = ? ";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);

            preStm.setInt(1, bookingID);
            rs = preStm.executeQuery();
            while (rs.next()) {
                int foodId = rs.getInt("FoodId");
                int quan = rs.getInt("soLuong");

                list.add(new BookingItemDTO(foodId, quan));
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public String getFoodName(int foodId) throws SQLException, NamingException {
        String foodName = null;
        try {
            String sql = " select foodname from Food  where foodId = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, foodId);
            rs = preStm.executeQuery();
            while (rs.next()) {
                foodName = rs.getString("foodname");
            }
        } finally {
            closeConnection();
        }
        return foodName;

    }

    String getFoodImage(int foodId) throws SQLException, NamingException {
        String foodName = null;
        try {
            String sql = " select imageLink from Food  where foodId = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, foodId);
            rs = preStm.executeQuery();
            while (rs.next()) {
                foodName = rs.getString("imageLink");
            }
        } finally {
            closeConnection();
        }
        return foodName;
    }

}
