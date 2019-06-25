/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duongpth.controllers;

import duongpth.daos.IngredientDAO;
import duongpth.handler.ItemHandler;
import duongpth.jaxbs.Ingredient;
import duongpth.jaxbs.Ingredients;
import duongpth.persistences.IngredientBLO;
import duongpth.utils.CrawlUtil;
import duongpth.utils.JAXBUtil;
import duongpth.utils.MarkerDTO;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
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
            ItemHandler handler = new ItemHandler(getServletContext());

            String crawledLink = CrawlUtil.normalizeLink(homePage, subDomain);

            String start = "<main id=\"main\" class=\"site-main\" role=\"main\">";
            String end = "</main><!-- #main -->";
            MarkerDTO markerHome = handler.getMarker(start, end, true);

            start = "<div class=\"summary entry-summary\">";
            end = "</div><!-- .summary -->";
            MarkerDTO markerDetail = handler.getMarker(start, end, true);

            String xslFileLinks = handler.getIngredients();
            String xslFileDetail = handler.getIngredientDetail();

            InputStream stream = null;
            List<Ingredient> list = null;
            Ingredients ingredients = null;
            Ingredient tmp = null;
            IngredientDAO dao = new IngredientDAO();
//            IngredientBLO blo = new IngredientBLO();
            int index;
            do {
                System.out.println("Crawling " + crawledLink);
                stream = CrawlUtil.crawlFromLink(crawledLink, markerHome);
                if (stream != null) {

                    stream = CrawlUtil.transformXML(stream, xslFileLinks);
                    stream.reset();

                    ingredients = JAXBUtil.unmarshalling(stream, new Ingredients());
                    list = ingredients.getIngredient();

                    for (Ingredient ingredient : list) {
                        crawledLink = CrawlUtil.normalizeLink(homePage, ingredient.getLink());
                        System.out.println("Crawling " + crawledLink);
                        stream = CrawlUtil.crawlFromLink(crawledLink, markerDetail);
                        if (stream != null) {
                            stream = CrawlUtil.transformXML(stream, xslFileDetail);
                            stream.reset();

                            tmp = JAXBUtil.unmarshalling(stream, new Ingredient());
                            index = list.indexOf(ingredient);
                            tmp.setLink(crawledLink);
                            tmp.setLink(ingredient.getImage());
                            list.set(index, tmp);
                            dao.insert(tmp);
//                            blo.insert(tmp);
                        }
                    }
                    nextPage = ingredients.getNextpage();
                }
                if (nextPage != null && !nextPage.equals("")) {
                    crawledLink = nextPage;
                }
            } while (nextPage != null && !nextPage.equals(""));
        } catch (NamingException ex) {
            Logger.getLogger(FoodController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(FoodController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JAXBException ex) {
            Logger.getLogger(FoodController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FoodController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (XMLStreamException ex) {
            Logger.getLogger(FoodController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(FoodController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(FoodController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(FoodController.class.getName()).log(Level.SEVERE, null, ex);
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
