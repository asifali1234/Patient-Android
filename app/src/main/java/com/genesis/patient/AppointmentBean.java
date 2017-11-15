package com.genesis.patient;

import java.io.Serializable;

/**
 * Created by asif on 14/11/17.
 */

public class AppointmentBean implements Serializable {
    
    private PatientBean pb;
    private String dateDay;
    private String dateMonth;
    private String dateYear;
    private String doctorid;
    private String dateHourStart;
    private String dateMinutesStart;
    private String dateHourEnd;
    private String dateMinutesEnd;

    public PatientBean getPb() {
        return pb;
    }

    public void setPb(PatientBean pb) {
        this.pb = pb;
    }

    public String getDateDay() {
        return dateDay;
    }

    public void setDateDay(String dateDay) {
        this.dateDay = dateDay;
    }

    public String getDateMonth() {
        return dateMonth;
    }

    public void setDateMonth(String dateMonth) {
        this.dateMonth = dateMonth;
    }

    public String getDateYear() {
        return dateYear;
    }

    public void setDateYear(String dateYear) {
        this.dateYear = dateYear;
    }

    public String getDoctorid() {
        return doctorid;
    }

    public void setDoctorid(String doctorid) {
        this.doctorid = doctorid;
    }


    public String getDateHourStart() {
        return dateHourStart;
    }

    public void setDateHourStart(String dateHourStart) {
        this.dateHourStart = dateHourStart;
    }

    public String getDateMinutesStart() {
        return dateMinutesStart;
    }

    public void setDateMinutesStart(String dateMinutesStart) {
        this.dateMinutesStart = dateMinutesStart;
    }

    public String getDateHourEnd() {
        return dateHourEnd;
    }

    public void setDateHourEnd(String dateHourEnd) {
        this.dateHourEnd = dateHourEnd;
    }

    public String getDateMinutesEnd() {
        return dateMinutesEnd;
    }

    public void setDateMinutesEnd(String dateMinutesEnd) {
        this.dateMinutesEnd = dateMinutesEnd;
    }
}
