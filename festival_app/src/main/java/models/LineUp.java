package models;

import java.util.Date;
import java.util.List;

public class LineUp {

    private int id;
    private Date date;
    private Venue venue;
    private List<Performance> performances;

    public LineUp() {
    }

    public LineUp(Date date, Venue venue) {
        this.date = date;
        this.venue = venue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public List<Performance> getPerformances() {
        return performances;
    }

    public void setPerformances(List<Performance> performances) {
        this.performances = performances;
    }


}
