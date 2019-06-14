/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testcrawler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 *
 * @author dragn
 */
public class AmThuc {

    private static URL url;

    public static URL getUrl() {
        return url;
    }

    public static void setUrl(URL url) {
        AmThuc.url = url;
    }

    public static void crawl() throws IOException, IOException {
//        System.out.println("Amthuc content" + url.getContent());
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
        String inputLine;
        String htmlFile = null;
        BufferedWriter writer = new BufferedWriter(new FileWriter("amthuc.html"));
        while ((inputLine = in.readLine()) != null) {
            htmlFile += inputLine;
        }
//        String begin = "<div class=\"box-video_info\">";
//        String end = "<!--end box-recipe_content-->";
//        String result = htmlFile.substring(htmlFile.indexOf(begin) + begin.length(), htmlFile.indexOf(end));
//        String result = htmlFile.substring(htmlFile.indexOf(begin) + begin.length(), htmlFile.indexOf(end));
        writer.write(htmlFile);
//        writer.write(URLDecoder.decode(result, "UTF-8"));
        writer.close();
        in.close();
    }
}
