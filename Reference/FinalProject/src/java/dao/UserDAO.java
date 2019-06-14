/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import utils.DBUtils;

/**
 *
 * @author ahhun
 */
public class UserDAO implements Serializable{
    private static final int ROLE_ADMIN = 1;
    
    public static String logInAsAdmin(String username, String password) throws SQLException {
        PreparedStatement stm = null;
        ResultSet rs = null;
        try (Connection con = DBUtils.makeConnection()) {
            stm = con.prepareStatement("SELECT name FROM [User] WHERE username=? AND password=? AND role=?");
            stm.setString(1, username);
            stm.setString(2, password);
            stm.setInt(3, ROLE_ADMIN);
            
            rs = stm.executeQuery();
            
            if (rs.next()) {
                String name = rs.getString("name");
                
                return name;
            }
        } catch (NamingException ex) {
            Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            rs.close();
            stm.close();
        }
        return null;
    }
}
