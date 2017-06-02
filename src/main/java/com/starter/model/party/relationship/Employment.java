package com.starter.model.party.relationship;

import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by adam.wells on 4/06/2016.
 */
@Audited
@Entity
@Table(name="j_employment")
public class Employment extends OneToOneAccountability{

    private String position;

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
