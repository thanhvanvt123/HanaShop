/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vanlt.bookingitem;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

/**
 *
 * @author AVITA
 */
public class BookingItemDTO implements Serializable {

    private int id, bookingId, foodId, amount;
    private String foodName;
    private String imageLink;

    public BookingItemDTO() {
    }

    public BookingItemDTO(int id, int bookingId, int foodId, int amount) {
        this.id = id;
        this.bookingId = bookingId;
        this.foodId = foodId;
        this.amount = amount;
    }

    public BookingItemDTO(int bookingId, int foodId, int amount) {
        this.bookingId = bookingId;
        this.foodId = foodId;
        this.amount = amount;
    }

    public BookingItemDTO(int foodId, int amount) {
        this.foodName = getFoodName(foodId);
        this.imageLink = getFoodImage(foodId);
        this.foodId = foodId;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getFoodName(int foodID) {
        try {
            return new BookingItemDAO().getFoodName(foodID);
        } catch (SQLException ex) {
            Logger.getLogger(BookingItemDTO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(BookingItemDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String getFoodImage(int foodID) {
        try {
            return new BookingItemDAO().getFoodImage(foodID);
        } catch (SQLException ex) {
            Logger.getLogger(BookingItemDTO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(BookingItemDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

}
