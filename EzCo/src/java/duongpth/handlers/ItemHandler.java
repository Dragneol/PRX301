/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duongpth.handlers;

import duongpth.daos.IngredientDAO;
import duongpth.daos.RecipeCateDAO;
import duongpth.daos.RecipeDAO;
import duongpth.jaxbs.Categories;
import duongpth.jaxbs.Ingredient;
import duongpth.jaxbs.Ingredients;
import duongpth.jaxbs.Marker;
import duongpth.jaxbs.Recipe;
import duongpth.jaxbs.Recipes;
import duongpth.jaxbs.Website;
import duongpth.utils.CrawlUtil;
import duongpth.utils.JAXBUtil;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerException;

/**
 *
 * @author dragn
 */
public class ItemHandler implements Serializable {

    private ServletContext context;

    public ItemHandler(ServletContext context) {
        this.context = context;
    }

    public List<Recipe> crawlRecipeAtOnce(Website recipeSite, List<Recipe> finalResult, String homePage, String subDomain, int categoryId) throws JAXBException, FileNotFoundException, XMLStreamException, XMLStreamException, IOException, TransformerException, Exception {
        InputStream stream = null;
        List<Recipe> list = null;
        Recipes recipes = null;
        Recipe tmp = null;
        RecipeDAO recipeDAO = new RecipeDAO();
        RecipeCateDAO cateDAO = new RecipeCateDAO();
        int index;

        String nextPage = "";
        String crawledLink = CrawlUtil.normalizeLink(homePage, subDomain);

        Marker markerHome = recipeSite.getMarkers().getHome();
        Marker markerDetail = recipeSite.getMarkers().getDetail();
        String xslFileLinks = context.getRealPath("/") + markerHome.getXsl();
        String xslFileDetail = context.getRealPath("/") + markerDetail.getXsl();

        do {
            crawledLink = CrawlUtil.normalizeLink(homePage, crawledLink);
            System.out.println("Crawling " + crawledLink);
            stream = CrawlUtil.crawlFromLink(crawledLink, markerHome);
            if (stream != null) {
                stream.reset();
                stream = CrawlUtil.transformXML(stream, xslFileLinks);
                recipes = JAXBUtil.unmarshalling(stream, new Recipes());
                list = recipes.getRecipe();

                for (Recipe recipe : list) {
                    index = contained(finalResult, recipe);
                    if (index != -1) {
                        Categories categories = finalResult.get(index).getCategories();
                        List<Integer> tmpCate = categories.getCategory();
                        if (!tmpCate.contains(categoryId)) {
                            tmpCate.add(categoryId);
                        }
                    } else {
                        crawledLink = CrawlUtil.normalizeLink(homePage, recipe.getLink());
                        System.out.println("Crawling " + recipe.getLink());
                        stream = CrawlUtil.crawlFromLink(crawledLink, markerDetail);
                        if (stream != null) {
                            stream.reset();
                            stream = CrawlUtil.transformXML(stream, xslFileDetail);
                            tmp = JAXBUtil.unmarshalling(stream, new Recipe());
                            //edit wrong data with default
                            recipe.setLink(crawledLink.trim());
                            tmp = DataErrorHandler.normalizeRecipe(tmp, recipe);
                            Categories categories = new Categories();
                            List<Integer> tmpCate = categories.getCategory();
                            tmpCate.add(categoryId);
                            tmp.setCategories(categories);
                            finalResult.add(tmp);
                        }
                    }
                }
            }
            try {
                nextPage = recipes.getNextpage();
            } catch (NullPointerException e) {
                nextPage = "";
            }

            if (!nextPage.equals("")) {
                crawledLink = nextPage;
            }
        } while (!nextPage.equals(""));
        return finalResult;
    }

    public void insertRecipesAtOnce(List<Recipe> finalResult) throws NamingException, SQLException, ClassNotFoundException, Exception {
        RecipeCateDAO repCatDAO = new RecipeCateDAO();
        RecipeDAO repDAO = new RecipeDAO();

        for (Recipe recipe : finalResult) {
            repDAO.insert(recipe);
            for (Integer cateRepID : recipe.getCategories().getCategory()) {
                repCatDAO.insertCateOfRecipe(recipe.getId(), cateRepID);
            }
        }
    }

