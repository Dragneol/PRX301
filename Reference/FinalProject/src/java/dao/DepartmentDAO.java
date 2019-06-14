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
import jaxb.Department;
import jaxb.Departments;
import utils.DBUtils;
import utils.TextUtils;

/**
 *
 * @author ahhun
 */
public class DepartmentDAO implements Serializable{
    public static void insertIfNotExisted(Department department) throws SQLException {
        PreparedStatement stm = null;
        ResultSet rs = null;
        try (Connection con = DBUtils.makeConnection()) {
            stm = con.prepareStatement("SELECT id FROM Department WHERE normalizedName=? AND theaterId=?");
//            stm.setString(1, department.getName());
            String normalizedName = TextUtils.normalizeVietnameseString(department.getName());
            stm.setString(1, normalizedName);
            stm.setInt(2, department.getTheaterId());
            
            rs = stm.executeQuery();
            
            if (!rs.next()) {
                stm = con.prepareStatement("INSERT INTO Department(name, normalizedName, address, theaterId) "
                    + "VALUES(?, ?, ?, ?)");
                stm.setString(1, department.getName());
                stm.setString(2, normalizedName);
                stm.setString(3, department.getAddress());
                stm.setInt(4, department.getTheaterId());
                
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
    
    public static Department getDepartmentByName(String name, int theaterId) throws SQLException {
        PreparedStatement stm = null;
        ResultSet rs = null;
        Department department = null;
        try (Connection con = DBUtils.makeConnection()) {
            stm = con.prepareStatement("SELECT * FROM Department WHERE normalizedName=? AND theaterId=?");
            stm.setString(1, TextUtils.normalizeVietnameseString(name));
            stm.setInt(2, theaterId);
            
            rs = stm.executeQuery();
            
            if (rs.next()) {
                int id = rs.getInt("id");
                String DBname = rs.getString("name");
                String address = rs.getString("address");
                department = new Department(id, DBname, address, theaterId);
            }
        } catch (NamingException ex) {
            Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            rs.close();
            stm.close();
            return department;
        }
    }
    
    public static Departments getAllDepartments() throws SQLException {
        PreparedStatement stm = null;
        ResultSet rs = null;
        Departments departments = new Departments();
        try (Connection con = DBUtils.makeConnection()) {
            stm = con.prepareStatement("SELECT * FROM Department");
            
            rs = stm.executeQuery();
            
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String address = rs.getString("address");
                int theaterId = rs.getInt("theaterId");
                
                departments.getDepartment().add(new Department(id, name, address, theaterId));
            }
        } catch (NamingException ex) {
            Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            rs.close();
            stm.close();
            return departments;
        }
    }
}
