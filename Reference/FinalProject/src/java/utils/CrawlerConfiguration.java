/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author ahhun
 */
public class CrawlerConfiguration {
    public String LIST_MOVIES;
    
    public String DETAIL_NAME;
    public String DETAIL_DESCRIPTION;
    public String DETAIL_DURATION;
    public String DETAIL_DIRECTOR;
    public String DETAIL_STARS;
    public String DETAIL_CATEGORY;
    public String DETAIL_LANGUAGES;
    public String DETAIL_TYPE;
    public String DETAIL_IMAGE_URL;
    public String DETAIL_ALTERNATIVE_IMAGE_URL;
    public String ID;
    
    public String SCHEDULE;
    public String SCHEDULE_DEPARTMENT;
    
    public String DEPARTMENT_SCHEDULES;
    public String DEPARTMENT_SCHEDULES_DATE;
    public String DEPARTMENT_SCHEDULES_TIMES;
    
    public String HOME_START;
    public String HOME_END;
    public String[] HOME_REMOVE_LIST;
    
    public String DETAIL_START;
    public String DETAIL_END;
    public String[] DETAIL_REMOVE_LIST;
    
    public String DESCRIPTION_START;
    public String DESCRIPTION_END;
    public String[] DESCRIPTION_REMOVE_LIST;
    
    public String SCHEDULE_START;
    public String SCHEDULE_END;
    public String[] SCHEDULE_REMOVE_LIST;

    public CrawlerConfiguration(String configFilePath)
            throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
        Document doc = XMLParser.parseFileToDOM(configFilePath);
        XPath xpath = XMLParser.getXPath();
        
        String exp = "//list-movies";
        LIST_MOVIES = ((String) xpath.evaluate(exp, doc, XPathConstants.STRING)).trim();
        
        exp = "//detail/name";
        DETAIL_NAME = ((String) xpath.evaluate(exp, doc, XPathConstants.STRING)).trim();
        exp = "//detail/description";
        DETAIL_DESCRIPTION = ((String) xpath.evaluate(exp, doc, XPathConstants.STRING)).trim();
        exp = "//detail/duration";
        DETAIL_DURATION = ((String) xpath.evaluate(exp, doc, XPathConstants.STRING)).trim();
        exp = "//detail/director";
        DETAIL_DIRECTOR = ((String) xpath.evaluate(exp, doc, XPathConstants.STRING)).trim();
        exp = "//detail/stars";
        DETAIL_STARS = ((String) xpath.evaluate(exp, doc, XPathConstants.STRING)).trim();
        exp = "//detail/category";
        DETAIL_CATEGORY = ((String) xpath.evaluate(exp, doc, XPathConstants.STRING)).trim();
        exp = "//detail/languages";
        DETAIL_LANGUAGES = ((String) xpath.evaluate(exp, doc, XPathConstants.STRING)).trim();
        exp = "//detail/type";
        DETAIL_TYPE = ((String) xpath.evaluate(exp, doc, XPathConstants.STRING)).trim();
        exp = "//detail/image-url";
        DETAIL_IMAGE_URL = ((String) xpath.evaluate(exp, doc, XPathConstants.STRING)).trim();
        exp = "//detail/alternative-image-url";
        DETAIL_ALTERNATIVE_IMAGE_URL = ((String) xpath.evaluate(exp, doc, XPathConstants.STRING)).trim();
        exp = "//id";
        ID = ((String) xpath.evaluate(exp, doc, XPathConstants.STRING)).trim();
        
        exp = "//schedules/@path";
        SCHEDULE = ((String) xpath.evaluate(exp, doc, XPathConstants.STRING)).trim();
        exp = "//schedules/department";
        SCHEDULE_DEPARTMENT = ((String) xpath.evaluate(exp, doc, XPathConstants.STRING)).trim();
        
        exp = "//schedules/department-schedules/@path";
        DEPARTMENT_SCHEDULES = ((String) xpath.evaluate(exp, doc, XPathConstants.STRING)).trim();
        exp = "//schedules/department-schedules/date";
        DEPARTMENT_SCHEDULES_DATE = ((String) xpath.evaluate(exp, doc, XPathConstants.STRING)).trim();
        exp = "//schedules/department-schedules/times";
        DEPARTMENT_SCHEDULES_TIMES = ((String) xpath.evaluate(exp, doc, XPathConstants.STRING)).trim();
        
        exp = "//processor/home/start";
        HOME_START = ((String) xpath.evaluate(exp, doc, XPathConstants.STRING)).trim();
        exp = "//processor/home/end";
        HOME_END = ((String) xpath.evaluate(exp, doc, XPathConstants.STRING)).trim();
        exp = "//processor/home/remove-list/remove-item";
        NodeList homeRemoveList = (NodeList) xpath.evaluate(exp, doc, XPathConstants.NODESET);
        HOME_REMOVE_LIST = new String[homeRemoveList.getLength()];
        for (int i = 0; i < homeRemoveList.getLength(); i++) {
            HOME_REMOVE_LIST[i] = homeRemoveList.item(i).getTextContent().trim();
        }
        
        exp = "//processor/detail/start";
        DETAIL_START = ((String) xpath.evaluate(exp, doc, XPathConstants.STRING)).trim();
        exp = "//processor/detail/end";
        DETAIL_END = ((String) xpath.evaluate(exp, doc, XPathConstants.STRING)).trim();
        exp = "//processor/detail/remove-list/remove-item";
        NodeList detailRemoveList = (NodeList) xpath.evaluate(exp, doc, XPathConstants.NODESET);
        DETAIL_REMOVE_LIST = new String[detailRemoveList.getLength()];
        for (int i = 0; i < detailRemoveList.getLength(); i++) {
            DETAIL_REMOVE_LIST[i] = detailRemoveList.item(i).getTextContent().trim();
        }
        
        exp = "//processor/description/start";
        DESCRIPTION_START = ((String) xpath.evaluate(exp, doc, XPathConstants.STRING)).trim();
        exp = "//processor/description/end";
        DESCRIPTION_END = ((String) xpath.evaluate(exp, doc, XPathConstants.STRING)).trim();
        exp = "//processor/description/remove-list/remove-item";
        NodeList descriptionRemoveList = (NodeList) xpath.evaluate(exp, doc, XPathConstants.NODESET);
        DESCRIPTION_REMOVE_LIST = new String[descriptionRemoveList.getLength()];
        for (int i = 0; i < descriptionRemoveList.getLength(); i++) {
            DESCRIPTION_REMOVE_LIST[i] = descriptionRemoveList.item(i).getTextContent().trim();
        }
        
        exp = "//processor/schedule/start";
        SCHEDULE_START = ((String) xpath.evaluate(exp, doc, XPathConstants.STRING)).trim();
        exp = "//processor/schedule/end";
        SCHEDULE_END = ((String) xpath.evaluate(exp, doc, XPathConstants.STRING)).trim();
        exp = "//processor/schedule/remove-list/remove-item";
        NodeList scheduleRemoveList = (NodeList) xpath.evaluate(exp, doc, XPathConstants.NODESET);
        SCHEDULE_REMOVE_LIST = new String[scheduleRemoveList.getLength()];
        for (int i = 0; i < scheduleRemoveList.getLength(); i++) {
            SCHEDULE_REMOVE_LIST[i] = scheduleRemoveList.item(i).getTextContent().trim();
        }
    }
}
