/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.xml.datatype.XMLGregorianCalendar;
import jaxb.MovieSchedule;
import jaxb.MovieSchedules;
import utils.DBUtils;
import utils.DataUtils;

/**
 *
 * @author ahhun
 */
public class MovieScheduleDAO implements Serializable{
    public static void insertOrUpdate(MovieSchedule movieSchedule) throws SQLException {
        PreparedStatement stm = null;
        ResultSet rs = null;
        try (Connection con = DBUtils.makeConnection()) {
            Date sqlDate = new Date(movieSchedule.getDate().toGregorianCalendar().getTimeInMillis());
            String showingTimeList = String.join(",", movieSchedule.getShowingTimeList().getTime());
            stm = con.prepareStatement("SELECT * FROM MovieSchedule WHERE movieId=? AND departmentId=? AND date=?");
            stm.setInt(1, movieSchedule.getMovieId());
            stm.setInt(2, movieSchedule.getDepartmentId());
            stm.setDate(3, sqlDate);
            
            rs = stm.executeQuery();
            
            if (!rs.next()) {
                stm = con.prepareStatement("INSERT INTO MovieSchedule(movieId, departmentId, date, "
                        + "showingTimeList, bookingUrl) VALUES(?, ?, ?, ?, ?)");
                stm.setInt(1, movieSchedule.getMovieId());
                stm.setInt(2, movieSchedule.getDepartmentId());
                stm.setDate(3, sqlDate);
                stm.setString(4, showingTimeList);
                stm.setString(5, movieSchedule.getBookingUrl());
                
                stm.executeUpdate();
            } else {
                stm = con.prepareStatement("UPDATE MovieSchedule SET showingTimeList=?, bookingUrl=? "
                        + "WHERE movieId=? AND departmentId=? AND date=?");
                stm.setString(1, showingTimeList);
                stm.setString(2, movieSchedule.getBookingUrl());
                stm.setInt(3, movieSchedule.getMovieId());
                stm.setInt(4, movieSchedule.getDepartmentId());
                stm.setDate(5, sqlDate);
                
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
    
    public static MovieSchedules getMovieSchedulesById(int id, java.util.Date date) throws SQLException {
        PreparedStatement stm = null;
        ResultSet rs = null;
        MovieSchedules movieSchedules = new MovieSchedules();
        try (Connection con = DBUtils.makeConnection()) {
            Date sqlDate = DataUtils.convertDateUtilToSQLDate(date);
            stm = con.prepareStatement("SELECT * FROM MovieSchedule WHERE movieId=? AND date=?");
            stm.setInt(1, id);
            stm.setDate(2, sqlDate);
            
            rs = stm.executeQuery();
            
            while (rs.next()) {
                int departmentId = rs.getInt("departmentId");
                String showingTimeList = rs.getString("showingTimeList");
                String bookingUrl = rs.getString("bookingUrl");
                
                MovieSchedule.ShowingTimeList timeList = new MovieSchedule.ShowingTimeList();
                timeList.getTime().addAll(Arrays.asList(showingTimeList.split(",")));
                
                XMLGregorianCalendar xmlDate = DataUtils.convertDateUtilToXMLDate(date);
                
                movieSchedules.getMovieSchedule().add(new MovieSchedule(id, departmentId, xmlDate, timeList, bookingUrl));
            }
        } catch (NamingException ex) {
            Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            rs.close();
            stm.close();
            return movieSchedules;
        }
    }
    
    public static MovieSchedules getMovieSchedulesByDepartment(int departmentId, java.util.Date date) throws SQLException {
        PreparedStatement stm = null;
        ResultSet rs = null;
        MovieSchedules movieSchedules = new MovieSchedules();
        try (Connection con = DBUtils.makeConnection()) {
            Date sqlDate = DataUtils.convertDateUtilToSQLDate(date);
            stm = con.prepareStatement("SELECT * FROM MovieSchedule WHERE departmentId=? AND date>=?");
            stm.setInt(1, departmentId);
            stm.setDate(2, sqlDate);
            
            rs = stm.executeQuery();
            
            while (rs.next()) {
                int movieId = rs.getInt("movieId");
                String showingTimeList = rs.getString("showingTimeList");
                String bookingUrl = rs.getString("bookingUrl");
                Date scheduleDate = rs.getDate("date");
                
                MovieSchedule.ShowingTimeList timeList = new MovieSchedule.ShowingTimeList();
                timeList.getTime().addAll(Arrays.asList(showingTimeList.split(",")));
                
                XMLGregorianCalendar xmlDate = DataUtils.convertDateUtilToXMLDate(scheduleDate);
                
                movieSchedules.getMovieSchedule().add(new MovieSchedule(movieId, departmentId, xmlDate, timeList, bookingUrl));
            }
        } catch (NamingException ex) {
            Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            rs.close();
            stm.close();
            return movieSchedules;
        }
    }
}
