/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.sun.codemodel.JCodeModel;
import com.sun.tools.xjc.api.ErrorListener;
import com.sun.tools.xjc.api.S2JJAXBModel;
import com.sun.tools.xjc.api.SchemaCompiler;
import com.sun.tools.xjc.api.XJC;
import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.xml.sax.InputSource;
import org.xml.sax.SAXParseException;

/**
 *
 * @author dragn
 */
public class XsdController extends HttpServlet {

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
            String output = realPath + "/WEB-INF/classess";

            SchemaCompiler sc = XJC.createSchemaCompiler();

            sc.setErrorListener(new ErrorListener() {
                @Override
                public void error(SAXParseException saxpe) {
                    System.out.println("error" + saxpe.getMessage());
                }

                @Override
                public void fatalError(SAXParseException saxpe) {
                    System.out.println("error" + saxpe.getMessage());
                }

                @Override
                public void warning(SAXParseException saxpe) {
                    System.out.println("error" + saxpe.getMessage());
                }

                @Override
                public void info(SAXParseException saxpe) {
                    System.out.println("error" + saxpe.getMessage());
                }
            });

            sc.forcePackageName("jaxbs.library");
            String filePath = realPath + "/WEB-INF/library.xsd";
            File schema = new File(filePath);
            InputSource inSrc = new InputSource(schema.toURI().toString());
            sc.parseSchema(inSrc);
            S2JJAXBModel model = sc.bind();
            JCodeModel code = model.generateCode(null, null);
            code.build(new File(output));
            System.out.println("Finished!");
        } catch (Exception ex) {
            System.out.println("ERROR at XSDToObjController: " + ex.getMessage());
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
