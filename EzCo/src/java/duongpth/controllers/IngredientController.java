/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duongpth.controllers;

import duongpth.daos.IngredientDAO;
import duongpth.jaxbs.Ingredient;
import duongpth.jaxbs.Ingredients;
import duongpth.jaxbs.Marker;
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
import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerException;

/**
 *
 * @author dragn
 */
public class IngredientController extends HttpServlet {

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
        String path = MainController.ERROR_PAGE;
        try {
            String homePage = request.getParameter("ingredientPage");
            String subDomain = request.getParameter("ingredientSubDomain");
            String nextPage = "";
            HttpSession session = request.getSession();
            Website ingredientSite = (Website) session.getAttribute("INGREDIENT_WEBSITE");
//            ItemHandler handler = new ItemHandler(getServletContext());

            String crawledLink = CrawlUtil.normalizeLink(homePage, subDomain);

//            String start = "<main id=\"main\" class=\"site-main\" role=\"main\">";
//            String end = "</main><!-- #main -->";
            Marker markerHome = ingredientSite.getMarkers().getHome();
//
//            start = "<div class=\"summary entry-summary\">";
//            end = "</div><!-- .summary -->";
            Marker markerDetail = ingredientSite.getMarkers().getDetail();

            String xslFileLinks = getServletContext().getRealPath("/") + markerHome.getXsl();
            String xslFileDetail = getServletContext().getRealPath("/") + markerDetail.getXsl();

            InputStream stream = null;
            List<Ingredient> list = null;
            Ingredients ingredients = null;
            Ingredient tmp = null;
            IngredientDAO dao = new IngredientDAO();
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
                            tmp.setLink(crawledLink.trim());
                            tmp.setImage(ingredient.getImage().trim());
                            list.set(index, tmp);
                            dao.insert(tmp);
                        }
                    }
                    nextPage = ingredients.getNextpage();
                }
                if (nextPage != null && !nextPage.equals("")) {
                    crawledLink = nextPage;
                }
            } while (nextPage != null && !nextPage.equals(""));
        } catch (JAXBException ex) {
            Logger.getLogger(IngredientController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(IngredientController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (XMLStreamException ex) {
            Logger.getLogger(IngredientController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(IngredientController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(IngredientController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(IngredientController.class.getName()).log(Level.SEVERE, null, ex);
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
