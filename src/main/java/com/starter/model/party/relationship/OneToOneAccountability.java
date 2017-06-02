package com.starter.model.party.relationship;


import com.starter.model.AbstractEntity;
import com.starter.model.party.Party;
import com.starter.model.time.TimePeriod;

import javax.persistence.Embedded;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * Created by adam.wells on 4/06/2016.
 */
@MappedSuperclass
public abstract class OneToOneAccountability extends AbstractEntity {

    @ManyToOne(targetEntity = Party.class)
    protected Party firstParty;

    @ManyToOne(targetEntity = Party.class)
    protected Party secondParty;

    @Embedded
    private TimePeriod validFor = new TimePeriod(new Date(), null);

    public Party getFirstParty() {
        return firstParty;
    }

    public void setFirstParty(Party firstParty) {
        this.firstParty = firstParty;
    }

    public Party getSecondParty() {
        return secondParty;
    }

    public void setSecondParty(Party secondParty) {
        this.secondParty = secondParty;
    }

    public TimePeriod getValidFor() {
        return validFor;
    }

    public void setValidFor(TimePeriod validFor) {
        this.validFor = validFor;
    }
}
