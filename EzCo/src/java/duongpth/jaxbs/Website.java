
package duongpth.jaxbs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for website complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="website">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="homeurl" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="subdomains" type="{http://www.ezco.com/XMLSchema/ezco}subdomains"/>
 *         &lt;element name="markers" type="{http://www.ezco.com/XMLSchema/ezco}markers"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "website", propOrder = {
    "homeurl",
    "subdomains",
    "markers"
})
public class Website {

    @XmlElement(required = true)
    protected String homeurl;
    @XmlElement(required = true)
    protected Subdomains subdomains;
    @XmlElement(required = true)
    protected Markers markers;

    /**
     * Gets the value of the homeurl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHomeurl() {
        return homeurl;
    }

    /**
     * Sets the value of the homeurl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHomeurl(String value) {
        this.homeurl = value;
    }

    /**
     * Gets the value of the subdomains property.
     * 
     * @return
     *     possible object is
     *     {@link Subdomains }
     *     
     */
    public Subdomains getSubdomains() {
        return subdomains;
    }

    /**
     * Sets the value of the subdomains property.
     * 
     * @param value
     *     allowed object is
     *     {@link Subdomains }
     *     
     */
    public void setSubdomains(Subdomains value) {
        this.subdomains = value;
    }

    /**
     * Gets the value of the markers property.
     * 
     * @return
     *     possible object is
     *     {@link Markers }
     *     
     */
    public Markers getMarkers() {
        return markers;
    }

    /**
     * Sets the value of the markers property.
     * 
     * @param value
     *     allowed object is
     *     {@link Markers }
     *     
     */
    public void setMarkers(Markers value) {
        this.markers = value;
    }

}
