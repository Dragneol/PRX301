/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.File;
import java.io.Serializable;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 *
 * @author dragn
 */
public class XMLUtil implements Serializable {

    public static Document parseToDom(String filePath) throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        File f = new File(filePath);
        Document doc = db.parse(f);
        return doc;
    }

    public static XPath createXPath() {
        XPathFactory xpf = XPathFactory.newInstance();
        XPath xPath = xpf.newXPath();
        return xPath;
    }

    public static boolean transformDOMSrcToStreamResult(Node node, String filePath) {
        Source src = new DOMSource(node);
        File file = new File(filePath);
        Result result = new StreamResult(file);

        TransformerFactory tf = TransformerFactory.newInstance();
        try {
            Transformer trans = tf.newTransformer();
            trans.transform(src, result);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
