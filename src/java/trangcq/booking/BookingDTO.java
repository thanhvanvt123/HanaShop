/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trangcq.booking;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @author AVITA
 */
public class BookingDTO implements Serializable{
    private int id, userId;
    private Timestamp importedDate;

    public BookingDTO() {
    }

    public BookingDTO(int id, int userId, Timestamp importedDate) {
        this.id = id;
        this.userId = userId;
        this.importedDate = importedDate;
    }

    public BookingDTO(int userId, Timestamp importedDate) {
        this.userId = userId;
        this.importedDate = importedDate;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Timestamp getImportedDate() {
        return importedDate;
    }

    public void setImportedDate(Timestamp importedDate) {
        this.importedDate = importedDate;
    }
    
    
    
}
