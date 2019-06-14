
package thang.genobj.webcrawlinfo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CrawlObject complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CrawlObject">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="xslUrl" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="marks" type="{http://www.thang.com/XMLSchema/thang}Marks"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CrawlObject", propOrder = {
    "xslUrl",
    "marks"
})
public class CrawlObject {

    @XmlElement(required = true)
    protected String xslUrl;
    @XmlElement(required = true)
    protected Marks marks;

    /**
     * Gets the value of the xslUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXslUrl() {
        return xslUrl;
    }

    /**
     * Sets the value of the xslUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXslUrl(String value) {
        this.xslUrl = value;
    }

    /**
     * Gets the value of the marks property.
     * 
     * @return
     *     possible object is
     *     {@link Marks }
     *     
     */
    public Marks getMarks() {
        return marks;
    }

    /**
     * Sets the value of the marks property.
     * 
     * @param value
     *     allowed object is
     *     {@link Marks }
     *     
     */
    public void setMarks(Marks value) {
        this.marks = value;
    }

}
