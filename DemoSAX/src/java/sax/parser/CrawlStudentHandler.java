/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sax.parser;

import javax.xml.namespace.QName;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import sax.daos.StudentDAO;
import sax.dtos.StudentDTO;

/**
 *
 * @author dragn
 */
public class CrawlStudentHandler extends DefaultHandler {

    private String currentTagname;
    private String fullName;
    private boolean inserted;
    private StudentDTO dto;

    public CrawlStudentHandler() {
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
//        super.startElement(uri, localName, qName, attributes); //To change body of generated methods, choose Tools | Templates.
        this.currentTagname = qName;
        if (qName.equals("student")) {
            dto = new StudentDTO();
            String id = attributes.getValue("id");
            dto.setId(id);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
//        super.characters(ch, start, length); //To change body of generated methods, choose Tools | Templates.
        String str = new String(ch, start, length);
        if (currentTagname.equals("lastname")) {
            fullName = str.trim() + " ";
        } else if (currentTagname.equals("middlename")) {
            fullName += str.trim() + " ";
        } else if (currentTagname.equals("firstname")) {
            fullName += str.trim();
        } else if (currentTagname.equals("password")) {
            dto.setPass(str);
        } else if (currentTagname.equals("status")) {
            if (!str.trim().equals("dropout")) {
                inserted = true;
                dto.setStatus(str.trim());
            }
        }
    }

    @Override
    public void endDocument() throws SAXException {
//        super.endDocument(); //To change body of generated methods, choose Tools | Templates.
        if (currentTagname.equals("student")) {
            dto.setName(fullName);
            if (inserted) {
                StudentDAO dao = new StudentDAO();
                dao.insert(dto);
                inserted = false;
            }
        }
    }

}
