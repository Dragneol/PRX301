/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author ahhun
 */
public class DataUtils {
    public static int parseNumber(String sourceStr) {
        int number;
        try {
            number = Integer.parseInt(sourceStr.trim().split(" ")[0]);
        } catch (NumberFormatException ex) {
            number = 0;
        }
        return number;
    }
    
    public static double parseDouble(String sourceStr) {
        double number;
        try {
            number = Double.parseDouble(sourceStr);
        } catch (NumberFormatException ex) {
            number = 0;
        }
        return number;
    }
    
    public static XMLGregorianCalendar convertDateUtilToXMLDate(Date date) {
        XMLGregorianCalendar xmlDate = null;
        try {
            GregorianCalendar gc = new GregorianCalendar();
            gc.setTime(date);
            xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
        } catch (DatatypeConfigurationException ex) {
            Logger.getLogger(DataUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return xmlDate;
    }
    
    public static java.sql.Date convertDateUtilToSQLDate(Date date) {
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        
        return sqlDate;
    }
    
    public static int compareCalendar(Calendar firstCal, Calendar secondCal) {
        int firstCalYear = firstCal.get(Calendar.YEAR);
        int secondCalYear = secondCal.get(Calendar.YEAR);
        int firstCalDateOfYear = firstCal.get(Calendar.DAY_OF_YEAR);
        int secondCalDateOfYear = secondCal.get(Calendar.DAY_OF_YEAR);
        
        return firstCalYear * 400 + firstCalDateOfYear - secondCalYear * 400 - secondCalDateOfYear;
    }
}
