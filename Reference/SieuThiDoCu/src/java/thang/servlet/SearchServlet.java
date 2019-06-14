/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thang.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import thang.dao.ProductDAO;
import thang.genobj.Products;
import thang.ultil.MyJAXBUltil;

/**
 *
 * @author Decen
 */
@WebServlet(name = "SearchServlet", urlPatterns = {"/SearchServlet"})
public class SearchServlet extends HttpServlet {

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
        response.setContentType("text/xml;charset=UTF-8");

        String txtSearch = request.getParameter("txtSearch");
        String categoryId = request.getParameter("categoryId");
        int page = Integer.parseInt(request.getParameter("page"));
        int num = Integer.parseInt(request.getParameter("num"));
        int filter = 1;
        try {
            filter = Integer.parseInt(request.getParameter("filter"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            ProductDAO productDAO = new ProductDAO();
            Products products = new Products();
            if (categoryId == null || categoryId.isEmpty() || categoryId.equals("null")) {
                products.getProduct().addAll(productDAO.searchProductsByName(txtSearch.trim(), page * num, num, filter));
            } else {
                products.getProduct().addAll(productDAO.searchProductsByNameAndCategory(txtSearch.trim(), categoryId, 0, 20, filter));
            }
            MyJAXBUltil.marshalling(products, response.getOutputStream());
        } finally {
//            out.close();
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
