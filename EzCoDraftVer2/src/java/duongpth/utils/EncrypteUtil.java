/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duongpth.utils;

import java.io.Serializable;

/**
 *
 * @author dragn
 */
public class EncrypteUtil implements Serializable {

    public static String decode(String string) {
        String s = string;
        s = s.replaceAll("&ndash;", "-");
        s = s.replaceAll("&nbsp;", "");
        s = s.replaceAll("&hellip;", "…");
        s = s.replaceAll("&rarr;", "→");
        s = s.replaceAll("&", "&amp;");
        s = s.replaceAll("<br>", "");
        s = s.replaceAll("<br/>", "");
        s = s.replaceAll("</br>", "");
        s = s.replaceAll("itemscope", "");
        s = s.replaceAll("disabled", "");
        return s;
    }
}
