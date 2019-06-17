/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contextlisteners;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import utils.XmlUtil;

/**
 * Web application lifecycle listener.
 *
 * @author dragn
 */
public class MyContextServletListener implements ServletContextListener {

    private final String xmlFile = "/WEB-INF/studentsAccount.xml";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            System.out.println("Deploying...");
            ServletContext context = sce.getServletContext();
            String realPath = context.getRealPath("/");
            String xmlFilePath = realPath + xmlFile;
            Document doc = XmlUtil.parseFileToDom(xmlFilePath);

            if (doc != null) {
                context.setAttribute("DOMTREE", doc);
            }
        } catch (SAXException ex) {
            Logger.getLogger(MyContextServletListener.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MyContextServletListener.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(MyContextServletListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("bye bye");
    }
}
