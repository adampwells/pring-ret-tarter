package com.starter.model.envers;

import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "j_audit")
@RevisionEntity(AuditingRevisionListener.class)
@Entity
public class AuditedRevisionEntity extends DefaultRevisionEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = "loggedInUser")
    private String user;

    private Date changeDate = new Date();

    public Date getChangeDate() {
        return changeDate;
    }

    public String getUser() {

        return user;
    }

    public void setUser(String user) {

        this.user = user;
    }
}
