/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duongpth.test.crawl;

import duongpth.daos.IngredientDAO;
import duongpth.daos.RecipeDAO;
import duongpth.jaxbs.Ingredient;
import duongpth.jaxbs.Ingredients;
import duongpth.jaxbs.Recipe;
import duongpth.jaxbs.Recipes;
import duongpth.persistences.IngredientBLO;
import duongpth.utils.CrawlUtil;
import duongpth.utils.JAXBUtil;
import duongpth.utils.MarkerDTO;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerException;

/**
 *
 * @author dragn
 */
public class TestCrawl {

    public static void crawlPageRecipe(String url) throws MalformedURLException, IOException, XMLStreamException, TransformerException, JAXBException, SQLException, NamingException, ClassNotFoundException {
        String lines = "";
        String start = "<div class=\"box-recipe_bottom\">";
        String end = "Tiếp theo</a></div> </div> </div>";
        String nextPage = "";
        String xslFileLinks = "web/WEB-INF/xsl/recipeLink.xsl";
        MarkerDTO markerHome = new MarkerDTO();
        markerHome.setEnd(end);
        markerHome.setStart(start);
        markerHome.setIncluded(true);
        String homePage = "http://www.amthuc365.vn/";
        String crawledLink = CrawlUtil.normalizeLink(homePage, url);
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
        Recipe tmp = null;
        RecipeDAO dao = new RecipeDAO();
        int index;
        do {
            crawledLink = CrawlUtil.normalizeLink(homePage, crawledLink);
            System.out.println("Crawling " + crawledLink);
            stream = CrawlUtil.crawlFromLink(crawledLink, markerHome);
            if (stream != null) {
                stream = CrawlUtil.transformXML(stream, xslFileLinks);
                stream.reset();

                recipes = JAXBUtil.unmarshalling(stream, new Recipes());
                list = recipes.getRecipe();

                for (Recipe recipe : list) {
                    crawledLink = CrawlUtil.normalizeLink(homePage, recipe.getLink());
                    System.out.println("Crawling " + crawledLink);
                    stream = CrawlUtil.crawlFromLink(crawledLink, markerDetail);
                    if (stream != null) {
                        stream.reset();
                        stream = CrawlUtil.transformXML(stream, xslFileDetail);
                        tmp = JAXBUtil.unmarshalling(stream, new Recipe());
                        tmp.setLink(crawledLink);
                        tmp.setId(recipe.getId());
                        index = list.indexOf(recipe);
                        list.set(index, tmp);
                        dao.insert(tmp);
                    }
                }
            }
            nextPage = recipes.getNextpage();
            if (nextPage != null && !nextPage.equals("")) {
                crawledLink = nextPage;
            }
            System.out.println("next page: " + nextPage);
        } while (nextPage != null && !nextPage.equals(""));
    }

    public static void crawlPageFood(String url) throws MalformedURLException, IOException, XMLStreamException, TransformerException, JAXBException {
        String homePage = "http://nkfood.vn/cua-hang";
        String crawledLink = CrawlUtil.normalizeLink(homePage, url);
        InputStream stream = null;
        Ingredients ingredients = null;
        List<Ingredient> list = null;
        String nextPage = "";
        Ingredient tmp = null;
        int index;

        String start = "<main id=\"main\" class=\"site-main\" role=\"main\">";
        String end = "</main><!-- #main -->";
        MarkerDTO markerHome = new MarkerDTO();
        markerHome.setEnd(end);
        markerHome.setStart(start);
        markerHome.setIncluded(true);
        String xslFileLinks = "web/WEB-INF/xsl/ingredientLink.xsl";

        start = "<div class=\"summary entry-summary\">";
        end = "</div><!-- .summary -->";
        MarkerDTO markerDetail = new MarkerDTO();
        markerDetail.setEnd(end);
        markerDetail.setStart(start);
        markerDetail.setIncluded(true);
        String xslFileDetail = "web/WEB-INF/xsl/ingredientDetail.xsl";

        IngredientBLO blo = new IngredientBLO();
        do {
            System.out.println("Crawling " + crawledLink);
            stream = CrawlUtil.crawlFromLink(crawledLink, markerHome);
            if (stream != null) {

                stream = CrawlUtil.transformXML(stream, xslFileLinks);
                stream.reset();

                ingredients = JAXBUtil.unmarshalling(stream, new Ingredients());
                list = ingredients.getIngredient();

                for (Ingredient ingredient : list) {
                    crawledLink = CrawlUtil.normalizeLink(homePage, ingredient.getLink());
                    System.out.println("Crawling " + crawledLink);
                    stream = CrawlUtil.crawlFromLink(crawledLink, markerDetail);
                    if (stream != null) {
                        stream = CrawlUtil.transformXML(stream, xslFileDetail);
                        stream.reset();

                        tmp = JAXBUtil.unmarshalling(stream, new Ingredient());
                        index = list.indexOf(ingredient);
                        tmp.setLink(crawledLink);
                        tmp.setImage(ingredient.getImage());
                        list.set(index, tmp);
                        blo.insert(tmp);
                    }
                }
                nextPage = ingredients.getNextpage();
            }
            if (nextPage != null && !nextPage.equals("")) {
                crawledLink = nextPage;
            }
        } while (nextPage != null && !nextPage.equals(""));
    }

    public static void main(String[] args) {
        String ingredientUrl = "http://nkfood.vn/cua-hang";
        String recipeUrl = "http://www.amthuc365.vn/cong-thuc/105-thanh-phan";
        try {
            do {
                crawlPageFood(ingredientUrl);
                crawlPageRecipe(recipeUrl);
            } while (false);
        } catch (IOException | XMLStreamException | TransformerException | JAXBException | SQLException | NamingException | ClassNotFoundException ex) {
            Logger.getLogger(TestCrawl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
