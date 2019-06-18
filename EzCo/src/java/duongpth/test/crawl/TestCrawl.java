/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duongpth.test.crawl;

import duongpth.jaxbs.Ingredient;
import duongpth.jaxbs.Ingredients;
import duongpth.utils.CrawlUtil;
import duongpth.utils.JAXBUtil;
import duongpth.utils.MarkerDTO;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerException;

/**
 *
 * @author dragn
 */
public class TestCrawl {

    public static void crawlPageRecipe(String url, int numPage) throws MalformedURLException, IOException, XMLStreamException, TransformerException {
//        String crawLink = url + "/trang-" + numPage + "/";
//        InputStream stream = CrawlUtil.getDataFromWeb(crawLink);
//        stream = CrawlUtil.processWellForm(stream);
//        String xslFile = "web/WEB-INF/xsl/recipeLink.xsl";
//        stream = CrawlUtil.transformXML(stream, xslFile);
//        String line, lines = "";
//        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
//            while ((line = reader.readLine()) != null) {
//                lines += line;
//            }
//        }
//
//        BufferedWriter writer = new BufferedWriter(new FileWriter("page.xml"));
//        writer.write(lines);
//        writer.close();

    }

    public static void crawlPageFood(String url, int numPage) throws MalformedURLException, IOException, XMLStreamException, TransformerException, JAXBException {
        String line, lines = "";
        String start = "<main id=\"main\" class=\"site-main\" role=\"main\">";
        String end = "</main><!-- #main -->";

        MarkerDTO marker = new MarkerDTO();
        marker.setEnd(end);
        marker.setStart(start);
        InputStream stream = CrawlUtil.getDataFromWeb(url, marker);
        stream = CrawlUtil.processWellForm(stream);
        String xslFile = "web/WEB-INF/xsl/ingredientLink.xsl";
        stream = CrawlUtil.transformXML(stream, xslFile);
        stream.reset();

        Ingredients ingredients = JAXBUtil.unmarshalling(stream, new Ingredients());
        System.out.println("nextPage " + ingredients.getNextPage());
        List<Ingredient> list = ingredients.getIngredient();
        Ingredient ingredient = null;
        String nextPage = "";
        do {
            nextPage = ingredients.getNextPage();
            start = "<div class=\"summary entry-summary\">";
            end = "</div><!-- .summary -->";
            marker.setEnd(end);
            marker.setStart(start);
            xslFile = "web/WEB-INF/xsl/ingredientDetail.xsl";
            Ingredient tmp = null;
            for (int i = 0; i < list.size(); i++) {
                ingredient = list.get(i);
                System.out.println("Crawling" + ingredient.getLink());
                stream = CrawlUtil.getDataFromWeb(ingredient.getLink(), marker);
                stream = CrawlUtil.processWellForm(stream);
                stream = CrawlUtil.transformXML(stream, xslFile);
                stream.reset();

                tmp = JAXBUtil.unmarshalling(stream, new Ingredient());
                ingredient.setName(tmp.getName());
                ingredient.setOldid(tmp.getOldid());
                ingredient.setUnit(tmp.getUnit());
                ingredient.setPrice(tmp.getPrice());
                lines += ingredient.toString() + "\n";
            }
            System.out.println("nextPage " + nextPage);
        } while (nextPage != null && !nextPage.equals(""));
        BufferedWriter writer = new BufferedWriter(new FileWriter("page.xml"));
        writer.write(lines);
        writer.close();
    }

    public static void main(String[] args) {
//        String homeUrl = "http://www.amthuc365.vn/cong-thuc/105-thanh-phan";
        String homeUrl = "http://nkfood.vn/cua-hang";
        try {
            do {
                crawlPageFood(homeUrl, 1);
            } while (false);
        } catch (IOException | XMLStreamException | TransformerException | JAXBException ex) {
            Logger.getLogger(TestCrawl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
