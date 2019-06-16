/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duongpth.utils;

import com.sun.xml.internal.stream.events.EndElementEvent;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.XMLEvent;
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
public class CrawlUtil implements Serializable {

    /**
     * Crawl from url and return InputStream
     *
     * @param url
     * @return
     * @throws MalformedURLException
     * @throws IOException
     * @throws XMLStreamException
     */
    public static InputStream getDataFromWeb(String url) throws MalformedURLException, IOException, XMLStreamException {
        URL urlink = new URL(url);
        HttpURLConnection con = (HttpURLConnection) urlink.openConnection();
        InputStream inputStream = con.getInputStream();
        String content = processStreamToString(inputStream);
        content = cutContent(content);
        return new ByteArrayInputStream(content.getBytes("UTF-8"));
    }

    public static String processStreamToString(InputStream inputStream) throws IOException {
        StringBuilder content;
        try (InputStreamReader reader = new InputStreamReader(inputStream, "UTF-8"); BufferedReader br = new BufferedReader(reader)) {
            content = new StringBuilder();
            String line = null;
            while ((line = br.readLine()) != null) {
                line = EncodeUtil.encode(line);
                content.append(line);
            }
        }
        return content.toString().trim();
    }

    public static String cutContent(String content) {
        String start = "<div class=\"box-recipe_bottom\">";
        String end = "Tiếp theo</a></div> </div> </div>";
        content = content.substring(content.indexOf(start), content.indexOf(end) + end.length());
        content = "<root>" + content + "</root>";
        return content;
    }

    /**
     * Wellform input stream
     *
     * @param inputStream
     * @return
     * @throws XMLStreamException
     * @throws UnsupportedEncodingException
     */
    public static InputStream processWellForm(InputStream inputStream) throws XMLStreamException, UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        StreamSource streamSource = new StreamSource(inputStream);

        XMLInputFactory factory = XMLInputFactory.newFactory();
        XMLEventReader reader = factory.createXMLEventReader(inputStream, "UTF-8");

        int tagMarker = 0;

        while (reader.hasNext()) {
            XMLEvent event = null;
            try {
                event = reader.nextEvent();
            } catch (XMLStreamException ex) {
                String message = ex.getMessage();
                String errString = "matching end-tag \"</";
                if (message.contains("Message: The element type")) {
                    String missingTagName = message.substring(message.indexOf(errString) + errString.length(), message.indexOf(">\"."));
                    EndElement missingTag = new EndElementEvent(new QName(missingTagName));
                    event = missingTag;
                }
            } catch (NullPointerException ex) {
                break;
            }

            if (event != null) {
                if (event.isStartElement()) {
                    tagMarker++;
                } else if (event.isEndElement()) {
                    tagMarker--;
                }
                if (tagMarker >= 0) {
                    result.append(event.toString().trim());
                }
            }
        }
        String res = result.toString().trim().replace("version=\"null\"", "version=\"1.0\"").replace("ENDDOCUMENT", "");
        return new ByteArrayInputStream(res.getBytes("UTF-8"));
    }

    /**
     * Apply xsl to transform into xml
     *
     * @param inputStream
     * @param xslUrl
     * @return
     * @throws TransformerConfigurationException
     * @throws TransformerException
     * @throws UnsupportedEncodingException
     */
    public static InputStream transformXML(InputStream inputStream, String xslUrl) throws TransformerConfigurationException, TransformerException, UnsupportedEncodingException {
        StringWriter writer = new StringWriter();
        StreamResult streamResult = new StreamResult(writer);
        TransformerFactory transformFactory = TransformerFactory.newInstance();
        Transformer transformer = transformFactory.newTransformer(new StreamSource(new File(xslUrl)));
        transformer.transform(new StreamSource(inputStream), streamResult);
        return new ByteArrayInputStream(writer.toString().getBytes("UTF-8"));
    }
}