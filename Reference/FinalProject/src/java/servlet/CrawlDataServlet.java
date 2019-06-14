/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.Crawler;

/**
 *
 * @author ahhun
 */
public class CrawlDataServlet extends HttpServlet {
    private final String DATA_FILE = "WEB-INF/temp.xml";
    private final String CGV_CONFIG_FILE = "WEB-INF/cgv.xml";
    private final String LOTTE_CONFIG_FILE = "WEB-INF/lotte.xml";
    
    private final String LAST_SEARCH_DATE = "LAST_SEARCH_DATE";
    
    private final String DONE_STATUS = "Done";
    private final String STOPPING_STATUS = "Stopping";
    private final String STOPPED_STATUS = "Stopped";
    private final String SCHEDULE_STATUS = "In schedule";
    private final String NOT_SCHEDULE_STATUS = "Not in schedule";
    
    private final int SCHEDULE_TIME = 2 * 3600 * 1000;
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
        try (PrintWriter out = response.getWriter()) {
            String action = request.getParameter("action");
            
            ServletContext context = getServletContext();

            String realPath = context.getRealPath("/");
            String dataFilePath = realPath + DATA_FILE;
            String cgvFilePath = realPath + CGV_CONFIG_FILE;
            String lotteFilePath = realPath + LOTTE_CONFIG_FILE;

            if ("start".equals(action)) {
                if (Crawler.isCrawling) {
                    out.println("In schedule, try again later!");
                } else {
                    Crawler.crawl(dataFilePath, cgvFilePath, lotteFilePath);

                    context.removeAttribute(LAST_SEARCH_DATE);

                    if (Crawler.isStopping) {
                        Crawler.isStopping = false;
                        out.println(STOPPED_STATUS);
                    } else {
                        out.println(DONE_STATUS);
                    }
                }
            } else if ("stop".equals(action)) {
                Crawler.isStopping = true;
                
                getServletContext().removeAttribute(LAST_SEARCH_DATE);
                
                out.println(STOPPING_STATUS);
            } else if ("start-schedule".equals(action)) {
                Crawler.startCrawlingRegularly(dataFilePath, cgvFilePath, lotteFilePath, SCHEDULE_TIME, context, LAST_SEARCH_DATE);
                out.println(SCHEDULE_STATUS);
            } else if ("stop-schedule".equals(action)) {
                Crawler.stopCrawlingRegularly();
                out.println(NOT_SCHEDULE_STATUS);
            } else if ("check-schedule".equals(action)) {
                out.println(Crawler.isScheduled ? SCHEDULE_STATUS : NOT_SCHEDULE_STATUS);
            }
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
