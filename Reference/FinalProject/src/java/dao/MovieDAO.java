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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import jaxb.Movie;
import jaxb.Movies;
import utils.DBUtils;

/**
 *
 * @author ahhun
 */
public class MovieDAO implements Serializable{
    public static void insertIfNotExisted(Movie movie) throws SQLException {
        PreparedStatement stm = null;
        ResultSet rs = null;
        try (Connection con = DBUtils.makeConnection()) {
            stm = con.prepareStatement("SELECT id FROM Movie WHERE EnglishName=? OR VietnameseName=?");
            stm.setString(1, movie.getEnglishName());
            stm.setString(2, movie.getVietnameseName());
            
            rs = stm.executeQuery();
            
            if (!rs.next()) {
                stm = con.prepareStatement("INSERT INTO Movie(VietnameseName, EnglishName, "
                    + "description, duration, director, stars, category, languages, type, imageUrl) "
                    + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                stm.setString(1, movie.getVietnameseName());
                stm.setString(2, movie.getEnglishName());
                stm.setString(3, movie.getDescription());
                stm.setInt(4, movie.getDuration());
                stm.setString(5, movie.getDirector());
                stm.setString(6, movie.getStars());
                stm.setString(7, movie.getCategory());
                stm.setString(8, movie.getLanguages());
                stm.setString(9, movie.getType());
                stm.setString(10, movie.getImageUrl());
                
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
    
    public static Movie getMovieByName(String EngName, String VnName) throws SQLException {
        PreparedStatement stm = null;
        ResultSet rs = null;
        Movie movie = null;
        try (Connection con = DBUtils.makeConnection()) {
            stm = con.prepareStatement("SELECT * FROM Movie WHERE EnglishName=? OR VietnameseName=?");
            stm.setString(1, EngName);
            stm.setString(2, VnName);
            
            rs = stm.executeQuery();
            
            if (rs.next()) {
                int id = rs.getInt("id");
                String VietnameseName = rs.getString("VietnameseName");
                String EnglishName = rs.getString("EnglishName");
                String description = rs.getString("description");
                int duration = rs.getInt("duration");
                String director = rs.getString("director");
                String stars = rs.getString("stars");
                String category = rs.getString("category");
                String languages = rs.getString("languages");
                String type = rs.getString("type");
                String imageUrl = rs.getString("imageUrl");
                movie = new Movie(id, VietnameseName, EnglishName, description, duration, director, stars, category, languages, type, imageUrl);
            }
        } catch (NamingException ex) {
            Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            rs.close();
            stm.close();
            return movie;
        }
    }
    
    public static Movies getListMovieByDate(Date date) throws SQLException {
        PreparedStatement stm = null;
        ResultSet rs = null;
        Movies movies = new Movies();
        try (Connection con = DBUtils.makeConnection()) {
            stm = con.prepareStatement("SELECT * FROM Movie WHERE id IN "
                    + "(SELECT movieId FROM MovieSchedule WHERE MovieSchedule.date >= ? GROUP BY movieId)");
            stm.setDate(1, date);
            
            rs = stm.executeQuery();
            
            while (rs.next()) {
                int id = rs.getInt("id");
                String VietnameseName = rs.getString("VietnameseName");
                String EnglishName = rs.getString("EnglishName");
                String description = rs.getString("description");
                int duration = rs.getInt("duration");
                String director = rs.getString("director");
                String stars = rs.getString("stars");
                String category = rs.getString("category");
                String languages = rs.getString("languages");
                String type = rs.getString("type");
                String imageUrl = rs.getString("imageUrl");
                movies.getMovie().add(new Movie(id, VietnameseName, EnglishName, description, duration, director, stars, category, languages, type, imageUrl));
            }
        } catch (NamingException ex) {
            Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MovieDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            rs.close();
            stm.close();
            return movies;
        }
    }
}
