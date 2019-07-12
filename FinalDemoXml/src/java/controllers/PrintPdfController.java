/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamResult;
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
        response.setContentType("text/html;charset=UTF-8");
        try {
            String search = request.getParameter("txtSearch");
            String sql = "Select username, fullname, role FROM Registration "
                    + "Where Fullname like ? "
                    + "FOR XML PATH ('account'), Root('accounts')";

            String sqlConn = "jdbc:sqlserver://localhost:1433;databaseName=SinhVien";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String str;
            try (Connection con = DriverManager.getConnection(sqlConn, "sa", "P@ssw0rd")) {
                System.out.println("connetion accessed");
                PreparedStatement statement = con.prepareStatement(sql);
                statement.setString(1, "%" + search + "%");
                System.out.println("prepare statement accessed");
                try (ResultSet rs = statement.executeQuery()) {
                    if (rs.next()) {
                        System.out.println("result set accessed");
                        str = rs.getString(1);
                        String path = getServletContext().getRealPath("/");
                        String xslPath = path + "WEB-INF/accountFO.xsl";
                        String foPath = path + "WEB-INF/accountFO.fo";
                        methodTrAX(xslPath, str, foPath, path);
                        ByteArrayOutputStream out = new ByteArrayOutputStream();
                        response.setContentType("application/pdf");
                        FopFactory ff = FopFactory.newInstance();
                        ff.setUserConfig(path + "/WEB-INF/config.xml");
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
                } catch (TransformerConfigurationException ex) {
                    Logger.getLogger(PrintPdfController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SAXException | TransformerException ex) {
                    Logger.getLogger(PrintPdfController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (SQLException ex) {
                Logger.getLogger(PrintPdfController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PrintPdfController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void methodTrAX(String xslPath, String xmlString, String output, String path) {
        try {
            TransformerFactory tf = TransformerFactory.newInstance();
            StreamSource xsltFile = new StreamSource(xslPath);
            Transformer transformer = tf.newTransformer(xsltFile);
            transformer.setParameter("pathFile", path);
            InputStream inStream = new ByteArrayInputStream(xmlString.getBytes(StandardCharsets.UTF_8));
            StreamSource xmlFile = new StreamSource(inStream);
            StreamResult htmlFile = new StreamResult(new FileOutputStream(output));
            transformer.transform(xmlFile, htmlFile);
        } catch (TransformerConfigurationException ex) {
//            System.out.println("ERROR at TrAX: " + ex.getMessage());
            ex.printStackTrace();
        } catch (FileNotFoundException | TransformerException ex) {
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
