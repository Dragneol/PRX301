/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sax.parser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author dragn
 */
public class StudentHandler extends DefaultHandler {

    private String username, password;
    private boolean foundUsername, foundPassword;
    private String currentTagName;
    private String fullName;

    public String getFullName() {
        return fullName;
    }
    private boolean found;

    public boolean isFound() {
        return found;
    }

    public StudentHandler(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
//        super.startElement(uri, localName, qName, attributes); //To change body of generated methods, choose Tools | Templates.
        if (!found) {
            if (qName.equals("student")) {
                String id = attributes.getValue("id");
                if (id.equals(username)) {
                    foundUsername = true;
                }
            }
            currentTagName = qName;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
//        super.endElement(uri, localName, qName); //To change body of generated methods, choose Tools | Templates.
        if (qName.equals("student")) {
            System.out.println("full name = " + fullName);
            System.out.println(found);
        }
        currentTagName = "";
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
//        super.characters(ch, start, length); //To change body of generated methods, choose Tools | Templates.
        if (!found) {
            if (foundUsername) {
                if (currentTagName.equals("lastname")) {
                    String str = new String(ch, start, length);
                    fullName = str.trim();
                } else if (currentTagName.equals("middlename")) {
                    String str = new String(ch, start, length);
                    fullName += " " + str.trim();
                } else if (currentTagName.equals("firstname")) {
                    String str = new String(ch, start, length);
                    fullName += " " + str.trim();
                } else if (currentTagName.equals("password")) {
                    String str = new String(ch, start, length);
                    foundUsername = false;
                    if (str.trim().equals(password)) {
                        foundPassword = true;
                    }
                }
            }
            if (foundPassword) {
                if (currentTagName.equals("status")) {
                    String str = new String(ch, start, length);
                    foundPassword = false;
                    if (!str.trim().equals("dropout")) {
                        found = true;
                    }
                }
            }
        }
    }
}
