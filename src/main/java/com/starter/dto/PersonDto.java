package com.starter.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by adam.wells on 2/04/2016.
 */
public class PersonDto implements Serializable {

    private Long personId;
    private String personUid;
    private String firstName;
    private String lastName;
    private String login;
    private String email;
    private String phone;
    private String passwordHash;
    private Date dob;
    private String mobile;
    private String workPhone;
    private Set<String> permissions = new HashSet<>();

    public String findName(){
        return firstName + " " + lastName;
    }

    public String getPhone() {
        return phone;
    }

    public Set<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPersonUid() {
        return personUid;
    }

    public void setPersonUid(String personUid) {
        this.personUid = personUid;
    }

    public String getEmail() {
        return email;

    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public void setWorkPhone(String workPhone) {
        this.workPhone = workPhone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    @Override
    public String toString() {
        return "PersonDto{" +
                "personId=" + personId +
                ", personUid='" + personUid + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", permissions=" + permissions +
                '}';
    }
}
