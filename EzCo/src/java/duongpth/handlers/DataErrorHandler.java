/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duongpth.handlers;

import duongpth.jaxbs.Ingredientdetail;
import duongpth.jaxbs.Ingredientmenu;
import duongpth.jaxbs.Recipe;
import duongpth.utils.VietnameseUtil;
import java.util.List;

/**
 *
 * @author dragn
 */
public class DataErrorHandler {

    /**
     * Set recipe data field from src.link and src.image and src.id
     *
     * @param recipe
     * @param src
     * @return
     */
    public static Recipe normalizeRecipe(Recipe recipe, Recipe src) {
        Recipe r = recipe;
        r.setId(src.getId());
        r.setImage(src.getImage());
        r.setLink(src.getLink());
        if (r.getCookingtime() == 0) {
            r.setCookingtime(30);
        }
        if (r.getDescription().equals("")) {
            r.setDescription("Đang cập nhật");
        }
        if (r.getPreparetime() == 0) {
            r.setPreparetime(5);
        }
        int tmp = r.getRation() % 10;
        if (tmp == 0) {
            tmp = 10;
        }
        r.setRation(tmp);

        r.setIngredientmenu(normalizeIngredientMenu(r.getIngredientmenu()));
        return r;
    }

    public static Ingredientmenu normalizeIngredientMenu(Ingredientmenu menu) {
        List<Ingredientdetail> ingredients = menu.getIngredientdetail();
        for (Ingredientdetail ingredient : ingredients) {
            ingredient.setName(VietnameseUtil.normalizeWords(ingredient.getName().trim()));
            ingredient.setUnit(VietnameseUtil.normalizeWords(ingredient.getUnit().trim()));
        }
        return menu;
    }
} 
