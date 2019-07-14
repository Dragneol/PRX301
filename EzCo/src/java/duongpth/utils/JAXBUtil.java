/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duongpth.utils;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLStreamException;

/**
 *
 * @author dragn
 */
public class JAXBUtil {

    public static <T> T unmarshalling(InputStream inputStream, T result) throws JAXBException, FileNotFoundException, XMLStreamException {
        JAXBContext jc = JAXBContext.newInstance(result.getClass());
        Unmarshaller u = jc.createUnmarshaller();
        result = (T) u.unmarshal(inputStream);
        return result;
    }

    public static <T> void marshalling(T object, StringWriter stringWriter) {
        try {
            JAXBContext jc = JAXBContext.newInstance(object.getClass());
            Marshaller marshaller = jc.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(object, stringWriter);
        } catch (JAXBException ex) {
            Logger.getLogger(JAXBUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static <T> void marshalling(T object, OutputStream stream) {
        try {
            JAXBContext jc = JAXBContext.newInstance(object.getClass());
            Marshaller marshaller = jc.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(object, stream);
        } catch (JAXBException ex) {
            Logger.getLogger(JAXBUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
