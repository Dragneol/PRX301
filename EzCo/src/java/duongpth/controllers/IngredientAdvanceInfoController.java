/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duongpth.controllers;

import duongpth.daos.IngredientDAO;
import duongpth.jaxbs.Ingredient;
import duongpth.jaxbs.Ingredients;
import duongpth.jaxbs.Recipes;
import duongpth.utils.JAXBUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author dragn
 */
public class IngredientAdvanceInfoController extends HttpServlet {

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
        response.setContentType("text/xml;charset=UTF-8");
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        request.setCharacterEncoding(StandardCharsets.UTF_8.name());
        try {
            String text = request.getParameter("txtSearch");
            int page = Integer.parseInt(request.getParameter("page"));
            int num = Integer.parseInt(request.getParameter("num"));
            IngredientDAO dao = new IngredientDAO();
            Ingredients ingredients = new Ingredients();
            ingredients.setIngredient(dao.getIngredientsByLikeNamePaging(text, page, num));
            JAXBUtil.marshalling(ingredients, response.getOutputStream());
        } catch (SQLException | NamingException ex) {
            Logger.getLogger(IngredientAdvanceInfoController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(IngredientAdvanceInfoController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
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
