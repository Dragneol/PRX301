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
public class TheaterDTO implements Serializable{
    private int id;
    private String name;
    private String logo;
    private String information;

    public TheaterDTO(int id, String name, String logo, String information) {
        this.id = id;
        this.name = name;
        this.logo = logo;
        this.information = information;
    }

    public TheaterDTO(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }
}
