/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.File;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author ahhun
 */
public class JaxbUtils {
    public static <T> T parseObject(String xmlFilePath, Class<T> jaxbClass){
        T result = null;
        try {
            JAXBContext jc = JAXBContext.newInstance(jaxbClass);
            Unmarshaller u  = jc.createUnmarshaller();
            File f = new File(xmlFilePath);
            result = (T) u.unmarshal(f);
        } catch (JAXBException ex) {
            Logger.getLogger(JaxbUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public static <T> String parseXmlToString(T t){
        try {
            JAXBContext jc = JAXBContext.newInstance(t.getClass());
            Marshaller m  = jc.createMarshaller();
            m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            
            StringWriter result = new StringWriter();
            m.marshal(t, result);
            
            return result.toString();
        } catch (JAXBException ex) {
            Logger.getLogger(JaxbUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static <T> void parseXmlToFile(T t, String xmlFilePath){
        try {
            JAXBContext jc = JAXBContext.newInstance(t.getClass());
            Marshaller m  = jc.createMarshaller();
            m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            
            m.marshal(t, new File(xmlFilePath));
        } catch (JAXBException ex) {
            Logger.getLogger(JaxbUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
