/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dtos.StudentDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import utils.XmlUtil;

/**
 *
 * @author dragn
 */
public class SearchController extends HttpServlet {

    private final String xmlFile = "WEB-INF/studentsAccount.xml";
    private final String showPage = "index.jsp";

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
        String url = showPage;
        try {
            ServletContext context = this.getServletContext();
            Document doc = (Document) context.getAttribute("DOMTREE");
            String searchValue = request.getParameter("txtSearch");
            if (doc != null) {
                String realPath = context.getRealPath("/");
                String xmlFile = realPath + this.xmlFile;
                doc = XmlUtil.parseFileToDom(xmlFile);
                context.setAttribute("DOMTREE", doc);

                if (doc != null) {
                    XPath path = XmlUtil.getXPath();
                    String exp = "//student[contains(address, '"
                            + searchValue
                            + "')]";
                    NodeList studentList = (NodeList) path.evaluate(exp, doc, XPathConstants.NODESET);
                    List<StudentDTO> list = new ArrayList<>();
                    for (int i = 0; i < studentList.getLength(); i++) {
                        Node tmp = studentList.item(i);
                        exp = "@id";
                        String id = (String) path.evaluate(exp, tmp, XPathConstants.STRING);
                        exp = "@class";
                        String sClass = (String) path.evaluate(exp, tmp, XPathConstants.STRING);
                        exp = "lastname";
                        String lastname = (String) path.evaluate(exp, tmp, XPathConstants.STRING);
                        exp = "middlename";
                        String middlename = (String) path.evaluate(exp, tmp, XPathConstants.STRING);
                        exp = "firstname";
                        String firstname = (String) path.evaluate(exp, tmp, XPathConstants.STRING);
                        exp = "gender";
                        String gender = (String) path.evaluate(exp, tmp, XPathConstants.STRING);
                        exp = "password";
                        String password = (String) path.evaluate(exp, tmp, XPathConstants.STRING);
                        exp = "address";
                        String address = (String) path.evaluate(exp, tmp, XPathConstants.STRING);
                        exp = "status";
                        String status = (String) path.evaluate(exp, tmp, XPathConstants.STRING);

                        StudentDTO dto = new StudentDTO(id, sClass, lastname, middlename, firstname, gender, password, address, status);
                        list.add(dto);
                    }
                    request.setAttribute("INFO", list);
                    list.forEach((dto) -> {
                        System.out.println(dto.toString());
                    });
                }
            }
        } catch (IOException | ParserConfigurationException | XPathExpressionException | SAXException e) {
            log("ERROR at SearchController: " + e.getMessage());
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

}
