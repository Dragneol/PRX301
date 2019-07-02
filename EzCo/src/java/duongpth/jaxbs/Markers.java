
package duongpth.jaxbs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for markers complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="markers">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="home" type="{http://www.ezco.com/XMLSchema/ezco}marker"/>
 *         &lt;element name="detail" type="{http://www.ezco.com/XMLSchema/ezco}marker"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "markers", propOrder = {
    "home",
    "detail"
})
public class Markers {

    @XmlElement(required = true)
    protected Marker home;
    @XmlElement(required = true)
    protected Marker detail;

    /**
     * Gets the value of the home property.
     * 
     * @return
     *     possible object is
     *     {@link Marker }
     *     
     */
    public Marker getHome() {
        return home;
    }

    /**
     * Sets the value of the home property.
     * 
     * @param value
     *     allowed object is
     *     {@link Marker }
     *     
     */
    public void setHome(Marker value) {
        this.home = value;
    }

    /**
     * Gets the value of the detail property.
     * 
     * @return
     *     possible object is
     *     {@link Marker }
     *     
     */
    public Marker getDetail() {
        return detail;
    }

    /**
     * Sets the value of the detail property.
     * 
     * @param value
     *     allowed object is
     *     {@link Marker }
     *     
     */
    public void setDetail(Marker value) {
        this.detail = value;
    }

}
