/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import utils.XMLUtils;

/**
 *
 * @author dragn
 */
public class LoginController extends HttpServlet {

    private boolean found, continuos;
    private String name;

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
            String username = request.getParameter("txtUsername");
            String password = request.getParameter("txtPassword");

            String realPath = getServletContext().getRealPath("/");
            String docPath = realPath + MainController.XML_DOC;

            Document document = XMLUtils.parseToDom(docPath);
            if (document != null) {
                found = false;
                continuos = true;
                name = "";

                checkLogin(document, username, password);
                if (found) {
                    path = MainController.SEARCH_PAGE;
                    HttpSession session = request.getSession();
                    session.setAttribute("USER", name);
                }
            }
        } catch (Exception e) {
            log("ERROR at Login Controller: " + e.getMessage());
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

    private void checkLogin(Node node, String username, String password) {
        if (node == null || found || !continuos) {
            return;
        }

        if (node.getNodeName().equals("student")) {
            NamedNodeMap attrs = node.getAttributes();

            String id = attrs.getNamedItem("id").getNodeValue();
            if (id.equals(username)) {
                NodeList children = node.getChildNodes();
                for (int i = 0; i < children.getLength(); i++) {
                    Node child = children.item(i);
                    switch (child.getNodeName()) {
                        case "firstname":
                            name += child.getTextContent().trim() + " ";
                            break;
                        case "lastname":
                            name += child.getTextContent().trim();
                            break;
                        case "password":
                            String pass = child.getTextContent().trim();
                            if (!pass.equals(password)) {
                                continuos = false;
                            }
                            break;
                        case "status":
                            if (continuos) {
                                String status = child.getTextContent().trim();
                                if (status.equals("studying")) {
                                    found = true;
                                }
                            }
                            break;
                    }
                }
            }
        }

        NodeList children = node.getChildNodes();
        int i = 0;
        while (i < children.getLength()) {
            checkLogin(children.item(i++), username, password);
        }
    }
}
