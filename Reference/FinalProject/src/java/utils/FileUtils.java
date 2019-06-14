/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ahhun
 */
public class FileUtils {

    public static String readFile(String inputFilePath) {
        StringBuilder result = new StringBuilder();

        try (FileReader fr = new FileReader(inputFilePath); BufferedReader br = new BufferedReader(fr)) {
            String tmpStr;

            while ((tmpStr = br.readLine()) != null) {
                result.append(tmpStr);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileUtils.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result.toString();
    }

    public static void writeFile(String outputFilePath, String text) {
        try (Writer w = new OutputStreamWriter(new FileOutputStream(outputFilePath), "UTF-8");) {
            w.write(text);
        } catch (IOException ex) {
            Logger.getLogger(FileUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
//        try (FileWriter fw = new FileWriter(outputFilePath); BufferedWriter bw = new BufferedWriter(fw)) {
//            bw.write(text);
//        } catch (IOException ex) {
//            Logger.getLogger(FileUtils.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
}
