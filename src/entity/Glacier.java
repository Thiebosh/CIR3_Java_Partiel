package entity;

import java.util.Date;

public class Glacier implements Comparable {
    private Date year;
    private Double deltaMass;

    public Glacier(Date year, Double deltaMass) {
        this.year = year;
        this.deltaMass = deltaMass;
    }

    @Override
    public int compareTo(Object o) {
        return this.getYear().compareTo(((Glacier)o).getYear());
    }

    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
    }

    public Double getDeltaMass() {
        return deltaMass;
    }

    public void setDeltaMass(Double deltaMass) {
        this.deltaMass = deltaMass;
    }
}
