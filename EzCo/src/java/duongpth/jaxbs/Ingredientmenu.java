
package duongpth.jaxbs;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ingredientmenu complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ingredientmenu">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;element name="ingredientdetail" type="{http://www.ezco.com/XMLSchema/ezco}ingredientdetail" maxOccurs="unbounded" minOccurs="0"/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ingredientmenu", propOrder = {
    "ingredientdetail"
})
public class Ingredientmenu {

    protected List<Ingredientdetail> ingredientdetail;

    /**
     * Gets the value of the ingredientdetail property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ingredientdetail property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIngredientdetail().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Ingredientdetail }
     * 
     * 
     */
    public List<Ingredientdetail> getIngredientdetail() {
        if (ingredientdetail == null) {
            ingredientdetail = new ArrayList<Ingredientdetail>();
        }
        return this.ingredientdetail;
    }

}
