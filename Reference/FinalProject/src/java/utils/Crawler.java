/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import constant.DatabaseConstant;
import constant.UrlConstant;
import dao.DepartmentDAO;
import dao.MovieDAO;
import dao.MovieScheduleDAO;
import dao.TheaterDAO;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import jaxb.Department;
import jaxb.Movie;
import jaxb.MovieSchedule;
import jaxb.Theater;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author ahhun
 */
public class Crawler {
    public static boolean isStopping = false;
    public static boolean isCrawling = false;
    public static boolean isScheduled = false;
    private static TimerTask timerTask;
    private static Timer timer;
    
    private static void crawlCGV(String dataFilePath, String configFilePath) {
        if (isStopping) {
            return;
        }
        try {
            CrawlerConfiguration config = new CrawlerConfiguration(configFilePath);
            // ----- +++++ -----
            // CRAWL LIST MOVIES PAGE
            String nowShowingString = HTMLRequestUtils.getDataFromPostRequest(UrlConstant.CGV_NOW_SHOWING_URL, null);
            HTMLProcessor.processCGVNowShowing(nowShowingString, config, dataFilePath);
            
            // Parse crawled data from file to dom
            Document doc = XMLParser.parseFileToDOM(dataFilePath);
            XPath xpath = XMLParser.getXPath();
            
            // Get all links of movies
            String exp = config.LIST_MOVIES;
            NodeList linkList = (NodeList) xpath.evaluate(exp, doc, XPathConstants.NODESET);
            
            // Get theater
            TheaterDAO.insertIfNotExisted(new Theater(DatabaseConstant.CGV));
            Theater theater = TheaterDAO.getTheaterByName(DatabaseConstant.CGV);
            
            // For each link, crawl detail data
            for (int i = 0; i < linkList.getLength(); i++) {
                if (isStopping) {
                    return;
                }
                try {
                    // ----- +++++ -----
                    // CRAWL DETAIL PAGE
                    String detailUrl = linkList.item(i).getAttributes().getNamedItem("href").getTextContent();
                    System.out.println(detailUrl);
                    String detailString = HTMLRequestUtils.getDataFromPostRequest(detailUrl, null);

                    // Parse crawled data from file to dom
                    HTMLProcessor.processCGVDetailMovie(detailString, config, dataFilePath);
                    Document detailDoc = XMLParser.parseFileToDOM(dataFilePath);

                    // Get detail movie from dom
                    exp = config.DETAIL_NAME;
                    String VietnameseName = ((String) xpath.evaluate(exp, detailDoc, XPathConstants.STRING)).trim();
                    exp = config.DETAIL_DESCRIPTION;
                    String description = ((String) xpath.evaluate(exp, detailDoc, XPathConstants.STRING)).trim();
                    exp = config.DETAIL_DURATION;
                    String duration = ((String) xpath.evaluate(exp, detailDoc, XPathConstants.STRING)).trim();
                    int durationNumber = DataUtils.parseNumber(duration);
                    exp = config.DETAIL_DIRECTOR;
                    String director = ((String) xpath.evaluate(exp, detailDoc, XPathConstants.STRING)).trim();
                    exp = config.DETAIL_STARS;
                    String stars = ((String) xpath.evaluate(exp, detailDoc, XPathConstants.STRING)).trim();
                    exp = config.DETAIL_CATEGORY;
                    String category = ((String) xpath.evaluate(exp, detailDoc, XPathConstants.STRING)).trim();
                    exp = config.DETAIL_LANGUAGES;
                    String languages = ((String) xpath.evaluate(exp, detailDoc, XPathConstants.STRING)).trim();
                    exp = config.DETAIL_TYPE;
                    String type = ((String) xpath.evaluate(exp, detailDoc, XPathConstants.STRING)).trim();
                    exp = config.DETAIL_IMAGE_URL;
                    String imageUrl = ((String) xpath.evaluate(exp, detailDoc, XPathConstants.STRING)).trim();
                    if (imageUrl.isEmpty()) {
                        exp = config.DETAIL_ALTERNATIVE_IMAGE_URL;
                        imageUrl = ((String) xpath.evaluate(exp, detailDoc, XPathConstants.STRING)).trim();
                    }
                    
                    // CRAWL DETAIL ENGLISH PAGE
                    String EngUrl = detailUrl.replace("default", "en").concat("?___from_store=default");
                    String detailEngString = HTMLRequestUtils.getDataFromPostRequest(EngUrl, null);
                    
                    // Parse crawled data from file to dom
                    HTMLProcessor.processCGVDetailMovie(detailEngString, config, dataFilePath);
                    Document detailEngDoc = XMLParser.parseFileToDOM(dataFilePath);
                    
                    // Get English name
                    exp = config.DETAIL_NAME;
                    String EnglishName = ((String) xpath.evaluate(exp, detailEngDoc, XPathConstants.STRING)).trim().toLowerCase();
                    
                    // Insert to database
                    Movie movie = new Movie(VietnameseName, EnglishName, description, durationNumber, director, stars, category, languages, type, imageUrl);
                    MovieDAO.insertIfNotExisted(movie);
                    movie = MovieDAO.getMovieByName(EnglishName, VietnameseName);
                    
                    // Get movie id
                    exp = config.ID;
                    String id = ((String) xpath.evaluate(exp, detailDoc, XPathConstants.STRING)).trim();
                    
                    // ----- +++++ -----
                    // CRAWL SCHEDULE PAGE
                    Calendar c = Calendar.getInstance();
                    c.setTime(new java.util.Date());
                    java.util.Date[] days = new java.util.Date[3];
                    days[0] = c.getTime();
                    c.add(Calendar.DATE, 1);
                    days[1] = c.getTime();
                    c.add(Calendar.DATE, 1);
                    days[2] = c.getTime();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                    for (Date day : days) {
                        if (isStopping) {
                            return;
                        }
                        HashMap params = new HashMap();
                        params.put("id", id);
                        params.put("dy", sdf.format(day));
                        String scheduleString = HTMLRequestUtils.getDataFromPostRequest(UrlConstant.CGV_SCHEDULE_URL, params);

                        // Parse crawled data from file to dom
                        HTMLProcessor.processCGVSchedule(scheduleString, config, dataFilePath);
                        Document scheduleDoc = XMLParser.parseFileToDOM(dataFilePath);

                        // Get schedule from dom
                        exp = config.SCHEDULE;
                        NodeList listDepartment = (NodeList) xpath.evaluate(exp, scheduleDoc, XPathConstants.NODESET);

                        for (int k = 0; k < listDepartment.getLength(); k++) {
                            if (isStopping) {
                                return;
                            }
                            // Get department
                            exp = config.SCHEDULE_DEPARTMENT;
                            Node department = listDepartment.item(k);
                            String departmentName = (String) xpath.evaluate(exp, department, XPathConstants.STRING);

                            // Insert department
                            Department departmentJaxb = DepartmentDAO.getDepartmentByName(departmentName, theater.getId());
                            if (departmentJaxb == null) {
                                // Get address
                                String address = HTMLRequestUtils.getLocation(departmentName);

                                DepartmentDAO.insertIfNotExisted(new Department(departmentName, address, theater.getId()));
                                departmentJaxb = DepartmentDAO.getDepartmentByName(departmentName, theater.getId());
                            }

                            // Get list schedule
                            exp = config.DEPARTMENT_SCHEDULES;
                            NodeList timeList = (NodeList) xpath.evaluate(exp, department, XPathConstants.NODESET);

                            MovieSchedule.ShowingTimeList timeListArray = new MovieSchedule.ShowingTimeList();                            
                            for (int l = 0; l < timeList.getLength(); l++) {
                                String time = timeList.item(l).getTextContent().split(" ")[0];
                                timeListArray.getTime().add(time);
                            }

                            // Insert to database
                            XMLGregorianCalendar xmlDate = DataUtils.convertDateUtilToXMLDate(day);
                            MovieSchedule movieSchedule = new MovieSchedule(movie.getId(), departmentJaxb.getId(),
                                    xmlDate, timeListArray, detailUrl);
                            MovieScheduleDAO.insertOrUpdate(movieSchedule);
                        }
                    }
                } catch (MalformedURLException ex) {
                    Logger.getLogger(Crawler.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Crawler.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(Crawler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Crawler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (XPathExpressionException ex) {
            Logger.getLogger(Crawler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Crawler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(Crawler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Crawler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void crawlLotteCinema(String dataFilePath, String configFilePath) {
        if (isStopping) {
            return;
        }
        try {
            CrawlerConfiguration config = new CrawlerConfiguration(configFilePath);
            // ----- +++++ -----
            // CRAWL LIST MOVIES PAGE
            String homePageString = HTMLRequestUtils.getDataFromGetRequest(UrlConstant.LOTTE_HOME_URL);
            HTMLProcessor.processLotteHomePage(homePageString, config, dataFilePath);
            
            // Parse crawled data from file to dom
            Document doc = XMLParser.parseFileToDOM(dataFilePath);
            XPath xpath = XMLParser.getXPath();
            
            // Get all links of movies
            String exp = config.LIST_MOVIES;
            NodeList linkList = (NodeList) xpath.evaluate(exp, doc, XPathConstants.NODESET);
            
            // Get theater
            TheaterDAO.insertIfNotExisted(new Theater(DatabaseConstant.LOTTE));
            Theater theater = TheaterDAO.getTheaterByName(DatabaseConstant.LOTTE);

            // For each link, crawl detail data
            for (int i = 0; i < linkList.getLength(); i++) {
                if (isStopping) {
                    return;
                }
                try {
                    // ----- +++++ -----
                    // CRAWL DETAIL PAGE
                    String detailUrl = linkList.item(i).getAttributes().getNamedItem("href").getTextContent();
                    String encodedUrl = URLEncoder.encode(detailUrl.toLowerCase(), "UTF-8").replaceAll("%2F", "/");
                    System.out.println(encodedUrl);
                    String detailString = HTMLRequestUtils.getDataFromGetRequest(UrlConstant.LOTTE_DETAIL_VI_URL + encodedUrl);
                    
                    // Parse crawled data from file to dom
                    HTMLProcessor.processLotteDetailMovie(detailString, config, dataFilePath);
                    Document detailDoc = XMLParser.parseFileToDOM(dataFilePath);

                    exp = config.DETAIL_NAME;
                    String VietnameseName = ((String) xpath.evaluate(exp, detailDoc, XPathConstants.STRING)).trim();
                    VietnameseName = VietnameseName.replaceAll("\\(.*\\)", "").trim();
                    exp = config.DETAIL_DURATION;
                    String duration = ((String) xpath.evaluate(exp, detailDoc, XPathConstants.STRING)).trim();
                    int durationNumber = DataUtils.parseNumber(duration);
                    exp = config.DETAIL_DIRECTOR;
                    String director = ((String) xpath.evaluate(exp, detailDoc, XPathConstants.STRING)).trim();
                    exp = config.DETAIL_STARS;
                    String stars = ((String) xpath.evaluate(exp, detailDoc, XPathConstants.STRING)).trim();
                    exp = config.DETAIL_CATEGORY;
                    String category = ((String) xpath.evaluate(exp, detailDoc, XPathConstants.STRING)).trim();
                    exp = config.DETAIL_LANGUAGES;
                    String languages = ((String) xpath.evaluate(exp, detailDoc, XPathConstants.STRING)).trim();
                    exp = config.DETAIL_IMAGE_URL;
                    String imageUrl = UrlConstant.LOTTE_DETAIL_VI_URL + ((String) xpath.evaluate(exp, detailDoc, XPathConstants.STRING)).trim();
                    String type = null;
                    
                    if ("Thông Báo".equals(category)) {
                        continue;
                    }
                    
                    // Parse description data from file to dom
                    HTMLProcessor.processLotteMovieDescription(detailString, config, dataFilePath);
                    Document descriptionDoc = XMLParser.parseFileToDOM(dataFilePath);
                    
                    exp = config.DETAIL_DESCRIPTION;
                    String description = ((String) xpath.evaluate(exp, descriptionDoc, XPathConstants.STRING)).trim();
                    
                    // CRAWL DETAIL ENGLISH PAGE
                    String detailEngString = HTMLRequestUtils.getDataFromGetRequest(UrlConstant.LOTTE_DETAIL_EN_URL + encodedUrl);
                    
                    // Parse crawled data from file to dom
                    HTMLProcessor.processLotteDetailMovie(detailEngString, config, dataFilePath);
                    Document detailEngDoc = XMLParser.parseFileToDOM(dataFilePath);
                    
                    // Get English name
                    exp = config.DETAIL_NAME;
                    String EnglishName = ((String) xpath.evaluate(exp, detailEngDoc, XPathConstants.STRING)).trim().toLowerCase();
                    EnglishName = EnglishName.replaceAll("\\(.*\\)", "").trim();
                    
                    // Insert to database
                    Movie movie = new Movie(VietnameseName, EnglishName, description, durationNumber, director, stars, category, languages, type, imageUrl);
                    MovieDAO.insertIfNotExisted(movie);
                    movie = MovieDAO.getMovieByName(EnglishName, VietnameseName);
                    
                    // Parse schedule data from file to dom
                    HTMLProcessor.processLotteSchedule(detailString, config, dataFilePath);
                    Document scheduleDoc = XMLParser.parseFileToDOM(dataFilePath);
                    
                    // Get schedule from dom
                    exp = config.SCHEDULE;
                    NodeList listDepartment = (NodeList) xpath.evaluate(exp, scheduleDoc, XPathConstants.NODESET);
                    
                    for (int j = 0; j < listDepartment.getLength(); j++) {
                        if (isStopping) {
                            return;
                        }
                        // Get department
                        Node department = listDepartment.item(j);
                        String departmentName = "Lotte " + TextUtils.separateWord(department.getTextContent().trim());

                        // Insert department
                        Department departmentJaxb = DepartmentDAO.getDepartmentByName(departmentName, theater.getId());
                        if (departmentJaxb == null) {
                            // Get address
                            String address = HTMLRequestUtils.getLocation(departmentName);
                            
                            DepartmentDAO.insertIfNotExisted(new Department(departmentName, address, theater.getId()));
                            departmentJaxb = DepartmentDAO.getDepartmentByName(departmentName, theater.getId());
                        }
                        
                        // Get list schedule
                        Node timeTables = department.getNextSibling();
                        if (timeTables != null) {
                            timeTables = timeTables.getNextSibling();
                            if (timeTables != null) {
                                exp = config.DEPARTMENT_SCHEDULES;
                                NodeList tables = (NodeList) xpath.evaluate(exp, timeTables, XPathConstants.NODESET);
                                
                                for (int k = 0; k < tables.getLength(); k++) {
                                    if (isStopping) {
                                        return;
                                    }
                                    Node table = tables.item(k);
                                    exp = config.DEPARTMENT_SCHEDULES_DATE;
                                    String dateStr = ((String) xpath.evaluate(exp, table, XPathConstants.STRING)).trim();
                                    
                                    exp = config.DEPARTMENT_SCHEDULES_TIMES;
                                    NodeList times = (NodeList) xpath.evaluate(exp, table, XPathConstants.NODESET);
                                    
                                    MovieSchedule.ShowingTimeList timeListArray = new MovieSchedule.ShowingTimeList();
                                    for (int l = 0; l < times.getLength(); l++) {
                                        timeListArray.getTime().add(times.item(l).getTextContent().trim());
                                    }
                                    
                                    // Insert to database
                                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                    java.util.Date date = sdf.parse(dateStr);
                                    XMLGregorianCalendar xmlDate = DataUtils.convertDateUtilToXMLDate(date);
                                    MovieSchedule movieSchedule = new MovieSchedule(movie.getId(), departmentJaxb.getId(),
                                            xmlDate, timeListArray, UrlConstant.LOTTE_DETAIL_VI_URL + encodedUrl);
                                    MovieScheduleDAO.insertOrUpdate(movieSchedule);
                                }
                            }
                        }
                    }
                } catch (MalformedURLException ex) {
                    Logger.getLogger(Crawler.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Crawler.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(Crawler.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(Crawler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Crawler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (XPathExpressionException ex) {
            Logger.getLogger(Crawler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(Crawler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(Crawler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Crawler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void crawl(String dataFilePath, String cgvFilePath, String lotteFilePath) {
        isCrawling = true;
        isStopping = false;
        crawlCGV(dataFilePath, cgvFilePath);
        crawlLotteCinema(dataFilePath, lotteFilePath);
        isCrawling = false;
    }
    
    public static void startCrawlingRegularly(String dataFilePath, String cgvFilePath, String lotteFilePath,
            long scheduleTime, ServletContext context, String flag) {
        if (!isScheduled) {
            isScheduled = true;
            timer = new Timer();
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    try {
                        if (!isCrawling) {
                            System.out.println("START CRAWLING IN SCHEDULE");
                            crawl(dataFilePath, cgvFilePath, lotteFilePath);
                            context.removeAttribute(flag);
                            System.out.println("STOP CRAWLING IN SCHEDULE");
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(Crawler.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            };
            timer.schedule(timerTask, 0, scheduleTime);
        }
    }
    
    public static void stopCrawlingRegularly() {
        timer.cancel();
        timer.purge();
        isScheduled = false;
    }
}
