/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duongpth.utils;

import duongpth.test.crawl.TestCrawl;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dragn
 */
public class LogUtil {

    public static String readFromStream(InputStream stream) {
        String line, lines = "";
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
            while ((line = reader.readLine()) != null) {
                lines += line;
            }
        } catch (IOException ex) {
            Logger.getLogger(TestCrawl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lines;
    }
}
