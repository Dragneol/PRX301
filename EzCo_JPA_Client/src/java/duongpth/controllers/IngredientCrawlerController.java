/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duongpth.controllers;

import duongpth.handlers.ItemHandler;
import duongpth.jaxbs.Subdomain;
import duongpth.jaxbs.Website;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
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
public class IngredientCrawlerController extends HttpServlet {

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
        String path = MainController.INDEX_CONTROLLER;
        try {
            String homePage = request.getParameter("ingredientPage");
            String subDomain = request.getParameter("ingredientSubDomain");
            int categoryId = Integer.parseInt(subDomain.trim());
            HttpSession session = request.getSession();
            Website ingredientSite = (Website) session.getAttribute("INGREDIENT_WEBSITE");

            List<Subdomain> subdomains = ingredientSite.getSubdomains().getSubdomain();
            ItemHandler handler = new ItemHandler(getServletContext());

            if (categoryId != 0) {
                subDomain = subdomains.get(categoryId).getHref();
                handler.crawlIngredient(ingredientSite, homePage, subDomain, categoryId);
            } else {
                for (int i = 1; i < subdomains.size(); i++) {
                    subDomain = subdomains.get(i).getHref();
                    handler.crawlIngredient(ingredientSite, homePage, subDomain, i);
                }
            }
        } catch (JAXBException | FileNotFoundException | XMLStreamException | TransformerException | UnsupportedEncodingException ex) {
            Logger.getLogger(IngredientCrawlerController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(IngredientCrawlerController.class.getName()).log(Level.SEVERE, null, ex);
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
