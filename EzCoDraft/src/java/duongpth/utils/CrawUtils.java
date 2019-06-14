/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duongpth.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.xml.stream.XMLStreamException;

/**
 *
 * @author dragn
 */
public class CrawUtils implements Serializable {

    public static final String CRAWL_BASE = "WEB-INF\\web-crawl.xml";
    private static String realPath;

    public static void crawl(String contextPath) {
        realPath = contextPath;
//        getConfig();
        List marker = new ArrayList();
        
    }

    public static InputStream getDataFromWeb(String spec) throws MalformedURLException, IOException {
        URL url = new URL(spec);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        InputStream inputStream = connection.getInputStream();
        String content = null;
        return new ByteArrayInputStream(content.getBytes("UTF-8"));
    }

    public static String cutContent(String content, List marker){
        return content;
    }
    
    public static String processContent(InputStream inputStream) throws UnsupportedEncodingException, IOException {
        InputStreamReader reader = new InputStreamReader(inputStream, "UTF-8");
        BufferedReader br = new BufferedReader(reader);
        StringBuilder content = new StringBuilder();
        String line = null;

        while ((line = br.readLine()) != null) {
            content.append(line);
        }
        br.close();
        reader.close();
        return content.toString().trim();
    }
}
