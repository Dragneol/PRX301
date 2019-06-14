/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author ahhun
 */
public class MovieScheduleDTO implements Serializable{
    private int movieId;
    private int departmentId;
    private Date date;
    private String showingTimeList;
    private String bookingUrl;

    public MovieScheduleDTO(int movieId, int departmentId, Date date, String showingTimeList, String bookingUrl) {
        this.movieId = movieId;
        this.departmentId = departmentId;
        this.date = date;
        this.showingTimeList = showingTimeList;
        this.bookingUrl = bookingUrl;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getShowingTimeList() {
        return showingTimeList;
    }

    public void setShowingTimeList(String showingTimeList) {
        this.showingTimeList = showingTimeList;
    }

    public String getBookingUrl() {
        return bookingUrl;
    }

    public void setBookingUrl(String bookingUrl) {
        this.bookingUrl = bookingUrl;
    }
}
