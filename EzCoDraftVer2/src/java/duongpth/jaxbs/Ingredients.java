
package duongpth.jaxbs;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ingredient" type="{http://www.ezco.com/XMLSchema/ezco}ingredient" maxOccurs="unbounded"/>
 *         &lt;element name="nextpage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "ingredient",
    "nextpage"
})
@XmlRootElement(name = "ingredients")
public class Ingredients {

    @XmlElement(required = true)
    protected List<Ingredient> ingredient;
    protected String nextpage;

    /**
     * Gets the value of the ingredient property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ingredient property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIngredient().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Ingredient }
     * 
     * 
     */
    public List<Ingredient> getIngredient() {
        if (ingredient == null) {
            ingredient = new ArrayList<Ingredient>();
        }
        return this.ingredient;
    }

    /**
     * Gets the value of the nextpage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNextpage() {
        return nextpage;
    }

    /**
     * Sets the value of the nextpage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNextpage(String value) {
        this.nextpage = value;
    }

}