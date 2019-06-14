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

/**
 *
 * @author dragn
 */
public class NguyenHaFood {

    private static URL url;

    public static URL getUrl() {
        return url;
    }

    public static void setUrl(URL url) {
        NguyenHaFood.url = url;
    }

    public static void crawl() throws IOException, IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
        String inputLine;
        String htmlFile = null;
        BufferedWriter writer = new BufferedWriter(new FileWriter("nguyenhafood.html"));
        while ((inputLine = in.readLine()) != null) {
            htmlFile += inputLine;
        }
        String begin = "<div class=\"tab-container\">";
        String end = "<div class=\"single-box\">";
        writer.write(htmlFile.substring(htmlFile.indexOf(begin) + begin.length(), htmlFile.indexOf(end)));
//        writer.write(htmlFile);
        writer.close();
        in.close();
    }
}
