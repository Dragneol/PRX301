/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thang.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import thang.db.MyConnection;
import thang.genobj.Images;

/**
 *
 * @author Decen
 */
public class ImageDAO {
    private Connection con;
    private PreparedStatement prestmt;
    private ResultSet rs;

    public ImageDAO() {
    }
    
    private void closeConnection(){
        try {
            if (rs != null) {
                rs.close();
            }
            if (prestmt != null) {
                prestmt.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (Exception e) {
        }
    }
    
    public boolean insert(String url, int productId){
        boolean result = false;
        try {
            con = MyConnection.getConnection();
            prestmt = con.prepareStatement("INSERT INTO [Image](imageUrl,productId) VALUES (?,?);");
            prestmt.setString(1, url);
            prestmt.setInt(2, productId);
            result = prestmt.execute();
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public Images getImagesByProductId(int productId){
        Images images = new Images();
        try {
            con = MyConnection.getConnection();
            prestmt = con.prepareStatement("SELECT imageUrl FROM [Image] WHERE productId = ?");
            prestmt.setInt(1, productId);
            rs = prestmt.executeQuery();
            while (rs.next()){
                images.getImageUrl().add(rs.getString(1));
            }
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return images;
    }
}
