
package duongpth.jaxbs;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for instructionmenu complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="instructionmenu">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;element name="instructiondetail" type="{http://www.ezco.com/XMLSchema/ezco}instructiondetail" maxOccurs="unbounded" minOccurs="0"/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "instructionmenu", propOrder = {
    "instructiondetail"
})
public class Instructionmenu {

    protected List<Instructiondetail> instructiondetail;

    /**
     * Gets the value of the instructiondetail property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the instructiondetail property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInstructiondetail().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Instructiondetail }
     * 
     * 
     */
    public List<Instructiondetail> getInstructiondetail() {
        if (instructiondetail == null) {
            instructiondetail = new ArrayList<Instructiondetail>();
        }
        return this.instructiondetail;
    }

}
