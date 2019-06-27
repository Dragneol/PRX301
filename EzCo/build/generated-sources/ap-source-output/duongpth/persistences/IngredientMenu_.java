package duongpth.persistences;

import duongpth.persistences.Recipe;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-27T18:59:52")
@StaticMetamodel(IngredientMenu.class)
public class IngredientMenu_ { 

    public static volatile SingularAttribute<IngredientMenu, String> unit;
    public static volatile SingularAttribute<IngredientMenu, String> name;
    public static volatile SingularAttribute<IngredientMenu, Integer> id;
    public static volatile SingularAttribute<IngredientMenu, Integer> quantitive;
    public static volatile SingularAttribute<IngredientMenu, Recipe> recipeID;

}