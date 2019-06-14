/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import constant.UrlConstant;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author ahhun
 */
public class HTMLRequestUtils {
    private static final String POST = "POST";
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String CONTENT_TYPE_FORM = "multipart/form-data";

    public static String getDataFromGetRequest(String sourceURL)
            throws MalformedURLException, FileNotFoundException, IOException {
        URL website = new URL(sourceURL);
        return getStringFromInputStream(website.openStream());
    }
    
    public static String getDataFromPostRequest(String sourceURL, HashMap params)
            throws MalformedURLException, FileNotFoundException, IOException {
        
        String paramsStr = "";
        if (params != null) {
            boolean first = true;
            for (Object key : params.keySet().toArray()) {
                Object value = params.get(key);
                if (first) {
                    paramsStr = "?";
                } else {
                    paramsStr += "&";
                }
                paramsStr += key + "=" + value;
                first = false;
            }
        }
        
        URL url = new URL(sourceURL + paramsStr);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod(POST);
        con.setRequestProperty(CONTENT_TYPE, CONTENT_TYPE_FORM);
        
        return getStringFromInputStream(con.getInputStream());
    }
    
    private static String getStringFromInputStream(InputStream is) {
        String result = "";
        
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
            result = br.lines().collect(Collectors.joining(" "));
        } catch (IOException ex) {
            Logger.getLogger(HTMLRequestUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }
    
    public static String getLocation(String name) {
        String address = null;
        
        try {
            String mapApi = UrlConstant.GOOGLE_MAPS_TEXT_SEARCH + "query="
                    + URLEncoder.encode(name, "UTF-8") + "&key=" + UrlConstant.GOOGLE_MAPS_API_KEY;
            String mapApiString = getDataFromGetRequest(mapApi);
            
            XPath xpath = XMLParser.getXPath();
            Document mapApiDoc = XMLParser.parseStringToDOM(mapApiString);
            
            String exp = "//location/lat";
            String lat = ((String) xpath.evaluate(exp, mapApiDoc, XPathConstants.STRING)).trim();
            exp = "//location/lng";
            String lng = ((String) xpath.evaluate(exp, mapApiDoc, XPathConstants.STRING)).trim();
            exp = "//formatted_address";
            String formattedAddress = ((String) xpath.evaluate(exp, mapApiDoc, XPathConstants.STRING)).trim();
            
            address = lat.length() > 0 ? lat + ";" + lng + ";" + formattedAddress : null;
            
            System.out.println(address);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(HTMLRequestUtils.class.getName()).log(Level.SEVERE, "", ex);
        } catch (IOException ex) {
            Logger.getLogger(HTMLRequestUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(HTMLRequestUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(HTMLRequestUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (XPathExpressionException ex) {
            Logger.getLogger(HTMLRequestUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return address;
    }
}
