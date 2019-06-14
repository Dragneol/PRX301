/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import utils.XMLUtils;

/**
 *
 * @author dragn
 */
public class LoginSevlet extends HttpServlet {

    private static final String xmlFile = "WEB-INF\\studentsAccount.xml";
    private static final String searchPage = "search.jsp";
    private static final String invalid = "error.jsp";

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
        try {
            String realPath = getServletContext().getRealPath("/");
            String xmlPath = realPath + xmlFile;

            String username = request.getParameter("txtUsername");
            String password = request.getParameter("txtPassword");

            System.out.println(username + " " + password);
            String fullname = "";
            boolean found = false;

            String path = invalid;

            XMLStreamReader reader = XMLUtils.createStAXCursorReaderFromFile(xmlPath);
            while (reader.hasNext()) {
                int cursor = reader.next();
                if (cursor == XMLStreamConstants.START_ELEMENT) {
                    String tagName = reader.getLocalName();
                    if (tagName.equals("student")) {
                        String id = reader.getAttributeValue("", "id");
                        if (id.equals(username)) {
                            fullname = XMLUtils.getTextContent(reader, "lastname").trim();
                            fullname += " " + XMLUtils.getTextContent(reader, "middlename").trim();
                            fullname += " " + XMLUtils.getTextContent(reader, "firstname").trim();
                            String pass = XMLUtils.getTextContent(reader, "password");
                            if (pass.equals(password)) {
                                String status = XMLUtils.getTextContent(reader, "status");
                                if (!status.equals("droupout")) {
                                    found = true;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            if (found) {
                path = searchPage;
                HttpSession session = request.getSession();
                session.setAttribute("name", fullname);
            }
            request.getRequestDispatcher(path).forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
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
