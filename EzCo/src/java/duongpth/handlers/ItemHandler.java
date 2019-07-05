/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duongpth.handlers;

import duongpth.daos.RecipeCateDAO;
import duongpth.daos.RecipeDAO;
import duongpth.jaxbs.Marker;
import duongpth.jaxbs.Recipe;
import duongpth.jaxbs.Recipes;
import duongpth.jaxbs.Website;
import duongpth.utils.CrawlUtil;
import duongpth.utils.JAXBUtil;
import duongpth.utils.LogUtil;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerException;

/**
 *
 * @author dragn
 */
public class ItemHandler implements Serializable {

    private ServletContext conext;

    public ItemHandler(ServletContext conext) {
        this.conext = conext;
    }

    public void crawlRecipe(Website recipeSite, String homePage, String subDomain, int categoryId) throws JAXBException, FileNotFoundException, XMLStreamException, XMLStreamException, IOException, TransformerException {
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
        String xslFileLinks = conext.getRealPath("/") + markerHome.getXsl();
        String xslFileDetail = conext.getRealPath("/") + markerDetail.getXsl();

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
}
