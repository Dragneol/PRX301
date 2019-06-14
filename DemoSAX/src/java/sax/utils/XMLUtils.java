/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sax.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author dragn
 */
public class XMLUtils implements Serializable {

    public static void parseFileWithSAX(String filePath, DefaultHandler handler) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            File file = new File(filePath);
            SAXParser parser = factory.newSAXParser();
            parser.parse(file, handler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static XMLStreamReader createStAXCursorReaderFromFile(String filePath) throws Exception {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        File f = new File(filePath);
        InputStream inputStream = new FileInputStream(f);
        try {
            XMLStreamReader reader = factory.createXMLStreamReader(inputStream);
            return reader;
        } catch (Exception e) {
        }
        return null;
    }
    
    public static String getTextContent(XMLStreamReader currentCursor, String tagName) throws Exception {
        if (currentCursor!=null) {
            while (currentCursor.hasNext()) {                
                int cursor = currentCursor.next();
                if (cursor==XMLStreamConstants.START_ELEMENT) {
                    String name = currentCursor.getLocalName();
                    if (name.equals(tagName)) {
                        currentCursor.next();
                        String result = currentCursor.getText();
                        currentCursor.nextTag();
                        return result;
                    }
                }
            }
        }
        return null;
    }
}
