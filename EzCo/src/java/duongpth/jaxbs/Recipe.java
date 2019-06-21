
package duongpth.jaxbs;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for recipe complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="recipe">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="link" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="image" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ration" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="preparetime" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="cookingtime" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="ingredientmenu" type="{http://www.ezco.com/XMLSchema/ezco}ingredientmenu" minOccurs="0"/>
 *         &lt;element name="instructionmenu" type="{http://www.ezco.com/XMLSchema/ezco}instructionmenu" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "recipe", propOrder = {
    "id",
    "title",
    "link",
    "image",
    "description",
    "ration",
    "preparetime",
    "cookingtime",
    "ingredientmenu",
    "instructionmenu"
})
public class Recipe {

    protected BigInteger id;
    protected String title;
    protected String link;
    protected String image;
    protected String description;
    protected BigInteger ration;
    protected BigInteger preparetime;
    protected BigInteger cookingtime;
    protected Ingredientmenu ingredientmenu;
    protected Instructionmenu instructionmenu;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setId(BigInteger value) {
        this.id = value;
    }

    /**
     * Gets the value of the title property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the value of the title property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitle(String value) {
        this.title = value;
    }

    /**
     * Gets the value of the link property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLink() {
        return link;
    }

    /**
     * Sets the value of the link property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLink(String value) {
        this.link = value;
    }

    /**
     * Gets the value of the image property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImage() {
        return image;
    }

    /**
     * Sets the value of the image property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImage(String value) {
        this.image = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the ration property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getRation() {
        return ration;
    }

    /**
     * Sets the value of the ration property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setRation(BigInteger value) {
        this.ration = value;
    }

    /**
     * Gets the value of the preparetime property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getPreparetime() {
        return preparetime;
    }

    /**
     * Sets the value of the preparetime property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setPreparetime(BigInteger value) {
        this.preparetime = value;
    }

    /**
     * Gets the value of the cookingtime property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getCookingtime() {
        return cookingtime;
    }

    /**
     * Sets the value of the cookingtime property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCookingtime(BigInteger value) {
        this.cookingtime = value;
    }

    /**
     * Gets the value of the ingredientmenu property.
     * 
     * @return
     *     possible object is
     *     {@link Ingredientmenu }
     *     
     */
    public Ingredientmenu getIngredientmenu() {
        return ingredientmenu;
    }

    /**
     * Sets the value of the ingredientmenu property.
     * 
     * @param value
     *     allowed object is
     *     {@link Ingredientmenu }
     *     
     */
    public void setIngredientmenu(Ingredientmenu value) {
        this.ingredientmenu = value;
    }

    /**
     * Gets the value of the instructionmenu property.
     * 
     * @return
     *     possible object is
     *     {@link Instructionmenu }
     *     
     */
    public Instructionmenu getInstructionmenu() {
        return instructionmenu;
    }

    /**
     * Sets the value of the instructionmenu property.
     * 
     * @param value
     *     allowed object is
     *     {@link Instructionmenu }
     *     
     */
    public void setInstructionmenu(Instructionmenu value) {
        this.instructionmenu = value;
    }

}
