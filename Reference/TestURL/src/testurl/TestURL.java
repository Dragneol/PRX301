/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testurl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dragn
 */
public class TestURL {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            URL url = new URL("http://www.amthuc365.vn/cong-thuc/4792-uc-vit-khoai-tay-sot-chanh-leo-hap-dan-nhu-nha-hang.html");
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String inputLine;
            BufferedWriter writer = new BufferedWriter(new FileWriter("foo.html"));
            while ((inputLine = in.readLine()) != null) {
                writer.write(inputLine);
            }
            writer.close();
            in.close();
        } catch (MalformedURLException ex) {
            Logger.getLogger(TestURL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TestURL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
