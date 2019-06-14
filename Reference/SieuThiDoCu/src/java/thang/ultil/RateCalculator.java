/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thang.ultil;

import java.math.BigDecimal;
import java.math.BigInteger;
import thang.genobj.Product;

/**
 *
 * @author Decen
 */
public class RateCalculator {

    public static final double priceRate = 2.0;
    public static final double statusRate = 1.0;

    public static void calc(Product product) {
        int price = (product.getPrice() != null)?product.getPrice().intValue():1;
        int priceOld = (product.getPriceOld() != null)?product.getPriceOld().intValue():1;
        priceOld = (priceOld < price) ? price : priceOld;
        double status = (product.getStatus() != null)?product.getStatus().doubleValue():90;
        double rate = 0;
        if (price != 1) {
            rate = (1 - price / priceOld) * priceRate + status / 100 * statusRate;
        } else {
            rate = status / 100 * statusRate;
        }
        product.setPriceOld(new BigInteger(priceOld + ""));
        product.setRate(new BigDecimal(rate));
    }
}
