/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duongpth.utils;

import duongpth.controllers.PrintPdfController;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 *
 * @author dragn
 */
public class FormatingUtil {

    /**
     * Apply xsl to transform into xml
     *
     * @param stream
     * @param xslUrl
     * @return
     * @throws TransformerConfigurationException
     * @throws TransformerException
     * @throws UnsupportedEncodingException
     */
    public static InputStream transformXML(InputStream stream, String xslUrl) throws TransformerConfigurationException, TransformerException, UnsupportedEncodingException {
        StringWriter writer = new StringWriter();
        StreamResult streamResult = new StreamResult(writer);
        TransformerFactory transformFactory = TransformerFactory.newInstance();
        Transformer transformer = transformFactory.newTransformer(new StreamSource(new File(xslUrl)));
        transformer.transform(new StreamSource(stream), streamResult);
        return new ByteArrayInputStream(writer.toString().getBytes(StandardCharsets.UTF_8));
    }

    public static void methodTrAX(String xslUrl, String xmlString, String foPath, String realPath) {
        try {
            TransformerFactory tf = TransformerFactory.newInstance();
            StreamSource xsltFile = new StreamSource(xslUrl);
            Transformer transformer = tf.newTransformer(xsltFile);
            transformer.setParameter("pathFile", realPath);
            InputStream inStream = new ByteArrayInputStream(xmlString.getBytes(StandardCharsets.UTF_8));
            StreamSource xmlFile = new StreamSource(inStream);
            StreamResult htmlFile = new StreamResult(new FileOutputStream(foPath));
            transformer.transform(xmlFile, htmlFile);
        } catch (FileNotFoundException | TransformerException ex) {
            Logger.getLogger(PrintPdfController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
