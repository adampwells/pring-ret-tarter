package com.starter.model;

/*
 * Copyright (c) 2016. Adam Wells
 */

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@MappedSuperclass
@EntityListeners({AbstractEntity.AbstractEntityListener.class})
public abstract class AbstractEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    /* "UUID" and "UID" are Oracle reserved keywords -> "ENTITY_UID" */
    @Column(name = "ENTITY_UID", unique = true, nullable = false, updatable = false, length = 36)
    private String uid;

    @Version()
    private Long version = null;

    @Basic
    private String createdBy;

    @Basic
    private String lastUpdatedBy;

    @Basic
    protected Date createDate = new Date();

    @Basic
    private Date lastUpdate = new Date();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    @Override
    public boolean equals(Object o) {
        return (o == this || (o instanceof AbstractEntity && uid().equals(
                ((AbstractEntity) o).uid())));
    }

    @Override
    public int hashCode() {
        return uid().hashCode();
    }

    public static class AbstractEntityListener {

        @PrePersist
        public void onPrePersist(AbstractEntity abstractEntity) {

            abstractEntity.uid();

            try {
                String userName = "System";

                SecurityContext context = SecurityContextHolder.getContext();

                if (context != null) {
                    Authentication authentication = context.getAuthentication();
                    if (authentication != null) {
                        userName = authentication.getName();
                    }
                }

                if (abstractEntity.getCreatedBy() == null) {
                    abstractEntity.setCreatedBy(userName);
                }

                abstractEntity.setLastUpdatedBy(userName);

            } catch (Exception e) {
                e.printStackTrace();
            }

            if (abstractEntity.createDate == null) abstractEntity.setCreateDate(new Date());
            abstractEntity.lastUpdate = new Date();
        }
    }

    private String uid() {
        if (uid == null)
            uid = UUID.randomUUID().toString();
        return uid;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUid() {
        return uid;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }
}
