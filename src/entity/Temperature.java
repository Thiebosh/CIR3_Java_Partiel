package entity;

import java.util.Date;

public class Temperature implements Comparable {
    private String source;
    private Date dateMesure;
    private Double tempMoyenne;

    public Temperature(String source, Date dateMesure, Double tempMoyenne) {
        this.source = source;
        this.dateMesure = dateMesure;
        this.tempMoyenne = tempMoyenne;
    }

    @Override
    public int compareTo(Object o) {
        return this.getDateMesure().compareTo(((Temperature)o).getDateMesure());
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Date getDateMesure() {
        return dateMesure;
    }

    public void setDateMesure(Date dateMesure) {
        this.dateMesure = dateMesure;
    }

    public Double getTempMoyenne() {
        return tempMoyenne;
    }

    public void setTempMoyenne(Double tempMoyenne) {
        this.tempMoyenne = tempMoyenne;
    }
}