    public void crawlRecipeIntoDBInterative(Website recipeSite, String homePage, String subDomain, int categoryId) throws JAXBException, FileNotFoundException, XMLStreamException, XMLStreamException, IOException, TransformerException , Exception{
        InputStream stream = null;
        List<Recipe> list = null;
        Recipes recipes = null;
        Recipe tmp = null;
        RecipeDAO recipeDAO = new RecipeDAO();
        RecipeCateDAO cateDAO = new RecipeCateDAO();
        int index;

        String nextPage = "";
        String crawledLink = CrawlUtil.normalizeLink(homePage, subDomain);

        Marker markerHome = recipeSite.getMarkers().getHome();
        Marker markerDetail = recipeSite.getMarkers().getDetail();
        String xslFileLinks = context.getRealPath("/") + markerHome.getXsl();
        String xslFileDetail = context.getRealPath("/") + markerDetail.getXsl();

        do {
            crawledLink = CrawlUtil.normalizeLink(homePage, crawledLink);
            System.out.println("Crawling " + crawledLink);
            stream = CrawlUtil.crawlFromLink(crawledLink, markerHome);
            if (stream != null) {
                stream.reset();
                stream = CrawlUtil.transformXML(stream, xslFileLinks);
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
                        //edit wrong data with default
                        recipe.setLink(crawledLink.trim());
                        tmp = DataErrorHandler.normalizeRecipe(tmp, recipe);
                        index = list.indexOf(recipe);
                        list.set(index, tmp);

                        try {
                            recipeDAO.insert(tmp);
                            cateDAO.insertCateOfRecipe(tmp.getId(), categoryId);
                        } catch (Exception e) {
                            Logger.getLogger(ItemHandler.class.getName()).log(Level.SEVERE, null, e);
                        }

                    }
                }
            }
            try {
                nextPage = recipes.getNextpage();
            } catch (NullPointerException e) {
                nextPage = "";
            }

            if (!nextPage.equals("")) {
                crawledLink = nextPage;
            }
        } while (!nextPage.equals(""));
    }

    private int contained(List<Recipe> recipes, Recipe r) {
        for (Recipe recipe : recipes) {
            if (r.equals(recipe)) {
                return recipes.indexOf(recipe);
            }
        }
        return -1;
    }

    public void crawlIngredient(Website ingredientSite, String homePage, String subDomain, int categoryId) throws TransformerException, UnsupportedEncodingException, JAXBException, FileNotFoundException, XMLStreamException, IOException, NamingException, SQLException, SQLException, ClassNotFoundException {
        String nextPage = "";
        Marker markerHome = ingredientSite.getMarkers().getHome();
        Marker markerDetail = ingredientSite.getMarkers().getDetail();
        String crawledLink = CrawlUtil.normalizeLink(homePage, subDomain);

        String xslFileLinks = context.getRealPath("/") + markerHome.getXsl();
        String xslFileDetail = context.getRealPath("/") + markerDetail.getXsl();

        InputStream stream = null;
        List<Ingredient> list = null;
        Ingredients ingredients = null;
        Ingredient tmp = null;
        IngredientDAO dao = new IngredientDAO();
        int index;
        do {
            crawledLink = CrawlUtil.normalizeLink(homePage, crawledLink);
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
                        //edit wrong data with default
                        tmp.setLink(crawledLink.trim());                        
                        tmp = DataErrorHandler.normalizeIngredient(tmp, ingredient);                        
                        list.set(index, tmp);
                        dao.insert(tmp);
                    }
                }
                try {
                    nextPage = ingredients.getNextpage();
                } catch (NullPointerException e) {
                    nextPage = "";
                }
            }
            if (!nextPage.equals("")) {
                crawledLink = DataErrorHandler.normalizeIngredientsLink(nextPage);
            }
        } while (!nextPage.equals(""));

    }
}
