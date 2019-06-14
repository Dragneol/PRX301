/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duongpth.utils;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.*;

/**
 *
 * @author dragn
 */
public class JAXBUnmarshall {

    public static void main(String[] args) {
        try {
            JAXBContext jaxbc = JAXBContext.newInstance(Customer.class);
            Unmarshaller unmarshaller = jaxbc.createUnmarshaller();
            File f = new File("customer.xml");
            Customer item = (Customer) unmarshaller.unmarshal(f);
            System.out.println(item.getId() + " " + item.getName());
        } catch (JAXBException ex) {
            Logger.getLogger(JAXBUnmarshall.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
