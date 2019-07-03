/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duongpth.utils;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author dragn
 */
public class VietnameseUtil implements Serializable {

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

    public static String normalizeWords(String words) {
        byte[] bytes = words.trim().getBytes(StandardCharsets.ISO_8859_1);
        String newWords = new String(bytes, StandardCharsets.UTF_8);
        newWords = newWords.replaceAll("  ", " ");
        String[] word = newWords.split(" ");
        StringBuilder sb = new StringBuilder();

        for (String string : word) {
            if (!string.equals("")) {
                sb.append(Character.toUpperCase(string.charAt(0)));
                sb.append(string.toLowerCase().substring(1));
            }
            sb.append(" ");
        }
        newWords = sb.toString().trim();
        return newWords;
    }

    public static void main(String[] args) {
        String s = "    cAS  asf   ";
        System.out.println('|' + normalizeWords(s) + '|');
    }
}
