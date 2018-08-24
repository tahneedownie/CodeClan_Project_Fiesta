package models;

import interfaces.iPerformable;

import java.sql.Time;
import java.util.List;

public class Performance {

    private int id;
    private Time time;
    private int duration;
    private iPerformable iPerform;
    private LineUp lineup;
    private List<Visitor> visitors;


    public Performance() {
    }

    public Performance(Time time, int duration, iPerformable iPerform, LineUp lineup) {
        this.time = time;
        this.duration = duration;
        this.iPerform = iPerform;
        this.lineup = lineup;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }


    public iPerformable getiPerform() {
        return iPerform;
    }

    public void setiPerform(iPerformable iPerform) {
        this.iPerform = iPerform;
    }

    public LineUp getLineup() {
        return lineup;
    }

    public void setLineup(LineUp lineup) {
        this.lineup = lineup;
    }

    public List<Visitor> getVisitors() {
        return visitors;
    }

    public void setVisitors(List<Visitor> visitors) {
        this.visitors = visitors;
    }

}
