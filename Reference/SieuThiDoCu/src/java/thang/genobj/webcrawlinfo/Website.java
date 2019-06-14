
package thang.genobj.webcrawlinfo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Website complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Website">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="homeUrl" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="categoryLink" type="{http://www.thang.com/XMLSchema/thang}CrawlObject"/>
 *         &lt;element name="productLink" type="{http://www.thang.com/XMLSchema/thang}CrawlObject"/>
 *         &lt;element name="product" type="{http://www.thang.com/XMLSchema/thang}CrawlObject"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Website", propOrder = {
    "homeUrl",
    "categoryLink",
    "productLink",
    "product"
})
public class Website {

    @XmlElement(required = true)
    protected String homeUrl;
    @XmlElement(required = true)
    protected CrawlObject categoryLink;
    @XmlElement(required = true)
    protected CrawlObject productLink;
    @XmlElement(required = true)
    protected CrawlObject product;

    /**
     * Gets the value of the homeUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHomeUrl() {
        return homeUrl;
    }

    /**
     * Sets the value of the homeUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHomeUrl(String value) {
        this.homeUrl = value;
    }

    /**
     * Gets the value of the categoryLink property.
     * 
     * @return
     *     possible object is
     *     {@link CrawlObject }
     *     
     */
    public CrawlObject getCategoryLink() {
        return categoryLink;
    }

    /**
     * Sets the value of the categoryLink property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrawlObject }
     *     
     */
    public void setCategoryLink(CrawlObject value) {
        this.categoryLink = value;
    }

    /**
     * Gets the value of the productLink property.
     * 
     * @return
     *     possible object is
     *     {@link CrawlObject }
     *     
     */
    public CrawlObject getProductLink() {
        return productLink;
    }

    /**
     * Sets the value of the productLink property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrawlObject }
     *     
     */
    public void setProductLink(CrawlObject value) {
        this.productLink = value;
    }

    /**
     * Gets the value of the product property.
     * 
     * @return
     *     possible object is
     *     {@link CrawlObject }
     *     
     */
    public CrawlObject getProduct() {
        return product;
    }

    /**
     * Sets the value of the product property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrawlObject }
     *     
     */
    public void setProduct(CrawlObject value) {
        this.product = value;
    }

}
