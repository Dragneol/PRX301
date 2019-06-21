/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duongpth.controllers;

import duongpth.daos.RecipeDAO;
import duongpth.jaxbs.Recipe;
import duongpth.jaxbs.Recipes;
import duongpth.utils.CrawlUtil;
import duongpth.utils.JAXBUtil;
import duongpth.utils.MarkerDTO;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.stream.XMLStreamException;
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
        String path = MainController.ERRORPAGE;
        try {
            String homePage = request.getParameter("recipePage");
            String subDomain = request.getParameter("recipeSubDomain");

            String crawLink = CrawlUtil.normalizeLink(homePage, subDomain);
            String xslFileLinks = getServletContext().getRealPath("/") + "WEB-INF/xsl/recipeLink.xsl";

            String start = "<div class=\"box-recipe_bottom\">";
            String end = "Tiếp theo</a></div> </div> </div>";
            String nextPage = "";
            MarkerDTO markerHome = new MarkerDTO();
            markerHome.setEnd(end);
            markerHome.setStart(start);
            markerHome.setIncluded(true);

            start = "<div class=\"summary entry-summary\">";
            end = "</div><!-- .summary -->";
            MarkerDTO markerDetail = new MarkerDTO();
            markerDetail.setEnd(end);
            markerDetail.setStart(start);
            markerDetail.setIncluded(false);
            String xslFileDetail = getServletContext().getRealPath("/") + "WEB-INF/xsl/recipeDetail.xsl";

            InputStream stream = null;

            List<Recipe> list = null;
            Recipes recipes = null;
            Recipe ing = null;
            RecipeDAO dao = new RecipeDAO();
            int numPage = 1;
            do {
                System.out.println("Crawling " + crawLink);
                stream = CrawlUtil.getDataFromWeb(crawLink, markerHome);
                stream = CrawlUtil.processWellForm(stream);
                stream = CrawlUtil.transformXML(stream, xslFileLinks);
                stream.reset();

                recipes = JAXBUtil.unmarshalling(stream, new Recipes());
                list = recipes.getRecipe();

                nextPage = recipes.getNextpage();
                if (nextPage != null && !nextPage.equals("")) {
                    crawLink = nextPage;
                }

                for (Recipe recipe : list) {
                    System.out.println("Crawling " + recipe.getLink());
                    stream = CrawlUtil.getDataFromWeb(recipe.getLink(), markerDetail);
                    stream = CrawlUtil.processWellForm(stream);
                    stream = CrawlUtil.transformXML(stream, xslFileDetail);
                    stream.reset();

                    dao.insert(recipe);
                }
                System.out.println("next page: " + nextPage);
            } while (nextPage != null && !nextPage.equals("") && numPage < 3);

        } catch (MalformedURLException | XMLStreamException | TransformerException | UnsupportedEncodingException ex) {
            Logger.getLogger(RecipeController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            log("ERROR at RecipeController: " + ex.getMessage());
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
