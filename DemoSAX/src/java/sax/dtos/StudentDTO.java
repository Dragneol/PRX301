/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sax.dtos;

import java.io.Serializable;

/**
 *
 * @author dragn
 */
public class StudentDTO implements Serializable {

    private String id, name, pass, status;

    public StudentDTO() {
    }

    public StudentDTO(String id, String name, String pass, String status) {
        this.id = id;
        this.name = name;
        this.pass = pass;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
