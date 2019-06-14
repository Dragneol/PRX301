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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import utils.XMLUtil;

/**
 *
 * @author dragn
 */
public class LoginController extends HttpServlet {

    private final String xmlFile = "/WEB-INF/studentsAccount.xml";
    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "search.jsp";
    private String fullName;
    private boolean found = false;

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
        String url = ERROR;
        try {
            String username = request.getParameter("txtUsername");
            String password = request.getParameter("txtPassword");
            String realPath = request.getServletContext().getRealPath("/");
            String filePath = realPath + xmlFile;
            Document doc = XMLUtil.parseToDom(filePath);
//            fullName = "";
            checkLogin(doc, username, password);
            if (found) {
                url = SUCCESS;
                HttpSession session = request.getSession();
                session.setAttribute("name", fullName);
            }
        } catch (Exception e) {
            log("ERROR At Login Controller: " + e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
        try {
            if (node == null || found) {
                return;
            }
            if (node.getNodeName().equals("student")) {
                String id = node.getAttributes().getNamedItem("id").getNodeValue();
                if (username.equals(id)) {
                    NodeList childrenOfStudent = node.getChildNodes();
                    for (int i = 0; i < childrenOfStudent.getLength(); i++) {
                        Node tmp = childrenOfStudent.item(i);
                        if (tmp.getNodeName().equals("lastname")) {
                            fullName += tmp.getTextContent();
                        } else if (tmp.getNodeName().equals("middleName")) {
                            fullName += tmp.getTextContent();
                        } else if (tmp.getNodeName().equals("firstname")) {
                            fullName += tmp.getTextContent();
                        } else if (tmp.getNodeName().equals("password")) {
                            String pass = tmp.getTextContent().trim();
                            if (pass.equals(password)) {
                                found = true;
                                break;
                            }
                        }
                    }
                } else {
                    return;
                }
            }
            NodeList children = node.getChildNodes();
            int count = 0;
            while (count < children.getLength()) {
                checkLogin(children.item(count++), username, password);
            }
        } catch (Exception e) {
            log("ERROR At LoginController: " + e.getMessage());
        }
    }
}
