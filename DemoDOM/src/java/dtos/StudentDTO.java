/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import java.io.Serializable;

/**
 *
 * @author dragn
 */
public class StudentDTO implements Serializable {

    private String id, classrom, lastname, middlename, firstname, status, address;
    private int gender;

    public StudentDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassrom() {
        return classrom;
    }

    public void setClassrom(String classrom) {
        this.classrom = classrom;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public StudentDTO(String id, String classrom, String lastname, String middlename, String firstname, String status, String address, int gender) {
        this.id = id;
        this.classrom = classrom;
        this.lastname = lastname;
        this.middlename = middlename;
        this.firstname = firstname;
        this.status = status;
        this.address = address;
        this.gender = gender;
    }

}
