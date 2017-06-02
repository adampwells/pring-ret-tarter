package com.starter.model.party;

import com.starter.model.AbstractEntity;
import org.hibernate.envers.Audited;

import javax.persistence.*;

/**
 * Created by adam.wells on 3/03/2016.
 */
@Audited
@Entity
@Table(name="j_party", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email"})
})
@Inheritance(strategy = InheritanceType.JOINED)
public class Party extends AbstractEntity {

    @Column(columnDefinition = "TEXT")
    private String mobile;

    @Column(columnDefinition = "TEXT")
    private String phone;

    @Column(columnDefinition = "TEXT")
    private String fax;

    @Column(columnDefinition = "TEXT")
    private String web;

    @Column(columnDefinition = "TEXT")
    private String email;

    @Basic
    private boolean active = true;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
