/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duongpth.utils;

import com.sun.xml.internal.stream.events.EndElementEvent;
import duongpth.jaxbs.Marker;
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
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    public static InputStream crawlFromLink(String crawledLink, Marker marker) {
        InputStream stream = null;
        try {
            stream = getDataFromWeb(crawledLink, marker);
            stream = processWellForm(stream);
        } catch (IOException | XMLStreamException ex) {
            Logger.getLogger(CrawlUtil.class.getName()).log(Level.SEVERE, null, ex);
            stream = null;
        } catch (Exception e) {
            Logger.getLogger(CrawlUtil.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            return stream;
        }
    }

    

    public static String normalizeLink(String homePage, String url) {
        String s = "";

        if (!homePage.startsWith("http://")) {
            s = "http://" + homePage;
        } else {
            s = homePage;
        }

        if (url.startsWith(s)) {
            s = url;
        } else {
            s += url;
        }
        if (!s.endsWith(".html") && s.charAt(s.length() - 1) != '/') {
            s += '/';
        }
        return s;
    }

    /**
     * Crawl from url and return InputStream
     *
     * @param url
     * @return
     * @throws MalformedURLException
     * @throws IOException
     * @throws XMLStreamException
     */
    private static InputStream getDataFromWeb(String url, Marker marker) throws MalformedURLException, IOException, XMLStreamException {
        URL urlink = new URL(url);
        HttpURLConnection con = (HttpURLConnection) urlink.openConnection();
        InputStream inputStream = con.getInputStream();
        String content = processStreamToString(inputStream);
        content = cutContent(content, marker);
        return new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8));
    }

    private static String processStreamToString(InputStream inputStream) throws IOException {
        StringBuilder content;
        try (InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8); BufferedReader br = new BufferedReader(reader)) {
            content = new StringBuilder();
            String line = null;
            while ((line = br.readLine()) != null) {
                line = VietnameseUtil.decode(line);
                content.append(line);
            }
        }
        return content.toString().trim();
    }

    private static String cutContent(String content, Marker marker) {
        String start = marker.getStart();
        String end = marker.getEnd();
        if (marker.isIncluded()) {
            content = content.substring(content.indexOf(start), content.indexOf(end) + end.length());
        } else {
            content = content.substring(content.indexOf(start), content.indexOf(end));
        }
        content = "<root>" + content + "</root>";
        content = VietnameseUtil.decode(content);
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
    private static InputStream processWellForm(InputStream inputStream) throws XMLStreamException, UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        XMLInputFactory factory = XMLInputFactory.newFactory();
        XMLEventReader reader = factory.createXMLEventReader(inputStream, StandardCharsets.UTF_8.name());

        int tagMarker = 0;

        while (reader.hasNext()) {
            XMLEvent event = null;
            try {
                event = reader.nextEvent();
            } catch (XMLStreamException ex) {
//                ex.printStackTrace();
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

}
