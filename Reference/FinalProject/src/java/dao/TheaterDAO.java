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
import jaxb.Theater;
import utils.DBUtils;

/**
 *
 * @author ahhun
 */
public class TheaterDAO implements Serializable{
    public static void insertIfNotExisted(Theater theater) throws SQLException {
        PreparedStatement stm = null;
        ResultSet rs = null;
        try (Connection con = DBUtils.makeConnection()) {
            stm = con.prepareStatement("SELECT id FROM Theater WHERE name=?");
            stm.setString(1, theater.getName());
            
            rs = stm.executeQuery();
            
            if (!rs.next()) {
                stm = con.prepareStatement("INSERT INTO Theater(name, logo, information) "
                    + "VALUES(?, ?, ?)");
                stm.setString(1, theater.getName());
                stm.setString(2, theater.getLogo());
                stm.setString(3, theater.getInformation());
                
                stm.executeUpdate();
            }
        } catch (NamingException ex) {
            Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            rs.close();
            stm.close();
        }
    }
    
    public static Theater getTheaterByName(String name) throws SQLException {
        PreparedStatement stm = null;
        ResultSet rs = null;
        Theater theater = null;
        try (Connection con = DBUtils.makeConnection()) {
            stm = con.prepareStatement("SELECT * FROM Theater WHERE name=?");
            stm.setString(1, name);
            
            rs = stm.executeQuery();
            
            if (rs.next()) {
                int id = rs.getInt("id");
                String logo = rs.getString("logo");
                String information = rs.getString("information");
                theater = new Theater(id, name, logo, information);
            }
        } catch (NamingException ex) {
            Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            rs.close();
            stm.close();
            return theater;
        }
    }
}
