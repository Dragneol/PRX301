/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duongpth.controllers;

import duongpth.daos.IngredientDAO;
import duongpth.jaxbs.Ingredient;
import duongpth.jaxbs.Ingredients;
import duongpth.utils.CrawlUtil;
import duongpth.utils.JAXBUtil;
import duongpth.utils.MarkerDTO;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerException;

/**
 *
 * @author dragn
 */
public class FoodController extends HttpServlet {

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
            String homePage = request.getParameter("foodPage");
            String subDomain = request.getParameter("foodSubDomain");
            String nextPage = "";

            String crawLink = CrawlUtil.normalizeLink(homePage, subDomain);
            String xslFileLinks = getServletContext().getRealPath("/") + "WEB-INF/xsl/ingredientLink.xsl";

            String start = "<main id=\"main\" class=\"site-main\" role=\"main\">";
            String end = "</main><!-- #main -->";

            MarkerDTO markerHome = new MarkerDTO();
            markerHome.setEnd(end);
            markerHome.setStart(start);
            markerHome.setIncluded(true);

            start = "<div class=\"summary entry-summary\">";
            end = "</div><!-- .summary -->";
            MarkerDTO markerDetail = new MarkerDTO();
            markerDetail.setEnd(end);
            markerDetail.setStart(start);
            markerDetail.setIncluded(true);
            String xslFileDetail = getServletContext().getRealPath("/") + "WEB-INF/xsl/ingredientDetail.xsl";

            InputStream stream = null;

            List<Ingredient> list = null;
            Ingredients ingredients = null;
            Ingredient ing = null;
            IngredientDAO dao = new IngredientDAO();
            int numPage = 1;
            do {
                System.out.println("Crawling " + crawLink);
                stream = CrawlUtil.getDataFromWeb(crawLink, markerHome);
                stream = CrawlUtil.processWellForm(stream);
                stream = CrawlUtil.transformXML(stream, xslFileLinks);
                stream.reset();

                ingredients = JAXBUtil.unmarshalling(stream, new Ingredients());
                list = ingredients.getIngredient();

                nextPage = ingredients.getNextpage();
                if (nextPage != null && !nextPage.equals("")) {
                    crawLink = nextPage;
                }

                for (Ingredient ingredient : list) {
                    System.out.println("Crawling " + ingredient.getLink());
                    stream = CrawlUtil.getDataFromWeb(ingredient.getLink(), markerDetail);
                    stream = CrawlUtil.processWellForm(stream);
                    stream = CrawlUtil.transformXML(stream, xslFileDetail);
                    stream.reset();

                    ing = JAXBUtil.unmarshalling(stream, new Ingredient());
                    ingredient.setName(ing.getName());
                    ingredient.setOldid(ing.getOldid());
                    ingredient.setUnit(ing.getUnit());
                    ingredient.setPrice(ing.getPrice());

                    dao.insert(ingredient);
                }
            } while (nextPage != null && !nextPage.equals(""));
        } catch (NamingException | SQLException | IOException | JAXBException | XMLStreamException | TransformerException ex) {
            Logger.getLogger(FoodController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
            log("ERROR at FoodController:" + e.getMessage());
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
