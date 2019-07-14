/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duongpth.controllers;

import duongpth.daos.RecipeDAO;
import duongpth.jaxbs.Recipe;
import duongpth.utils.FormatingUtil;
import duongpth.utils.VietnameseUtil;
import duongpth.utils.JAXBUtil;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.xml.sax.SAXException;

/**
 *
 * @author dragn
 */
public class PrintPdfController extends HttpServlet {

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
        response.setContentType("application/pdf;charset=UTF-8");
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        request.setCharacterEncoding(StandardCharsets.UTF_8.name());
        try {
            String text = request.getParameter("id");
            int recipeid;
            if (text != null) {
                String realPath = getServletContext().getRealPath("/");
                String xslPath = realPath + "WEB-INF/fop/recipeFO.xsl";
                String foPath = realPath + "WEB-INF/fop/recipeFO.fo";
                recipeid = Integer.parseInt(text);
                RecipeDAO dao = new RecipeDAO();
                Recipe recipe = dao.getRecipe(recipeid);
                StringWriter writer = new StringWriter();
                JAXBUtil.marshalling(recipe, writer);
                String tmp = VietnameseUtil.encode(writer.getBuffer().toString());
                FormatingUtil.methodTrAX(xslPath, tmp, foPath, realPath);
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                FopFactory ff = FopFactory.newInstance();
                ff.setUserConfig(realPath + "/WEB-INF/config/fop.xml");
                FOUserAgent fua = ff.newFOUserAgent();
                Fop fop = ff.newFop(MimeConstants.MIME_PDF, fua, out);
                TransformerFactory tff = TransformerFactory.newInstance();
                Transformer trans = tff.newTransformer();
                File fo = new File(foPath);
                Source src = new StreamSource(fo);
                Result result = new SAXResult(fop.getDefaultHandler());
                trans.transform(src, result);
                byte[] content = out.toByteArray();
                response.setContentLength(content.length);
                response.getOutputStream().write(content);
                response.getOutputStream().flush();
            }
        } catch (SQLException | NamingException | ClassNotFoundException | SAXException | TransformerException ex) {
            Logger.getLogger(PrintPdfController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(PrintPdfController.class.getName()).log(Level.SEVERE, null, ex);
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
