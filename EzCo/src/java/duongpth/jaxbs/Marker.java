
package duongpth.jaxbs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for marker complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="marker">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="start" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="end" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="included" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="xsl" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "marker", propOrder = {
    "start",
    "end",
    "included",
    "xsl"
})
public class Marker {

    @XmlElement(required = true)
    protected String start;
    @XmlElement(required = true)
    protected String end;
    @XmlElement(required = true)
    protected boolean included;
    @XmlElement(required = true)
    protected String xsl;

    /**
     * Gets the value of the start property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStart() {
        return start;
    }

    /**
     * Sets the value of the start property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStart(String value) {
        this.start = value;
    }

    /**
     * Gets the value of the end property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnd() {
        return end;
    }

    /**
     * Sets the value of the end property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnd(String value) {
        this.end = value;
    }

    /**
     * Gets the value of the included property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public boolean isIncluded() {
        return included;
    }

    /**
     * Sets the value of the included property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIncluded(boolean value) {
        this.included = value;
    }

    /**
     * Gets the value of the xsl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXsl() {
        return xsl;
    }

    /**
     * Sets the value of the xsl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXsl(String value) {
        this.xsl = value;
    }

}
