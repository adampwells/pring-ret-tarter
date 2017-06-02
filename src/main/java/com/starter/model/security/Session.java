package com.starter.model.security;

import com.starter.dto.SessionDto;
import com.starter.model.AbstractEntity;
import com.starter.model.party.Person;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * Created by adam.wells on 3/03/2016.
 */
@Audited
@Entity
@Table(name="j_session")
public class Session extends AbstractEntity {

    @ManyToOne(targetEntity = Person.class)
    private Person person;

    private Date expired = null;

    private String ipAddress;

    @Column(columnDefinition = "TEXT")
    private String userAgent;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> permissions = new HashSet<>();

    public Set<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Date getExpired() {
        return expired;
    }

    public void setExpired(Date expired) {
        this.expired = expired;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public SessionDto asDto(){
        SessionDto dto = new SessionDto();
        dto.setPerson(person.asDto());
        dto.setSessionUid(getUid());
        dto.getPermissions().addAll(permissions);
        return dto;
    }
}
