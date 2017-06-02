package com.starter.model.time;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by adam.wells on 4/06/2016.
 */
@Embeddable
public class TimePeriod implements Serializable {

    public static TimePeriod NOW_AND_FOREVER = new TimePeriod(new Date(), null);

    public TimePeriod() {
    }

    public TimePeriod(Date start, Date end) {
        this.start = start;
        this.end = end;
    }

    @Basic
    @Column(name = "startDate")
    private Date start;

    @Basic
    @Column(name = "endDate")
    private Date end;

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TimePeriod that = (TimePeriod) o;

        if (start != null ? !start.equals(that.start) : that.start != null) return false;
        return end != null ? end.equals(that.end) : that.end == null;

    }

    @Override
    public int hashCode() {
        int result = start != null ? start.hashCode() : 0;
        result = 31 * result + (end != null ? end.hashCode() : 0);
        return result;
    }
}
