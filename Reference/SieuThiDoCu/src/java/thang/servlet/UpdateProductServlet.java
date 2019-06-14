/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thang.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import thang.dao.ProductDAO;
import thang.genobj.Product;
import thang.genobj.Products;
import thang.ultil.MyJAXBUltil;

/**
 *
 * @author Decen
 */
@WebServlet(name = "UpdateProductServlet", urlPatterns = {"/UpdateProductServlet"})
public class UpdateProductServlet extends HttpServlet {

    private final String PRODUCT_PAGE = "/ShowProductServlet";
    private final String EDIT_PAGE = "/EditProductServlet";
    private final String ERROR_PAGE = "error.jsp";

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
        
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String url = ERROR_PAGE;
        try {
            ProductDAO productDAO = new ProductDAO();
            String txtId = request.getParameter("txtId");
            String txtUsername = request.getParameter("txtName");
            String txtPrice = request.getParameter("txtPrice");
            String txtPriceOld = request.getParameter("txtPriceOld");
            String txtStatus = request.getParameter("txtStatus");
            String txtDescription = request.getParameter("txtDescription");
            Product product = new Product();
            product.setId(new BigInteger(txtId));
            product.setName(txtUsername);
            product.setPrice(new BigInteger(txtPrice));
            product.setPriceOld(new BigInteger(txtPriceOld));
            product.setStatus(new BigDecimal(txtStatus));
            product.setDescription(txtDescription);

            boolean result = productDAO.update(product);
            if (result) {
                url = PRODUCT_PAGE + "?productId=" + txtId;
            } else {
                request.setAttribute("ERROR", "Xảy ra lỗi, vui lòng nhập lại");
                url = EDIT_PAGE + "?productId=" + txtId;
            }
        } catch(Exception e){
            e.printStackTrace();
        }finally {
            RequestDispatcher rq = request.getRequestDispatcher(url);
            rq.forward(request, response);
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
