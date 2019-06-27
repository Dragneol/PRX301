package duongpth.persistences;

import duongpth.persistences.Recipe;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-27T18:59:52")
@StaticMetamodel(InstructionMenu.class)
public class InstructionMenu_ { 

    public static volatile SingularAttribute<InstructionMenu, Integer> numStep;
    public static volatile SingularAttribute<InstructionMenu, Integer> id;
    public static volatile SingularAttribute<InstructionMenu, String> detail;
    public static volatile SingularAttribute<InstructionMenu, Recipe> recipeID;

}