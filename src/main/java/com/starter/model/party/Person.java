package com.starter.model.party;

import com.starter.dto.PersonDto;
import com.starter.model.document.Document;
import org.hibernate.envers.Audited;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * Created by adam.wells on 3/03/2016.
 */
@Audited
@Entity
@Table(name = "j_person", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"login"})
})
public class Person extends Party {

    @Column(columnDefinition = "TEXT")
    private String firstName;

    @Column(columnDefinition = "TEXT")
    private String lastName;

    @Column(columnDefinition = "TEXT")
    private String login;

    @Column(columnDefinition = "TEXT")
    private String mobile;

    @Column(columnDefinition = "TEXT")
    private String workPhone;

    @Column(columnDefinition = "TEXT")
    private String passwordHash;

    @ManyToOne(targetEntity = Document.class)
    private Document photo;

    private Date dob;

    @Override
    public String getMobile() {
        return mobile;
    }

    @Override
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public void setWorkPhone(String workPhone) {
        this.workPhone = workPhone;
    }

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> permissions = new HashSet<>();

    public String findName(){
        return firstName + " " + lastName;
    }

    public Set<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }

    public Document getPhoto() {
        return photo;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public void setPhoto(Document photo) {
        this.photo = photo;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getFirstName() {
        return firstName;
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

    public void setPassword(String passwordClear) {
        this.passwordHash = BCrypt.hashpw(passwordClear, BCrypt.gensalt());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return com.google.common.base.Objects.equal(login, person.login);
    }

    @Override
    public int hashCode() {
        return com.google.common.base.Objects.hashCode(super.hashCode(), login);
    }

    public PersonDto asDto(){
        PersonDto dto = new PersonDto();
        dto.setFirstName(firstName);
        dto.setLastName(lastName);
        dto.setLogin(login);
        dto.setEmail(getEmail());
        dto.setPhone(getPhone());
        dto.setPersonId(this.getId());
        dto.setPersonUid(getUid());
        dto.getPermissions().addAll(permissions);
        dto.setDob(dob);
        dto.setMobile(mobile);
        dto.setWorkPhone(workPhone);
        return dto;
    }
}
