/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.io.PrintWriter;
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

/**
 *
 * @author dragn
 */
public class SearchServlet extends HttpServlet {

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
            String str = null;
            String search = request.getParameter("txtSearch");
            String sql = "Select username, fullname, role From Registration Where Fullname like %" + search + "% For XML Path ('account'), Root ('accounts')";
            String connectionString = "jdbc:sqlserver://localhost:1433;databaseName=Sinhvien";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("============ getconnection string");
            try (
                    Connection con = DriverManager.getConnection(connectionString, "sa", "P@ssw0rd");
                    PreparedStatement prestm = con.prepareStatement(sql);) {
                try (ResultSet rs = prestm.executeQuery()) {
                    if (rs.next()) {
                        str = rs.getString(1);
                        System.out.println(str);
                        request.setAttribute("INFO", str);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(SearchServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (SQLException ex) {
                Logger.getLogger(SearchServlet.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
            response.getOutputStream().write(str.getBytes(StandardCharsets.UTF_8));
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SearchServlet.class.getName()).log(Level.SEVERE, null, ex);
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
