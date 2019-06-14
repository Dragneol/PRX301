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
public class DepartmentDTO implements Serializable{
    private int id;
    private String name;
    private String address;
    private int theaterId;

    public DepartmentDTO(int id, String name, String address, int theaterId) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.theaterId = theaterId;
    }

    public DepartmentDTO(String name, int theaterId) {
        this.name = name;
        this.address = name;
        this.theaterId = theaterId;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(int theaterId) {
        this.theaterId = theaterId;
    }
}
