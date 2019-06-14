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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import utils.XMLUtils;

/**
 *
 * @author dragn
 */
public class SearchController extends HttpServlet {

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
        String path = MainController.SEARCH_PAGE;
        try {
            String text = request.getParameter("txtSearch");

            String realPath = getServletContext().getRealPath("/");
            String docPath = realPath + MainController.XML_DOC;

            Document document = XMLUtils.parseToDom(docPath);
            if (document != null) {
                XPath xPath = XMLUtils.createXPath();
                String exp = "//student[contains(address, '"
                        + text
                        + "')]";
                NodeList listStudent = (NodeList) xPath.evaluate(exp, document, XPathConstants.NODESET);

                if (listStudent != null) {
                    List<StudentDTO> list = new ArrayList<>();
                    String firstname = null, lastname = null, id, password = null, address = null, status = null, sClass, sex = null;
                    for (int i = 0; i < listStudent.getLength(); i++) {
                        Node student = listStudent.item(i);
                        NamedNodeMap attrs = student.getAttributes();

                        id = attrs.getNamedItem("id").getNodeValue();
                        sClass = attrs.getNamedItem("class").getNodeValue();

                        NodeList childrenOfStudent = student.getChildNodes();

                        for (int j = 0; j < childrenOfStudent.getLength(); j++) {
                            Node child = childrenOfStudent.item(j);
                            switch (child.getNodeName()) {
                                case "firstname":
                                    firstname = child.getTextContent().trim();
                                    break;
                                case "lastname":
                                    lastname = child.getTextContent().trim();
                                    break;
                                case "password":
                                    password = child.getTextContent().trim();
                                    break;
                                case "sex":
                                    sex = child.getTextContent().trim();
                                    break;
                                case "address":
                                    address = child.getTextContent().trim();
                                    break;
                                case "status":
                                    status = child.getTextContent().trim();
                                    break;
                            }
                        }
                        StudentDTO dto = new StudentDTO(firstname, lastname, id, password, address, status, sClass, sex);
                        list.add(dto);
                    }
                    request.setAttribute("RESULT", list);
                    
                }
            }
        } catch (Exception e) {
//            log("ERROR at Search Controller:" + e.getMessage());
            e.printStackTrace();
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
