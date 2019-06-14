/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import sax.parser.StudentHandler;
import sax.utils.XMLUtils;

/**
 *
 * @author dragn
 */
public class test {

    public static void main(String[] args) {
        StudentHandler saxObj = new StudentHandler("3", "123456");
        XMLUtils.parseFileWithSAX("web\\WEB-INF\\studentsAccount.xml", saxObj);
    }
}
