
package thang.genobj.webcrawlinfo;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the thang.genobj.webcrawlinfo package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: thang.genobj.webcrawlinfo
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Websites }
     * 
     */
    public Websites createWebsites() {
        return new Websites();
    }

    /**
     * Create an instance of {@link CategoriesType }
     * 
     */
    public CategoriesType createCategoriesType() {
        return new CategoriesType();
    }

    /**
     * Create an instance of {@link Website }
     * 
     */
    public Website createWebsite() {
        return new Website();
    }

    /**
     * Create an instance of {@link CrawlObject }
     * 
     */
    public CrawlObject createCrawlObject() {
        return new CrawlObject();
    }

    /**
     * Create an instance of {@link Mark }
     * 
     */
    public Mark createMark() {
        return new Mark();
    }

    /**
     * Create an instance of {@link CategoryType }
     * 
     */
    public CategoryType createCategoryType() {
        return new CategoryType();
    }

    /**
     * Create an instance of {@link Marks }
     * 
     */
    public Marks createMarks() {
        return new Marks();
    }

}
