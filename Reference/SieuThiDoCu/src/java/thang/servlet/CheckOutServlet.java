/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thang.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import thang.dao.OrderDAO;
import thang.genobj.User;

/**
 *
 * @author Decen
 */
@WebServlet(name = "CheckOutServlet", urlPatterns = {"/CheckOutServlet"})
public class CheckOutServlet extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/xml;charset=UTF-8");
        PrintWriter out = response.getWriter();
        boolean result = true;
        try {
            String txtAddress = request.getParameter("txtAddress");
            String txtEmail = request.getParameter("txtEmail");
            String txtPhone = request.getParameter("txtPhone");
            String products = request.getParameter("products");
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("USER");
            int userId = (user != null)?user.getId().intValue():-1;

            float totalCost = Float.parseFloat(request.getParameter("totalCost"));

            OrderDAO orderDAO = new OrderDAO();
            int orderId = orderDAO.insert(userId, txtAddress, txtEmail, txtPhone, totalCost);
            
            if (orderId != -1) {
                for (String product : products.split(" ")) {
                    String[] info = product.split("-");
                    int productId = Integer.parseInt(info[0]);
                    int quantity = Integer.parseInt(info[1]);
                    orderDAO.insertOrderDetail(orderId, productId, quantity);
                }
            } else {
                result = false;
            }
        } catch (Exception ex) {
//            ex.printStackTrace();
            result = false;
        } finally {
            out.print("<?xml version=\"1.0\" encoding=\"UTF-8\"?><result>" + result + "</result>");
            out.close();
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
