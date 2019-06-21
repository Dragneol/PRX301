
package duongpth.jaxbs;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the duongpth.jaxbs package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: duongpth.jaxbs
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Recipes }
     * 
     */
    public Recipes createRecipes() {
        return new Recipes();
    }

    /**
     * Create an instance of {@link Recipe }
     * 
     */
    public Recipe createRecipe() {
        return new Recipe();
    }

    /**
     * Create an instance of {@link Ingredientmenu }
     * 
     */
    public Ingredientmenu createIngredientmenu() {
        return new Ingredientmenu();
    }

    /**
     * Create an instance of {@link Instructionmenu }
     * 
     */
    public Instructionmenu createInstructionmenu() {
        return new Instructionmenu();
    }

}
