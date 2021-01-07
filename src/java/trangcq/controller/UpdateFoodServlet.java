/*
 * To change this license header, choose Lic  ense Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trangcq.controller;

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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;
import trangcq.utilities.Util;
import vanlt.daos.FoodDAO;
import vanlt.dtos.FoodDto;
import vanlt.dtos.FoodInsertErr;
import vanlt.dtos.RegistrationDTO;

/**
 *
 * @author AVITA
 */
@WebServlet(name = "UpdateFoodServlet", urlPatterns = {"/UpdateFoodServlet"})
public class UpdateFoodServlet extends HttpServlet {

    private final String Update_Fail = "editFood.jsp";

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
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        String url = Update_Fail;
        HttpSession session = request.getSession();
        RegistrationDTO userDto = (RegistrationDTO) session.getAttribute("USER");

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
                Date updateDate = new Date(now.getTime());
                String foodID = parameters.get("txtFoodId");
                String foodname = parameters.get("txtFoodname");
                String foodprice = parameters.get("txtPrice");
                String fquantity = parameters.get("txtQuantity");
                String categori_id = parameters.get("txtCategory");
                String description = parameters.get("txtDes");
                String fileImage = parameters.get("fileImage");
                String currImageLink = parameters.get("txtImage");

                String imageLink = null;
                try {
                    String uploadPath = request.getServletContext().getRealPath("/") + "img" + File.separator;
                    String fileName = Util.randomFileName(15);
                    String extension = Util.getFileExtension(fileItem.getName());
                    System.out.println("đuôi của nó là : " + extension);
                    File fileUpload = new File(uploadPath + fileName + extension);
                    fileItem.write(fileUpload);
                    imageLink = "img/" + fileName + extension;
                    System.out.println("cả cái hình ảnh tên là : " + imageLink);
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
                String imageUp;
                if (imageLink != null) {
                    imageUp = imageLink;
                }else{
                    imageUp = currImageLink;
                }
                if (fourdErr) {
                    request.setAttribute("CREATEERROR", errors);
                } else {
                    FoodDAO dao = new FoodDAO();

                    FoodDto dto = new FoodDto(Integer.parseInt(foodID), foodname, price, quantity, categoryId, description, imageUp, updateDate, userDto.getId());
                    boolean result = dao.updateFood(dto);
                    if (result) {
                        url = "search.jsp";
                    }
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (NamingException ex) {
            ex.printStackTrace();
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
