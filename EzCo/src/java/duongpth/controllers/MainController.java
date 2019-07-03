/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duongpth.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author dragn
 */
public class MainController extends HttpServlet {

    public static final String ADMIN_PAGE = "admin.jsp";
    public static final String ERROR_PAGE = "error.jsp";
    public static final String INDEX_PAGE = "index.jsp";
    public static final String INGREDIENT_PAGE = "ingredient.jsp";
    public static final String RECIPE_PAGE = "recipe.jsp";
    public static final String RECIPE_DETAIL_PAGE = "recipe_detail.jsp";
    public static final String INDEX_CONTROLLER = "RecipeController";
    public static final String RECIPE_CRAWLER = "RecipeController";
    public static final String RECIPE_VIEWER = "RecipeInfoController";
    public static final String RECIPE_DETAIL = "RecipeDetailController";
    public static final String INGREDIENT_CRAWLER = "IngredientController";
    public static final String INGREDIENT_VIEWER = "IngredientInfoController";

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
        String path = ERROR_PAGE;
        try {
            String action = request.getParameter("action");
            switch (action) {
                case "CrawlRecipe":
                    path = RECIPE_CRAWLER;
                    break;
                case "CrawlIngredient":
                    path = INGREDIENT_CRAWLER;
                    break;
                case "Lookup":
                    path = INGREDIENT_VIEWER;
                    break;
                case "Search":
                    path = RECIPE_VIEWER;
                    break;
                case "RecipeDetail":
                    path = RECIPE_DETAIL;
                    break;
                default:
                    log("ERROR at MainController: Action not supported");
                    break;
            }
        } catch (Exception e) {
            log("ERROR at MainController: " + e.getMessage());
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
