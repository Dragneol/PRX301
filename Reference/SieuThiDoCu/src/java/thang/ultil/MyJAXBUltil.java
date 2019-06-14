/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thang.ultil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.Result;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import org.xml.sax.SAXException;
import thang.crawl.CrawlUltil;

/**
 *
 * @author Decen
 */
public class MyJAXBUltil {
    public static <T> T unmarshalling(InputStream inputStream, T result) throws JAXBException, FileNotFoundException, XMLStreamException {
        JAXBContext jc = JAXBContext.newInstance(result.getClass());
        Unmarshaller u = jc.createUnmarshaller();

        result = (T) u.unmarshal(inputStream);
        return result;
    }

    public static <T> T unmarshalling(InputStream inputStream, T result, String schemaUrl) {
        try {
            JAXBContext jc = JAXBContext.newInstance(result.getClass());
            Unmarshaller u = jc.createUnmarshaller();

            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = sf.newSchema(new File(schemaUrl));
            u.setSchema(schema);

            result = (T) u.unmarshal(inputStream);
            return result;
        } catch (SAXException ex) {
            Logger.getLogger(CrawlUltil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JAXBException ex) {
            Logger.getLogger(CrawlUltil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public static <T> void marshalling(T object, OutputStream os){
        try {
            JAXBContext jc = JAXBContext.newInstance(object.getClass());
            Marshaller marshaller = jc.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            
            marshaller.marshal(object, os);
        } catch (JAXBException ex) {
            Logger.getLogger(MyJAXBUltil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static <T> void marshalling(T object, StringWriter stringWriter){
        try {
            JAXBContext jc = JAXBContext.newInstance(object.getClass());
            Marshaller marshaller = jc.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            
            marshaller.marshal(object, stringWriter);
        } catch (JAXBException ex) {
            Logger.getLogger(MyJAXBUltil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
