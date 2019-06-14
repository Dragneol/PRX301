/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dtos.StudentDTO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import utils.XMLUtil;

/**
 *
 * @author dragn
 */
public class SearchController extends HttpServlet {

    private static final String xmlFile = "/WEB-INF/studentsAccount/xml";
    private static final String SUCCESS = "search.jsp";

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
            String search = request.getParameter("txtSearch");
            String realPath = getServletContext().getRealPath("/");
            String filePath = realPath + xmlFile;
            Document doc = XMLUtil.parseToDom(filePath);
            if (doc != null) {
                XPath xPath = XMLUtil.createXPath();
                String exp = "//student[contains(address, '" + search + "')]";
                NodeList studentList = (NodeList) xPath.evaluate(exp, doc, XPathConstants.NODE);
                if (studentList != null) {
                    List<StudentDTO> list = new ArrayList<>();
                    for (int i = 0; i < studentList.getLength(); i++) {
                        Node node = studentList.item(i);
                        System.out.println(i);
                        StudentDTO dto = new StudentDTO();
                        dto.setId(node.getAttributes().getNamedItem("id").getNodeValue());
                        NodeList children = node.getChildNodes();
                        for (int j = 0; j < children.getLength(); j++) {
                            Node tmp = children.item(j);
                            switch (tmp.getNodeName()) {
                                case "firstname":
                                    dto.setFirstname(tmp.getTextContent());
                                    break;
                                case "middlename":
                                    dto.setMiddlename(tmp.getTextContent());
                                    break;
                                case "lastname":
                                    dto.setLastname(tmp.getTextContent());
                                    break;
                                case "address":
                                    dto.setAddress(tmp.getTextContent());
                                    break;
                                case "status":
                                    dto.setStatus(tmp.getTextContent());
                                    break;
                                default:
                                    break;
                            }
                            System.out.println(dto);
                            list.add(dto);
                        }
                        request.setAttribute("INFO", list);
                    }
                }
            }
        } catch (Exception e) {
            log("ERROR at Search Controller:" + e.getMessage());
        } finally {
            request.getRequestDispatcher(SUCCESS).forward(request, response);
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
