/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duongpth.controllers;

import duongpth.daos.RecipeDAO;
import duongpth.handlers.DataErrorHandler;
import duongpth.jaxbs.Marker;
import duongpth.jaxbs.Recipe;
import duongpth.jaxbs.Recipes;
import duongpth.jaxbs.Website;
import duongpth.utils.CrawlUtil;
import duongpth.utils.JAXBUtil;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.transform.TransformerException;

/**
 *
 * @author dragn
 */
public class RecipeController extends HttpServlet {

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
        String path = MainController.INDEX_CONTROLLER;
        try {
            String homePage = request.getParameter("recipePage");
            String subDomain = request.getParameter("recipeSubDomain");
            String nextPage = "";
            String crawledLink = CrawlUtil.normalizeLink(homePage, subDomain);
            HttpSession session = request.getSession();
            Website recipeSite = (Website) session.getAttribute("RECIPE_WEBSITE");
            Marker markerHome = recipeSite.getMarkers().getHome();
            Marker markerDetail = recipeSite.getMarkers().getDetail();

            String xslFileLinks = getServletContext().getRealPath("/") + markerHome.getXsl();
            String xslFileDetail = getServletContext().getRealPath("/") + markerDetail.getXsl();

            InputStream stream = null;
            List<Recipe> list = null;
            Recipes recipes = null;
            Recipe tmp = null;
            RecipeDAO dao = new RecipeDAO();
            int index;
            do {
                crawledLink = CrawlUtil.normalizeLink(homePage, crawledLink);
                System.out.println("Crawling " + crawledLink);
                stream = CrawlUtil.crawlFromLink(crawledLink, markerHome);
                if (stream != null) {
                    stream.reset();
                    stream = CrawlUtil.transformXML(stream, xslFileLinks);
                    recipes = JAXBUtil.unmarshalling(stream, new Recipes());
                    list = recipes.getRecipe();

                    for (Recipe recipe : list) {
                        crawledLink = CrawlUtil.normalizeLink(homePage, recipe.getLink());
                        System.out.println("Crawling " + crawledLink);
                        stream = CrawlUtil.crawlFromLink(crawledLink, markerDetail);
                        if (stream != null) {
                            stream.reset();
                            stream = CrawlUtil.transformXML(stream, xslFileDetail);
                            tmp = JAXBUtil.unmarshalling(stream, new Recipe());
                            //edit wrong data with default
                            recipe.setLink(crawledLink.trim());
                            tmp = DataErrorHandler.normalizeRecipe(tmp, recipe);
                            index = list.indexOf(recipe);
                            list.set(index, tmp);
                            try {
                                dao.insert(tmp);
                            } catch (Exception e) {
//                                e.printStackTrace();
                                log("ERROR at ID: " + tmp.getId() + " - " + e.getMessage());
                            }
                        }
                    }
                }
                nextPage = recipes.getNextpage();
                if (nextPage != null && !nextPage.equals("")) {
                    crawledLink = nextPage;
                }
            } while (nextPage != null && !nextPage.equals(""));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RecipeController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(RecipeController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(RecipeController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(RecipeController.class.getName()).log(Level.SEVERE, null, ex);
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
