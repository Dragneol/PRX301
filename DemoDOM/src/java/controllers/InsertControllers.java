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
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import utils.XMLUtil;

/**
 *
 * @author dragn
 */
public class InsertControllers extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "SearchController";
    private static final String xmlFile = "/WEB-INF/studentsAccount.xml";

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
        String path = ERROR;
        try {
            String id = request.getParameter("txtID");
            String classR = request.getParameter("txtClass");
            String firstname = request.getParameter("txtFirestname");
            String middlename = request.getParameter("txtMiddlename");
            String lastname = request.getParameter("txtLastname");
            String address = request.getParameter("txtAddress");
            String status = request.getParameter("txtStatus");
            String pass = request.getParameter("txtPassword");
            String sex = request.getParameter("chkSex");

            String realPath = getServletContext().getContextPath("/");
            String fileName = realPath + xmlFile;
            Document doc = XMLUtil.parseToDom(fileName);
            if (doc != null) {
                Element student = doc.createElement("student");
                student.setAttribute("id", id);
                student.setAttribute("class", classR);
                
                Element last = doc.createElement("lastname");
                last.setTextContent(lastname);
                student.appendChild(last);
                
                Element mid = doc.createElement("middlename");
                mid.setTextContent(middlename);
                student.appendChild(mid);
                
                Element first = doc.createElement("firstname");
                first.setTextContent(firstname);
                student.appendChild(first);
                
                Element gender = doc.createElement("gender");
                gender.setTextContent(sex);
                student.appendChild(gender);
                
                Element adr = doc.createElement("address");
                adr.setTextContent(address);
                student.appendChild(adr);
                
                Element sta = doc.createElement("status");
                sta.setTextContent(status);
                student.appendChild(sta);
                
            }
        } catch (Exception e) {
            log("ERROR at Insert Controller:" + e.getMessage());
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
