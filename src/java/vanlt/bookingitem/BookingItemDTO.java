/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vanlt.bookingitem;

import java.io.Serializable;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author AVITA
 */
public class BookingItemDTO implements Serializable {

    private int id, bookingId, foodId, amount;

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
    public String getFoodName(int foodID) throws SQLException, NamingException{
        System.out.println("giá trị nó nhận dc là : " + foodID);
        return new BookingItemDAO().getFoodName(foodID);
    }

}
