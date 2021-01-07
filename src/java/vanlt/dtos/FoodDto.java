/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vanlt.dtos;

import java.io.Serializable;
import java.sql.Date;
import java.sql.SQLException;
import javax.naming.NamingException;
import vanlt.daos.FoodDAO;

/**
 *
 * @author AVITA
 */
public class FoodDto implements Serializable {
    private int foodId;
    private String foodname;
    private float foodprice;
    private int quantity;
    private int categoriID;
    private Date createDate;
    private String description;
    private String imageLink;
    private int statusId;
    private Date updateDate;
    private int userId;

    public FoodDto() {
    }

    public FoodDto(int foodId, String foodname, float foodprice, int quantity, int categoriID, Date createDate, String description, String imageLink) {
        this.foodId = foodId;
        this.foodname = foodname;
        this.foodprice = foodprice;
        this.quantity = quantity;
        this.categoriID = categoriID;
        this.createDate = createDate;
        this.description = description;
        this.imageLink = imageLink;
    }

    public FoodDto(int foodId, String foodname, float foodprice, int quantity, int categoriID, String description, String imageLink, Date updateDate, int userId) {
        this.foodId = foodId;
        this.foodname = foodname;
        this.foodprice = foodprice;
        this.quantity = quantity;
        this.categoriID = categoriID;
        this.description = description;
        this.imageLink = imageLink;
        this.updateDate = updateDate;
        this.userId = userId;
    }
    

    

    public String getCategoryname(int categoriID) {
        FoodDAO dao = new FoodDAO();
        try {
            return dao.getCategoryName(categoriID);
        } catch (SQLException ex) {
            System.out.println("ErrorSql In FoodDto : " + ex.getMessage());
        } catch (NamingException ex) {
            System.out.println("ErrorNaming In FoodDto : " + ex.getMessage());
        }
        return null;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    public float getFoodprice() {
        return foodprice;
    }

    public void setFoodprice(float foodprice) {
        this.foodprice = foodprice;
    }

    public int getCategoriID() {
        return categoriID;
    }

    public void setCategoriID(int categoriID) {
        this.categoriID = categoriID;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
        
    
}
