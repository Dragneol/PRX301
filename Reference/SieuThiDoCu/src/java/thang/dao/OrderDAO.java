/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thang.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import thang.db.MyConnection;

/**
 *
 * @author Decen
 */
public class OrderDAO {
    private Connection con;
    private PreparedStatement prestmt;
    private ResultSet rs;

    public OrderDAO() {
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
    
    public int insert(int userId, String address, String email, String phone, float totalPrice){
        int orderId = -1;
        try {
            con = MyConnection.getConnection();
            prestmt = con.prepareStatement("INSERT INTO [Order](orderDate,userId,[address],email,phone,totalPrice) "
                    + "VALUES (?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            Date date = new Date(new java.util.Date().getTime());
            prestmt.setDate(1, date);
            if (userId != -1) {
                prestmt.setInt(2, userId);
            } else {
                prestmt.setNull(2, Types.INTEGER);
            }
            prestmt.setString(3, address);
            prestmt.setString(4, email);
            prestmt.setString(5, phone);
            prestmt.setFloat(6, totalPrice);
            prestmt.execute();
            rs = prestmt.getGeneratedKeys();
            rs.next();
            orderId = rs.getInt(1);
            System.out.println(orderId);
        } catch (Exception e) {
//            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return orderId;
    }
    
    public boolean insertOrderDetail(int orderId, int productId, int quantity){
        boolean result = false;
        try {
            con = MyConnection.getConnection();
            prestmt = con.prepareStatement("INSERT INTO OrderDetail(orderId,productId,quantity) VALUES (?,?,?)");
            prestmt.setInt(1, orderId);
            prestmt.setInt(2, productId);
            prestmt.setInt(3, quantity);
            result = prestmt.execute();
        } catch (Exception e) {
//            e.printStackTrace();
        }
        return result;
    }
}
