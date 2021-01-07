/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trangcq.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import vanlt.daos.CategoryDAO;

/**
 *
 * @author AVITA
 */
public class DispatcherController extends HttpServlet {
    private final String loginPage = "login.jsp";
    private final String loginServlet = "LoginServlet";
    private final String logoutServlet = "LogoutServlet";
    private final String searchServlet = "SearchServlet";
    private final String createNewFoodServlet ="CreateNewFoodServlet";
    private final String deleteFoodServlet = "DeleteFoodServlet";
    private final String updateFoodServlet = "UpdateFoodServlet";
    private final String editFoodServlet = "EditFoodServlet";
    private final String addCart = "AddItemToCartServlet";
    private final String confirmCart = "ConfirmServlet";

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
        String button = request.getParameter("btAction");
        HttpSession session = request.getSession();
        String url = loginPage;
        System.out.println("action lúc nay : " + button);
        try {
            if (button == null) {
                session.setAttribute("LISTCATE", new CategoryDAO().getAllCategory());
                url = "search.jsp";
            } else if (button.equals("Login")) {
                url = loginServlet;
            } else if(button.equals("Add New Food")){
                url = createNewFoodServlet;
            } else if(button.equals("Search")){
                url = searchServlet;
            } else if (button.equals("Delete")){
                url = deleteFoodServlet;
            } else if(button.equals("Update") ){
                url= updateFoodServlet;
            } else if(button.equals("Edit") ){
                url= editFoodServlet;
            } else if (button.equals("LogOut")){
                url= logoutServlet;
            } else if (button.equals("Add To Cart")){
                url= addCart;
            } else if (button.equals("Confirm")){
                url= confirmCart;
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
    }// </editor-fold>

}
