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

    private String id, sClass, lastname, middlename, firstname, gender, password, address, status;

    public StudentDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getsClass() {
        return sClass;
    }

    public void setsClass(String sClass) {
        this.sClass = sClass;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public StudentDTO(String id, String sClass, String lastname, String middlename, String firstname, String gender, String password, String address, String status) {
        this.id = id;
        this.sClass = sClass;
        this.lastname = lastname;
        this.middlename = middlename;
        this.firstname = firstname;
        this.gender = gender;
        this.password = password;
        this.address = address;
        this.status = status;
    }

    @Override
    public String toString() {
        return "StudentDTO{" + "id=" + id + ", sClass=" + sClass + ", lastname=" + lastname + ", middlename=" + middlename + ", firstname=" + firstname + ", gender=" + gender + ", password=" + password + ", address=" + address + ", status=" + status + '}';
    }

}
