/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duongpth.console;

import duongpth.jaxbs.Website;
import duongpth.jaxbs.Websites;
import duongpth.utils.CrawlUtil;
import duongpth.utils.JAXBUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;

/**
 *
 * @author dragn
 */
public class Configurer {
    public static void main(String[] args) {
        FileInputStream stream = null;
        try {
            String configFile = "web/WEB-INF/config/config.xml";
            stream = new FileInputStream(new File(configFile));
            Websites websites = JAXBUtil.unmarshalling(stream, new Websites());
            List<Website> list = websites.getWebsite();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Configurer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JAXBException ex) {
            Logger.getLogger(Configurer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (XMLStreamException ex) {
            Logger.getLogger(Configurer.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stream.close();
            } catch (IOException ex) {
                Logger.getLogger(Configurer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
