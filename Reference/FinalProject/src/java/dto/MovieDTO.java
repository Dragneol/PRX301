/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;

/**
 *
 * @author ahhun
 */
public class MovieDTO implements Serializable{
    private int id;
    private String VietnameseName;
    private String EnglishName;
    private String description;
    private int duration;
    private String director;
    private String stars;
    private String category;
    private String languages;
    private String type;

    public MovieDTO(int id, String VietnameseName, String EnglishName, String description, int duration, String director, String stars, String category, String languages, String type) {
        this.id = id;
        this.VietnameseName = VietnameseName;
        this.EnglishName = EnglishName;
        this.description = description;
        this.duration = duration;
        this.director = director;
        this.stars = stars;
        this.category = category;
        this.languages = languages;
        this.type = type;
    }

    public MovieDTO(String VietnameseName, String EnglishName, String description, int duration, String director, String stars, String category, String languages, String type) {
        this.VietnameseName = VietnameseName;
        this.EnglishName = EnglishName;
        this.description = description;
        this.duration = duration;
        this.director = director;
        this.stars = stars;
        this.category = category;
        this.languages = languages;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVietnameseName() {
        return VietnameseName;
    }

    public void setVietnameseName(String VietnameseName) {
        this.VietnameseName = VietnameseName;
    }

    public String getEnglishName() {
        return EnglishName;
    }

    public void setEnglishName(String EnglishName) {
        this.EnglishName = EnglishName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
