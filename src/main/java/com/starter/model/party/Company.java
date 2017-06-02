package com.starter.model.party;

import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Created by adam.wells on 3/03/2016.
 */
@Audited
@Entity
@Table(name="j_company", uniqueConstraints = {
        @UniqueConstraint(columnNames = "legalName")
})
public class Company extends Party {

    @Column(columnDefinition = "TEXT")
    private String legalName;

    @Column(columnDefinition = "TEXT")
    private String tradingName;

    public String getLegalName() {
        return legalName;
    }

    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }

    public String getTradingName() {
        return tradingName;
    }

    public void setTradingName(String tradingName) {
        this.tradingName = tradingName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Company company = (Company) o;
        return com.google.common.base.Objects.equal(legalName, company.legalName);
    }

    @Override
    public int hashCode() {
        return com.google.common.base.Objects.hashCode(super.hashCode(), legalName);
    }
}
