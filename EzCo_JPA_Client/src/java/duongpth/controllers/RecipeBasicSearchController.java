/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duongpth.controllers;

import duongpth.daos.RecipeDAO;
import duongpth.ezcojpa.client.RecipeClient;
import duongpth.jaxbs.Recipe;
import duongpth.jaxbs.Recipes;
import duongpth.utils.JAXBUtil;
import duongpth.utils.LogUtil;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
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
public class RecipeBasicSearchController extends HttpServlet {

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
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        request.setCharacterEncoding(StandardCharsets.UTF_8.name());
        String path = MainController.RECIPE_ADVANCE_PAGE;
        try {
            String text = request.getParameter("txtSearch");
//            byte[] bytes = text.getBytes(StandardCharsets.ISO_8859_1);
//            text = new String(bytes, StandardCharsets.UTF_8);
            RecipeDAO dao = new RecipeDAO();
//            List<Recipe> list = dao.getRecipeLikeName(text);
            RecipeClient client = new RecipeClient();
            String tmp = client.findAll_XML(String.class);
            Recipes recipes = JAXBUtil.unmarshalling(new ByteArrayInputStream(tmp.getBytes()), new Recipes());
            for (Recipe recipe : recipes.getRecipe()) {
                System.out.println(recipe.getTitle());
            }
            request.setAttribute("LIST_RECIPE", recipes.getRecipe());
//        } catch (NamingException | SQLException ex) {
//            Logger.getLogger(RecipeBasicSearchController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(RecipeBasicSearchController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            request.getRequestDispatcher(path).forward(request, response);
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
