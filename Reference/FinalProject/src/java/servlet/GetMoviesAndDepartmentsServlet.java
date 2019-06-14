/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dao.DepartmentDAO;
import dao.MovieDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jaxb.Departments;
import jaxb.Movies;
import utils.DataUtils;
import utils.JaxbUtils;

/**
 *
 * @author ahhun
 */
public class GetMoviesAndDepartmentsServlet extends HttpServlet {
    private final String HOME_PAGE = "home.jsp";
    private final String THEATERS_PAGE = "theaters.jsp";
    private final String MOVIES_FILE = "WEB-INF/movies.xml";
    private final String DEPARTMENTS_FILE = "WEB-INF/departments.xml";
    private final String LAST_SEARCH_DATE = "LAST_SEARCH_DATE";
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
        PrintWriter out = response.getWriter();
        
        String url = HOME_PAGE;
        
        String[] requestUrlPaths = request.getRequestURL().toString().split("/");
        
        if (requestUrlPaths[requestUrlPaths.length - 1].startsWith("theaters")) url = THEATERS_PAGE;
        
        try {
            ServletContext context = getServletContext();
            
            Date lastSearchDate = (Date) context.getAttribute(LAST_SEARCH_DATE);
            
            if (lastSearchDate != null) {
                Calendar lastSearchCal = Calendar.getInstance();
                lastSearchCal.setTime(lastSearchDate);
                
                int result = DataUtils.compareCalendar(Calendar.getInstance(), lastSearchCal);
                if (result == 0) {
                    return;
                }
            }
            
            Calendar today = Calendar.getInstance();
            Movies movies = MovieDAO.getListMovieByDate(DataUtils.convertDateUtilToSQLDate(today.getTime()));
            Departments departments = DepartmentDAO.getAllDepartments();
            System.out.println("GET MOVIES AND DEPARTMENTS FUNCTION IS INVOKED!");
            
            String realPath = context.getRealPath("/");
            JaxbUtils.parseXmlToFile(movies, realPath + MOVIES_FILE);
            JaxbUtils.parseXmlToFile(departments, realPath + DEPARTMENTS_FILE);

            context.setAttribute(LAST_SEARCH_DATE, today.getTime());
        } catch (SQLException ex) {
            Logger.getLogger(GetMoviesAndDepartmentsServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
