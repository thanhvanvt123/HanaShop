/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trangcq.cart;

import java.io.Serializable;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import javax.naming.NamingException;
import vanlt.dtos.FoodDto;


/**
 *
 * @author AVITA
 */
public class CartObject implements Serializable {

    private FoodDto foodDto;
    public Map<Integer, Integer> items;
    public Map<Integer, FoodDto> travelTour;

    
    
    

    public Map<Integer, Integer> getItems() {
        return items;
    }

    public Map<Integer, FoodDto> getTravelTour() {
        return travelTour;
    }

//    public void addItemToCart(int foodId) throws SQLException, NamingException {
//        if (foodId == 0) {
//            return;
//        }
//        if (this.items == null) {
//            this.items = new HashMap<>();
//        }
//        int amount = 1;
//        if (this.items.containsKey(foodId)) {
//            amount = this.items.get(foodId) + 1;
//        }
//
//        this.items.put(foodId, amount); // id tua và số lượng
//        FoodDto dao = new FoodDto();
//        FoodDto = dao.getTravelTourWithQuota(foodId); // trả về 1 dtoTravel theo ID
//
//        if (travelTour == null) {
//            travelTour = new HashMap<>();
//        }
//        travelTour.put(foodId, FoodDto); // lưu
//
//    }

    public boolean updateItem(int tourId, int amount) {
        if (amount <= 0) {
            return false;
        }

        if (items.containsKey(tourId)) {
            items.put(tourId, amount);
        }
        return true;
    }

    public void removeItemFromCart(int tourId) {
        if (this.items == null) {
            return;
        }
        if (this.items.containsKey(tourId)) {
            this.items.remove(tourId);
        }

        if (this.travelTour == null) {
            return;
        }
        if (this.travelTour.containsKey(tourId)) {
            this.travelTour.remove(tourId);
        }
    }
//
//    public float getTotalPrice() {
//        float totalPrice = 0;
//        float total = 0;
//        if (travelTour != null && items != null) {
//            for (Integer tourId : travelTour.keySet()) {
//                if (items != null) {
//                    int amount = items.get(tourId);
//                    float price = travelTour.get(tourId).getPrice();
//                    total += amount * price;   
//                }
//            }
//        }
//        return total;
//    }
    
//    public float getDiscountValue(){
//        float totalPrice = 0;
//        float total = 0;
//        if (travelTour != null && items != null) {
//            for (Integer tourId : travelTour.keySet()) {
//                if (items != null) {
//                    int amount = items.get(tourId);
//                    float price = travelTour.get(tourId).getPrice();
//                    total += amount * price;   
//                }
//            }
//        }
////        float value = 0;
////        if(discoutDTO != null){
////            value = discoutDTO.getValue();
////        }
//        
//        //totalPrice = total * (value / 100);
//        
//        return total;
//    }
//    public String getDiscountValueDisplay(){
//        DecimalFormat formatter = new DecimalFormat("###,###,###");
//        return formatter.format(getDiscountValue());
//    }
//    
//    public String getTotalPriceDisplay() {
//        DecimalFormat formatter = new DecimalFormat("###,###,###");
//        return formatter.format(getTotalPrice());
//    }
//
//    public float getPriceOfItems(int tourId) {
//        float priceOfItems = 0;
//        if (travelTour != null && items != null) {
//
//            int amount = items.get(tourId);
//            float price = travelTour.get(tourId).getPrice();
//            priceOfItems = amount * price;
//
//        }
//        return priceOfItems;
//    }
//    public String getPriceOfEachItemDisplay(int tourId) {
//        DecimalFormat formatter = new DecimalFormat("###,###,###");
//        return formatter.format(getPriceOfItems(tourId));
//    }
//    
//    public String getPriceOfItemsDisplay(int tourId) {
//        DecimalFormat formatter = new DecimalFormat("###,###,###");
//        return formatter.format(getTotalPrice());
//    }
//
//
//    public String getPriceDisplay(int tourId) {
//        DecimalFormat formatter = new DecimalFormat("###,###,###");
//        return formatter.format(travelTour.get(tourId).getPrice());
//    }
}
