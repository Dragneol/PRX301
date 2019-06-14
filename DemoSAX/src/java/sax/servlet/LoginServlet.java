/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sax.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sax.parser.StudentHandler;
import sax.utils.XMLUtils;

/**
 *
 * @author dragn
 */
public class LoginServlet extends HttpServlet {

    private static final String xmlFile = "WEB-INF\\studentsAccount.xml";
    private static final String invalid = "error.jsp";
    private static final String crawl = "crawl.jsp";

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
        String path = invalid;
        try {
            String realPath = getServletContext().getRealPath("/");
            String filePath = realPath + xmlFile;

            String username = request.getParameter("txtUsername");
            String password = request.getParameter("txtPassword");

            System.out.println(username + " " + password);
            StudentHandler saxObj = new StudentHandler(username, password);
            XMLUtils.parseFileWithSAX(filePath, saxObj);
            if (saxObj.isFound()) {
                path = crawl;
                HttpSession session = request.getSession();
                session.setAttribute("NAME", saxObj.getFullName());
            }
        } catch (Exception e) {
            log("ERROR at LoginServlet:" + e.getMessage());
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
