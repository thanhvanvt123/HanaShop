/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vanlt.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import vanlt.daos.CategoryDAO;
import vanlt.daos.FoodDAO;
import vanlt.dtos.FoodDto;

/**
 *
 * @author AVITA
 */
@WebServlet(name = "SearchServlet", urlPatterns = {"/SearchServlet"})
public class SearchServlet extends HttpServlet {

    private final String URL_SEARCH_PAGE = "search.jsp";

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
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        String url = URL_SEARCH_PAGE;
        String searchName = request.getParameter("txtSearchName");
        String searchCate = request.getParameter("txtSearchCategory");
        String searchFromPrice = request.getParameter("txtFromPrice");
        String searchToPrice = request.getParameter("txtToPrice");
        String pageNum = request.getParameter("pageNum");
        try {
            if (searchCate.isEmpty() && searchFromPrice.isEmpty() && searchToPrice.isEmpty()) {
                url = URL_SEARCH_PAGE;
            } else {
                int cate_id;
                if (searchCate.equals("")) {
                    cate_id = 0;
                } else {
                    cate_id = Integer.parseInt(searchCate);
                }
                
                float fromPrice;
                if (searchFromPrice.equals("")) {
                    fromPrice = -1;
                } else {
                    fromPrice = Float.parseFloat(searchFromPrice);
                }

                float toPrice;
                if (searchToPrice.equals("")) {
                    toPrice = -1;
                } else {
                    toPrice = Float.parseFloat(searchToPrice);
                }

                String food_name; 
                if (searchName.equals("")) {
                    food_name = null;
                }else{
                    food_name = searchName;
                }

                FoodDAO foodDao = new FoodDAO();
                CategoryDAO cateDao = new CategoryDAO();
                List<FoodDto> listFood;
                if (fromPrice == -1 && toPrice == -1 && searchCate.isEmpty()) {
                    //do nothing
                } else {
                    int numberFood = foodDao.countTotalFood(food_name,cate_id, toPrice, fromPrice);
                    int page = (int) (Math.ceil((double) numberFood / 5));
                    request.setAttribute("PAGENUMBER", page);
                    int pageIndex = 1;
                    if (pageNum != null && !pageNum.equals("")){
                        pageIndex = Integer.parseInt(pageNum);
                    }
                    listFood = foodDao.searchFoodPaging(food_name, cate_id, toPrice, fromPrice, pageIndex);
                    request.setAttribute("SEARCH_RESULT", listFood);
                    session.setAttribute("LISTCATE", cateDao.getAllCategory());

                }
            }
        } catch (SQLException ex) {
            System.out.println("Error SQL Search Food: " + ex.getMessage());
        } catch (NamingException ex) {
            ex.printStackTrace();
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
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
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
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
