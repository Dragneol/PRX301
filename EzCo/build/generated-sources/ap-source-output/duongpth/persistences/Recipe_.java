package duongpth.persistences;

import duongpth.persistences.IngredientMenu;
import duongpth.persistences.InstructionMenu;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-27T18:59:52")
@StaticMetamodel(Recipe.class)
public class Recipe_ { 

    public static volatile CollectionAttribute<Recipe, InstructionMenu> instructionMenuCollection;
    public static volatile SingularAttribute<Recipe, Integer> prepareTime;
    public static volatile SingularAttribute<Recipe, Integer> ration;
    public static volatile SingularAttribute<Recipe, String> instruction;
    public static volatile SingularAttribute<Recipe, String> description;
    public static volatile SingularAttribute<Recipe, Integer> id;
    public static volatile SingularAttribute<Recipe, String> title;
    public static volatile CollectionAttribute<Recipe, IngredientMenu> ingredientMenuCollection;
    public static volatile SingularAttribute<Recipe, Integer> cookingTime;

}