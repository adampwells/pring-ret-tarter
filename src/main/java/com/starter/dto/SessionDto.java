package com.starter.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by adam.wells on 18/06/2016.
 */
public class SessionDto implements Serializable{

    private String sessionUid;
    private PersonDto person;
    private Set<String> permissions = new HashSet<>();

    public String getSessionUid() {
        return sessionUid;
    }

    public void setSessionUid(String sessionUid) {
        this.sessionUid = sessionUid;
    }

    public PersonDto getPerson() {
        return person;
    }

    public void setPerson(PersonDto person) {
        this.person = person;
    }

    public Set<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return "SessionDto{" +
                "sessionUid='" + sessionUid + '\'' +
                ", person=" + person +
                ", permissions=" + permissions +
                '}';
    }
}
