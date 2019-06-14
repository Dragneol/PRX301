/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thang.dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import thang.db.MyConnection;
import thang.genobj.User;

/**
 *
 * @author Decen
 */
public class UserDAO {

    private Connection con;
    private PreparedStatement prestmt;
    private ResultSet rs;

    public UserDAO() {
    }

    private void closeConnection() {
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

    public int insert(String username, String password, String name, String address, String email, String phone, int roleId) {
        int result = -1;
        try {
            con = MyConnection.getConnection();
            prestmt = con.prepareStatement("INSERT INTO [User]"
                    + "(username,[password],[name],[address],email,phone,roleId) "
                    + "VALUES (?,?,?,?,?,?,?)",
                    PreparedStatement.RETURN_GENERATED_KEYS);
            prestmt.setString(1, username);
            prestmt.setString(2, password);
            prestmt.setString(3, name);
            prestmt.setString(4, address);
            prestmt.setString(5, email);
            prestmt.setString(6, phone);
            prestmt.setInt(7, roleId);
            prestmt.execute();
            rs = prestmt.getGeneratedKeys();
            rs.next();
            result = rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return result;
    }

    public User checkLogin(String username, String password) {
        User result = null;
        try {
            con = MyConnection.getConnection();
            prestmt = con.prepareStatement("SELECT * FROM [User] WHERE username = ? AND password = ?");
            prestmt.setString(1, username);
            prestmt.setString(2, password);
            rs = prestmt.executeQuery();
            rs.next();
            if (rs.getString("password").equals(password)) {
                result = new User();
                result.setId(new BigInteger(rs.getInt("id") + ""));
                result.setUsername(rs.getString("username"));
                result.setName(rs.getString("name"));
                result.setAddress(rs.getString("address"));
                result.setEmail(rs.getString("email"));
                result.setPhone(rs.getString("phone"));
                result.setRoleId(new BigInteger(rs.getInt("roleId") + ""));
            }
        } catch (Exception e) {
            result = null;
        } finally {
            closeConnection();
        }
        return result;
    }
}
