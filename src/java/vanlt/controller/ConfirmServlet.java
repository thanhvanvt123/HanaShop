/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vanlt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import vanlt.booking.BookingDAO;
import vanlt.booking.BookingDTO;
import vanlt.bookingitem.BookingItemDAO;
import vanlt.bookingitem.BookingItemDTO;
import vanlt.cart.CartObject;
import vanlt.daos.FoodDAO;
import vanlt.dtos.FoodDto;
import vanlt.dtos.RegistrationDTO;

/**
 *
 * @author AVITA
 */
@WebServlet(name = "ConfirmServlet", urlPatterns = {"/ConfirmServlet"})
public class ConfirmServlet extends HttpServlet {

    static org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(ConfirmServlet.class);
    private final String VIEW_CART = "view.jsp";
    private final String CONFIRM_CART = "confirm.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String url = VIEW_CART;
        try {
            HttpSession session = request.getSession();
            if (session != null) {
                CartObject cart = (CartObject) session.getAttribute("CART");
                RegistrationDTO userDTO = (RegistrationDTO) session.getAttribute("USER");
                if (cart != null) {
                    Map<Integer, Integer> items = cart.getItems();
                    Map<Integer, FoodDto> foodItems = cart.getFood();
                    BookingItemDAO bookingItemDAO = new BookingItemDAO();
                    BookingDAO bookingDAO = new BookingDAO();

                    List<String> confirmError = new ArrayList<>();
                    for (Integer foodId : items.keySet()) {
                        int totalBooked = bookingItemDAO.countTotalBookedFood(foodId);
                        FoodDto foodInfo = foodItems.get(foodId);
                        int amount = items.get(foodId);
                        FoodDAO dao = new FoodDAO();
                        int foodQuan = dao.getFoodQuantity(foodId);
                        int notBookedYet = foodQuan - totalBooked;

                        if (notBookedYet - amount < 0) {
                            confirmError.add("Food: " + foodInfo.getFoodname() + " is invalid! (Remainings: " + notBookedYet + "!)");
                        }
                    }

                    if (confirmError.isEmpty()) {
                        BookingDTO dto = new BookingDTO(userDTO.getId(), new Timestamp(System.currentTimeMillis()), cart.getTotalPrice());
                        if (items != null) {
                            int idBookingInsert = bookingDAO.insertBookingFood(dto);
                            dto.setId(idBookingInsert);
                            if (idBookingInsert != -1) {
                                Set<Integer> keyList = items.keySet();
                                for (Integer foodId : keyList) {
                                    BookingItemDTO bookingItemDTO = new BookingItemDTO(dto.getId(), foodId, items.get(foodId));
                                    bookingItemDAO.insertBookingItem(bookingItemDTO);
                                }
                                url = CONFIRM_CART;
                                request.setAttribute("CART", cart);
                                session.removeAttribute("CART");
                            }
                        }
                    } else {
                        request.setAttribute("CONFIRM_ERROR", confirmError);
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (NamingException ex) {
            ex.printStackTrace();
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
