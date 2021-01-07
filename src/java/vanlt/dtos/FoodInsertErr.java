/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vanlt.dtos;

/**
 *
 * @author AVITA
 */
public class FoodInsertErr {

    private String foodnameErr;
    private String foodpriceErr;
    private String quantityErr;
    private String categoriIDErr;
    private String descriptionErr;
    private String imageErr;

    public FoodInsertErr() {
    }

    public FoodInsertErr(String foodnameErr, String foodpriceErr, String quantityErr, String categoriIDErr, String descriptionErr, String imageErr) {
        this.foodnameErr = foodnameErr;
        this.foodpriceErr = foodpriceErr;
        this.quantityErr = quantityErr;
        this.categoriIDErr = categoriIDErr;
        this.descriptionErr = descriptionErr;
        this.imageErr = imageErr;
    }

    public String getFoodnameErr() {
        return foodnameErr;
    }

    public void setFoodnameErr(String foodnameErr) {
        this.foodnameErr = foodnameErr;
    }

    public String getFoodpriceErr() {
        return foodpriceErr;
    }

    public void setFoodpriceErr(String foodpriceErr) {
        this.foodpriceErr = foodpriceErr;
    }

    public String getQuantityErr() {
        return quantityErr;
    }

    public void setQuantityErr(String quantityErr) {
        this.quantityErr = quantityErr;
    }

    public String getCategoriIDErr() {
        return categoriIDErr;
    }

    public void setCategoriIDErr(String categoriIDErr) {
        this.categoriIDErr = categoriIDErr;
    }

    public String getDescriptionErr() {
        return descriptionErr;
    }

    public void setDescriptionErr(String descriptionErr) {
        this.descriptionErr = descriptionErr;
    }

    public String getImageErr() {
        return imageErr;
    }

    public void setImageErr(String imageErr) {
        this.imageErr = imageErr;
    }



}
