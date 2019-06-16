/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duongpth.test.crawl;

import duongpth.utils.CrawlUtil;
import duongpth.utils.EncodeUtil;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerException;

/**
 *
 * @author dragn
 */
public class TestCrawl {

    public static void crawlPageRecipe(String url, int numPage) throws MalformedURLException, IOException, XMLStreamException, TransformerException {
        String start = "<div class=\"box-recipe_bottom\">";
        String end = "Tiếp theo</a></div> </div> </div>";
        String crawLink = url + "/trang-" + numPage + "/";
        InputStream stream = CrawlUtil.getDataFromWeb(crawLink);
        stream = CrawlUtil.processWellForm(stream);
        String xslFile = "web\\WEB-INF\\xsl\\recipeLink.xsl";
        stream = CrawlUtil.transformXML(stream, xslFile);
        String line, lines = "";
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
            while ((line = reader.readLine()) != null) {
                lines += line;
            }
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter("page.xml"));
        writer.write(lines);
        writer.close();

    }

    public static void crawlPageFood(String url, int numPage) throws MalformedURLException, IOException {
        String crawLink = url + "/?paged=" + numPage;
        URL link = new URL(crawLink);
        String line, lines = "";
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(link.openStream(), "UTF8"))) {
            while ((line = reader.readLine()) != null) {
                line = line + System.lineSeparator();
                lines += line;
            }
        }
        System.out.println(lines);
    }

    public static void main(String[] args) {
        String homeUrl = "http://www.amthuc365.vn/cong-thuc/105-thanh-phan";
//        String homeUrl = "http://nkfood.vn/cua-hang";
        try {
            for (int page = 1; page < 2; page++) {
                crawlPageRecipe(homeUrl, page);
            }
        } catch (IOException | XMLStreamException | TransformerException ex) {
            Logger.getLogger(TestCrawl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
