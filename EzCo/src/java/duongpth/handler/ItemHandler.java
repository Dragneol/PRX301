/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duongpth.handler;

import duongpth.daos.IngredientDAO;
import duongpth.jaxbs.Ingredient;
import duongpth.jaxbs.Ingredients;
import duongpth.utils.CrawlUtil;
import duongpth.utils.JAXBUtil;
import duongpth.utils.MarkerDTO;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import javax.servlet.ServletContext;

/**
 *
 * @author dragn
 */
public class ItemHandler implements Serializable {

    public static final String INGREDIENTS = "WEB-INF/xsl/ingredientLink.xsl";
    public static final String INGREDIENT_DETAIL = "WEB-INF/xsl/ingredientDetail.xsl";
    public static final String RECIPES = "WEB-INF/xsl/ingredientLink.xsl";
    public static final String RECIPE_DETAIL = "WEB-INF/xsl/ingredientDetail.xsl";

    private ServletContext context;

    public ItemHandler(ServletContext context) {
        this.context = context;
    }

//    public InputStream CrawlItems(String homePage, String crawledLink, MarkerDTO markerHome, MarkerDTO markerDetail, String xslFileLinks, String xslFileDetail) {
//        String nextPage = "";
//        InputStream stream = null;
//        List<Ingredient> list = null;
//        Ingredients ingredients = null;
//        Ingredient ing = null;
//        IngredientDAO dao = new IngredientDAO();
//        int numPage = 1;
//        do {
//            System.out.println("Crawling " + crawledLink);
//            stream = CrawlUtil.crawlFromLink(crawledLink, markerHome);
//            stream = CrawlUtil.transformXML(stream, xslFileLinks);
//            stream.reset();
//
//            ingredients = JAXBUtil.unmarshalling(stream, new Ingredients());
//            list = ingredients.getIngredient();
//
//            for (Ingredient ingredient : list) {
//                crawledLink = CrawlUtil.normalizeLink(homePage, ingredient.getLink());
//                System.out.println("Crawling " + crawledLink);
//                stream = CrawlUtil.crawlFromLink(crawledLink, markerDetail);
//                stream = CrawlUtil.transformXML(stream, xslFileDetail);
//                stream.reset();
//
//                ing = JAXBUtil.unmarshalling(stream, new Ingredient());
//                ingredient.setName(ing.getName());
//                ingredient.setOldid(ing.getOldid());
//                ingredient.setUnit(ing.getUnit());
//                ingredient.setPrice(ing.getPrice());
//
//                dao.insert(ingredient);
//            }
//
//            nextPage = ingredients.getNextpage();
//            if (nextPage != null && !nextPage.equals("")) {
//                crawledLink = nextPage;
//            }
//        } while (nextPage != null && !nextPage.equals(""));
//    }

    public MarkerDTO getMarker(String start, String end, boolean included) {
        MarkerDTO marker = new MarkerDTO();
        marker.setEnd(end);
        marker.setStart(start);
        marker.setIncluded(included);
        return marker;
    }

    public String getIngredients() {
        return context.getRealPath("/") + INGREDIENTS;
    }

    public String getIngredientDetail() {
        return context.getRealPath("/") + INGREDIENT_DETAIL;
    }

    public String getRecipes() {
        return context.getRealPath("/") + RECIPES;
    }

    public String getRecipeDetail() {
        return context.getRealPath("/") + RECIPE_DETAIL;
    }

}
