/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thang.crawl;

import com.sun.xml.internal.stream.events.EndElementEvent;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import org.xml.sax.SAXException;
import thang.dao.CategoryDAO;
import thang.dao.ImageDAO;
import thang.dao.ProductDAO;
import thang.genobj.Categories;
import thang.genobj.Category;
import thang.genobj.Images;
import thang.genobj.Product;
import thang.genobj.Products;
import thang.genobj.webcrawlinfo.CategoriesType;
import thang.genobj.webcrawlinfo.CategoryType;
import thang.genobj.webcrawlinfo.Mark;
import thang.genobj.webcrawlinfo.Website;
import thang.genobj.webcrawlinfo.Websites;
import thang.ultil.MyJAXBUltil;
import thang.ultil.RateCalculator;

/**
 *
 * @author Decen
 */
public class CrawlUltil {

    private static final String LOG_FILE = "WEB-INF/logfile.txt";

    private static CategoriesType categoriesType;
    private static String otherCategory;
    private static Collection<String> totalProductLink;
    private static String realPath;
    private static FileWriter fw;
    private static int process;

//    public static void main(String[] args) {
//        crawl("");
//    }
    public static void crawl(String path) {
        try {
            realPath = path;
            process = 0;
            fw = new FileWriter(new File(realPath + LOG_FILE));
            Websites w = getConfig();
            categoriesType = w.getCategories();
            otherCategory = getCategoryId("other");
            insertCategory(w);
            for (Website website : w.getWebsite()) {
                crawlWeb(website);
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(CrawlUltil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JAXBException ex) {
            Logger.getLogger(CrawlUltil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (XMLStreamException ex) {
            Logger.getLogger(CrawlUltil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CrawlUltil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(CrawlUltil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(CrawlUltil.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException ex) {
                    Logger.getLogger(CrawlUltil.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private static boolean insertCategory(Websites w) {
        boolean result = true;
        for (CategoryType categoryType : w.getCategories().getCategory()) {
            CategoryDAO categoryDAO = new CategoryDAO();
            Category category = new Category();
            category.setId(categoryType.getId());
            category.setName(categoryType.getName());
            boolean rs = categoryDAO.insert(category);
            if (!rs) {
                result = rs;
            }
        }
        return result;
    }

    String asd= "asdadsasdasd";
    private static void insertProduct(Product product) {
        ProductDAO productDAO = new ProductDAO();
        ImageDAO imageDAO = new ImageDAO();
        
        RateCalculator.calc(product);
        int productId = productDAO.insert(product);
        for (String url : product.getImages().getImageUrl()) {
            imageDAO.insert(url, productId);
        }
        System.out.println("Process: " + ++process);
    }

    private static void crawlWeb(Website website) throws IOException, XMLStreamException, TransformerException, SAXException {
        //variable for storeage all link of product
        totalProductLink = new ArrayList<>();
        String homeUrl = website.getHomeUrl();

        List<Mark> markers = website.getCategoryLink().getMarks().getMark();
        String xslUrl = realPath + website.getCategoryLink().getXslUrl();

        InputStream result = getDataFromWeb(homeUrl, markers); ///cut content + delete all special character + process VN
        result = processWellForm(result);//add closing tag
        result = transformXML(result, xslUrl);

        Categories categories = null;
        try {
            categories = MyJAXBUltil.unmarshalling(result, new Categories());
            List<Category> categoriList = categories.getCategory();

            for (int i = 0; i < categoriList.size(); i++) {
                Category category = categoriList.get(i);
                String categoryLink = category.getLink();
                String categoryId = getCategoryId(category.getName());
                String nextPage = null;

                do {
                    if (nextPage != null && !nextPage.equals("")) {
                        categoryLink = nextPage;
                    }
                    if (!categoryLink.startsWith("http")) {
                        categoryLink = homeUrl + categoryLink;
                    }
                    result = getDataFromWeb(categoryLink, website.getProductLink().getMarks().getMark());
                    result = processWellForm(result);
                    result = transformXML(result, realPath + website.getProductLink().getXslUrl());

                    Products products = null;
                    products = MyJAXBUltil.unmarshalling(result, new Products());
                    for (Product product : products.getProduct()) {
                        String productLink = product.getLink();
                        if (!productLink.startsWith("http")) {
                            productLink = homeUrl + productLink;
                        }
                        if (!totalProductLink.contains(productLink)) {
                            totalProductLink.add(productLink);
                            result = getDataFromWeb(productLink, website.getProduct().getMarks().getMark());
                            result = processWellForm(result);
                            result = transformXML(result, realPath + website.getProduct().getXslUrl(), homeUrl);
                            Products pTmp = null;

                            pTmp = MyJAXBUltil.unmarshalling(result, new Products(), realPath + "WEB-INF/xsd/products.xsd");
                            if (pTmp == null) {
                                fw.append("Error product: " + productLink + "\n");
                                continue;
                            }
                            Product pDetail = pTmp.getProduct().get(0);
                            pDetail.setName(product.getName());
                            pDetail.setCategoryId(categoryId);
                            try {
                                insertProduct(pDetail);
                            } catch (Exception e) {
                                fw.append("Error product: " + productLink + "\n");
                            }
                        }

//                        System.out.println(pDetail.getPriceOld());
                    }
                    nextPage = products.getNextPage();
                } while (nextPage != null && !nextPage.equals(""));
            }
        } catch (JAXBException ex) {
            Logger.getLogger(CrawlUltil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CrawlUltil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static Websites getConfig() throws FileNotFoundException, JAXBException, XMLStreamException {
        File f = new File(realPath + "WEB-INF/crawl-web.xml");
        InputStream is = new FileInputStream(f);
        Websites w = MyJAXBUltil.unmarshalling(is, new Websites());
        return w;
    }

    private static String getCategoryId(String altName) {
        for (CategoryType categoryType : categoriesType.getCategory()) {
            for (String string : categoryType.getAltName()) {
                if (string.equalsIgnoreCase(altName.trim())) {
                    return categoryType.getId();
                }
            }
        }
        return otherCategory;
    }


    public static InputStream transformXML(InputStream inputStream, String xslUrl) throws TransformerConfigurationException, TransformerException, UnsupportedEncodingException {
        StringWriter writer = new StringWriter();

        StreamResult streamResult = new StreamResult(writer);
        TransformerFactory transformFactory = TransformerFactory.newInstance();
        Transformer transformer = transformFactory.newTransformer(new StreamSource(new File(xslUrl)));
        transformer.transform(new StreamSource(inputStream), streamResult);
        return new ByteArrayInputStream(writer.toString().getBytes("UTF-8"));
    }

    public static InputStream transformXML(InputStream inputStream, String xslUrl, String param) throws TransformerConfigurationException, TransformerException, UnsupportedEncodingException {
        StringWriter writer = new StringWriter();

        StreamResult streamResult = new StreamResult(writer);
        TransformerFactory transformFactory = TransformerFactory.newInstance();
        Transformer transformer = transformFactory.newTransformer(new StreamSource(new File(xslUrl)));
        transformer.setParameter("mainUrl", param);
//            transformer.transform(new StreamSource(new ByteArrayInputStream(result.toString().getBytes())), streamResult);
        transformer.transform(new StreamSource(inputStream), streamResult);
        return new ByteArrayInputStream(writer.toString().getBytes("UTF-8"));
    }

    public static InputStream processWellForm(InputStream inputStream) throws XMLStreamException, UnsupportedEncodingException {
        StringBuffer result = new StringBuffer();
        StreamSource streamSource = new StreamSource(inputStream);

        XMLInputFactory factory = XMLInputFactory.newFactory();
        XMLEventReader reader = factory.createXMLEventReader(inputStream, "UTF-8");

        int tagMarker = 0;

        while (reader.hasNext()) {
            XMLEvent event = null;
            try {
                event = reader.nextEvent();
            } catch (XMLStreamException ex) {
//                ex.printStackTrace();
//                System.out.println(ex.getMessage());
                String message = ex.getMessage();
                String errString = "matching end-tag \"</";
                if (message.contains("Message: The element type")) {
                    String missingTagName = message.substring(message.indexOf(errString) + errString.length(), message.indexOf(">\"."));
                    EndElement missingTag = new EndElementEvent(new QName(missingTagName));
                    event = missingTag;
                }
            } catch (NullPointerException ex) {
                break;
            }

            if (event != null) {
                if (event.isStartElement()) {
                    tagMarker++;
                } else if (event.isEndElement()) {
                    tagMarker--;
                }
                if (tagMarker >= 0) {
                    result.append(event.toString().trim());
                }
            }
        }
        String res = result.toString().trim().replace("version=\"null\"", "version=\"1.0\"").replace("ENDDOCUMENT", "");
        return new ByteArrayInputStream(res.getBytes("UTF-8"));
    }

    public static InputStream getDataFromWeb(String url, List<Mark> markers) throws MalformedURLException, IOException, XMLStreamException {
        URL urlink = new URL(url);
        HttpURLConnection con = (HttpURLConnection) urlink.openConnection();
        InputStream inputStream = con.getInputStream();
        String content = processStreamToString(inputStream);
        content = cutContent(content, markers);

        return new ByteArrayInputStream(content.getBytes("UTF-8"));
    }

    public static String cutContent(String content, List<Mark> markers) {
        if (markers == null) {
            return content;
        }
        try {
            for (int i = 0; i < markers.size(); i++) {
                Mark tmp = markers.get(i);
                int startCut = content.indexOf(tmp.getStart());
                int endCut = content.indexOf(tmp.getEnd());
                startCut = (startCut != -1) ? startCut : 0;
                if (endCut != -1) {
                    if (tmp.isIsInclude()) {
                        endCut = endCut + tmp.getEnd().length();
                    } else {
                        endCut = endCut;
                    }
                } else {
                    endCut = content.length();
                }

                content = content.substring(startCut, endCut);
            }
            content = "<root>" + content + "</root>";
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return content;
    }

    public static String processStreamToString(InputStream inputStream) throws IOException {
        InputStreamReader reader = new InputStreamReader(inputStream, "UTF-8");
        BufferedReader br = new BufferedReader(reader);
        StringBuilder content = new StringBuilder();
        String line = null;

        while ((line = br.readLine()) != null) {
            line = line
                    .replace("&nbsp;", "")
                    .replace("&aacute;", "á")
                    .replace("> < </a>", "> &lt; </a>")
                    .replace("&", "&amp;")
                    .replace("g:plusone", "gplusone")
                    .replace("<br>", "")
                    .replace("<br/>", "")
                    .replace("</br>", "")
                    .replace("itemscope", " ")
                    .replace("disabled", "")
                    .replaceAll("onclick=\"(([^'\"]*)'([^'\"]*))+\"", "");
            content.append(line);
        }
        br.close();
        reader.close();
        return content.toString().trim();
    }
}
