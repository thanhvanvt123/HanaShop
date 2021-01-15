/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vanlt.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;
import vanlt.utilities.Util;
import vanlt.daos.FoodDAO;
import vanlt.dtos.FoodDto;
import vanlt.dtos.FoodInsertErr;

/**
 *
 * @author AVITA
 */
@WebServlet(name = "CreateNewFoodServlet", urlPatterns = {"/CreateNewFoodServlet"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 5 * 5)
public class CreateNewFoodServlet extends HttpServlet {

    private final String CREATE_NEW_FOOD = "createFood.jsp";
    

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
        String url = CREATE_NEW_FOOD;
        FoodInsertErr errors = new FoodInsertErr();
        boolean fourdErr = false;
        try {
            boolean isMutilpart = ServletFileUpload.isMultipartContent(request);
            if (isMutilpart) {
                FileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);
               
                List<FileItem> items = upload.parseRequest(new ServletRequestContext(request));
                Map<String, String> parameters = new HashMap<>();
                FileItem fileItem = null;
                for (FileItem item : items) {
                    if (item.isFormField()) {
                        parameters.put(item.getFieldName(), item.getString());
                    }
                    if (item.getFieldName().equals("fileImage")) {
                        fileItem = item;
                    }
                }
                Timestamp now = new Timestamp(System.currentTimeMillis());
                Date createDate = new Date(now.getTime());
                String foodname = parameters.get("txtFoodname");
                String foodprice = parameters.get("txtPrice");
                String fquantity = parameters.get("txtQuantity");
                String categori_id = parameters.get("txtCategory");
                String description = parameters.get("txtDes");

                String imageLink = null;
                try {
                    String uploadPath = request.getServletContext().getRealPath("/") + "img" + File.separator;
                    String fileName = Util.randomFileName(15);
                    String extension = Util.getFileExtension(fileItem.getName());
                    File fileUpload = new File(uploadPath + fileName + extension);
                    fileItem.write(fileUpload);
                    imageLink = "img/" + fileName + extension;
                } catch (Exception ex) {
                    errors.setImageErr("Failed to upload image!");
                }

                if (foodname.trim().length() < 2 || foodname.trim().length() > 70) {
                    fourdErr = true;
                    errors.setFoodnameErr("Food name string is requred from 2 to 70 characters");
                }
                float price = 0;
                try {
                    price = Float.parseFloat(foodprice);
                } catch (Exception e) {

                }
                if (price == 0) {
                    fourdErr = true;
                    errors.setFoodpriceErr("Input price must be interger");
                }
                int quantity = 0;
                try {
                    quantity = Integer.parseInt(fquantity);
                } catch (Exception e) {

                }
                if (quantity == 0) {
                    fourdErr = true;
                    errors.setQuantityErr("Input Quantity must be interger");
                }

                if (description.trim().length() < 2 || description.trim().length() > 300) {
                    fourdErr = true;
                    errors.setDescriptionErr("Description string is requred from 2 to 300 characters");
                }

                int categoryId = 0;
                try {
                    categoryId = Integer.parseInt(categori_id);
                } catch (Exception e) {

                }
                if (categoryId == 0) {
                    fourdErr = true;
                    errors.setCategoriIDErr("Choose one Category!");
                }
                if (fourdErr) {
                    request.setAttribute("CREATEERROR", errors);
                } else {
                    FoodDAO dao = new FoodDAO();
                    FoodDto dto = new FoodDto(111, foodname, price, quantity, categoryId, createDate, description, imageLink);
                    boolean result = dao.insertFood(dto);
                    if (result) {
                        url = "search.jsp";
                    }
                }
            }

        } catch (SQLException ex) {
           log("Error CreFood SQL: " + ex.getMessage());
        } catch (NamingException ex) {
           log("Error CreFood SQL: " + ex.getMessage());
        } catch (FileUploadException ex) {
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
