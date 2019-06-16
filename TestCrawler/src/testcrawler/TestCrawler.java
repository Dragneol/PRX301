/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testcrawler;

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
public class TestCrawler {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
//            AmThuc.setUrl(new URL("http://www.amthuc365.vn/cong-thuc/105-thanh-phan/"));
//            AmThuc.crawl();
//            NguyenHaFood.setUrl(new URL("https://nguyenhafood.vn/detail/khoai-tay-dong-lanh-mccain-frozen-french-fries-5kggoi-977.html"));
//            NguyenHaFood.crawl();
            NkFood.setUrl(new URL("http://nkfood.vn/cua-hang/?paged=1"));
            NkFood.crawl();
        } catch (MalformedURLException ex) {
            Logger.getLogger(TestCrawler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TestCrawler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
