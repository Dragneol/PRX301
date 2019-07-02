
package duongpth.jaxbs;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for subdomains complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="subdomains">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="subdomain" type="{http://www.ezco.com/XMLSchema/ezco}subdomain" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "subdomains", propOrder = {
    "subdomain"
})
public class Subdomains {

    @XmlElement(required = true)
    protected List<Subdomain> subdomain;

    /**
     * Gets the value of the subdomain property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the subdomain property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSubdomain().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Subdomain }
     * 
     * 
     */
    public List<Subdomain> getSubdomain() {
        if (subdomain == null) {
            subdomain = new ArrayList<Subdomain>();
        }
        return this.subdomain;
    }

}
