/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duongpth.test.crawl;

import duongpth.daos.RecipeDAO;
import duongpth.jaxbs.Ingredient;
import duongpth.jaxbs.Ingredients;
import duongpth.jaxbs.Recipe;
import duongpth.jaxbs.Recipes;
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

    public static void crawlPageRecipe(String url, int numPage) throws MalformedURLException, IOException, XMLStreamException, TransformerException, JAXBException {
        String line, lines = "";
        String start = "<div class=\"box-recipe_bottom\">";
        String end = "Tiếp theo</a></div> </div> </div>";
        String nextPage = "";
        String xslFileLinks = "web/WEB-INF/xsl/recipeLink.xsl";
        MarkerDTO markerHome = new MarkerDTO();
        markerHome.setEnd(end);
        markerHome.setStart(start);
        markerHome.setIncluded(true);
        String homePage = "http://www.amthuc365.vn";
        String crawLink = CrawlUtil.normalizeLink(homePage, url);
        start = "<div class=\"box-video_info\">";
        end = "<div class=\"comments mt20\"";
        MarkerDTO markerDetail = new MarkerDTO();
        markerDetail.setEnd(end);
        markerDetail.setStart(start);
        markerDetail.setIncluded(false);
        String xslFileDetail = "web/WEB-INF/xsl/recipeDetail.xsl";

        InputStream stream = null;

        List<Recipe> list = null;
        Recipes recipes = null;
        Recipe ing = null;

        BufferedWriter writer = new BufferedWriter(new FileWriter("page.xml"));
        do {
            crawLink = CrawlUtil.normalizeLink(homePage, crawLink);
            System.out.println("Crawling " + crawLink);
            stream = CrawlUtil.getDataFromWeb(crawLink, markerHome);
            stream = CrawlUtil.processWellForm(stream);
            stream = CrawlUtil.transformXML(stream, xslFileLinks);
            stream.reset();

//            stream.reset();
            recipes = JAXBUtil.unmarshalling(stream, new Recipes());
            list = recipes.getRecipe();

            nextPage = recipes.getNextpage();
            if (nextPage != null && !nextPage.equals("")) {
                crawLink = nextPage;
            }

            for (Recipe recipe : list) {
                crawLink = CrawlUtil.normalizeLink(homePage, recipe.getLink());
                System.out.println("Crawling " + recipe.getLink());
//                crawLink = CrawlUtil.normalizeLink(homePage, "/cong-thuc/4796-cach-nau-canh-kim-chi-han-quoc-nong-hoi-thom-ngon.html");
                stream = CrawlUtil.getDataFromWeb(crawLink, markerDetail);
                stream = CrawlUtil.processWellForm(stream);
                stream = CrawlUtil.transformXML(stream, xslFileDetail);
                stream.reset();

                try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
                    while ((line = reader.readLine()) != null) {
                        lines += line;
                    }
                }
                writer.write(lines);
//                lines += recipe.getLink();
            }
            writer.close();
            System.out.println("next page: " + nextPage);
            numPage++;
        } while (nextPage != null && !nextPage.equals("") && numPage < 1);

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
        System.out.println("nextPage " + ingredients.getNextpage());
        List<Ingredient> list = ingredients.getIngredient();
        Ingredient ingredient = null;
        String nextPage = "";

        do {
            nextPage = ingredients.getNextpage();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
                while ((line = reader.readLine()) != null) {
                    lines += line;
                }
            }
            numPage++;
            System.out.println("next page: " + nextPage);
        } while (nextPage != null && !nextPage.equals("") && numPage < 3);
        BufferedWriter writer = new BufferedWriter(new FileWriter("page.xml"));
        writer.write(lines);
        writer.close();
    }

    public static void main(String[] args) {
        String homeUrl = "http://www.amthuc365.vn/cong-thuc/105-thanh-phan";
//        String homeUrl = "http://nkfood.vn/cua-hang";
        try {
            do {
                crawlPageRecipe(homeUrl, 1);
//                crawlPageFood(homeUrl, 1);
            } while (false);
        } catch (IOException | XMLStreamException | TransformerException | JAXBException ex) {
            Logger.getLogger(TestCrawl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
