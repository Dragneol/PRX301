/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duongpth.handlers;

import duongpth.jaxbs.Marker;
import java.io.Serializable;
import javax.servlet.ServletContext;

/**
 *
 * @author dragn
 */
public class ItemHandler implements Serializable {

    public static final String INGREDIENTS = "WEB-INF/xsl/ingredientLink.xsl";
    public static final String INGREDIENT_DETAIL = "WEB-INF/xsl/ingredientDetail.xsl";
    public static final String RECIPES = "WEB-INF/xsl/recipeLink.xsl";
    public static final String RECIPE_DETAIL = "WEB-INF/xsl/recipeDetail.xsl";

    private ServletContext context;

    public ItemHandler(ServletContext context) {
        this.context = context;
    }

    public Marker getMarker(String start, String end, boolean included) {
        Marker marker = new Marker();
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
