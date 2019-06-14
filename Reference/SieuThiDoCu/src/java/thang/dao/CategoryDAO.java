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
import thang.genobj.Categories;
import thang.genobj.Category;

/**
 *
 * @author Decen
 */
public class CategoryDAO {
    private Connection con;
    private PreparedStatement prestmt;
    private ResultSet rs;

    public CategoryDAO() {
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
    
    public boolean insert(Category category){
        boolean result = false;
        try {
            con = MyConnection.getConnection();
            prestmt = con.prepareStatement("INSERT INTO Category VALUES (?,?)");
            prestmt.setString(1, category.getId());
            prestmt.setString(2, category.getName());
            result = prestmt.execute();
        } catch (Exception e) {
//            e.printStackTrace();
        }finally{
            closeConnection();
        }
        return result;
    }
    
    public Categories getCategories(){
        Categories categories = new Categories();
        Category category = null;
        try {
            con = MyConnection.getConnection();
            prestmt = con.prepareStatement("SELECT * FROM Category");
            rs = prestmt.executeQuery();
            while (rs.next()){
                category = new Category();
                category.setId(rs.getString("id"));
                category.setName(rs.getString("name"));
                categories.getCategory().add(category);
            }
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return categories;
    }
}
