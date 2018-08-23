package models;

import java.util.ArrayList;
import java.util.List;

public class Venue {

    private int id;
    private String name;
    private String location;
    private int visitorCapacity;
    private List<Act> acts;

    public Venue() {
    }

    public Venue(String name, String location, int visitorCapacity) {
        this.name = name;
        this.location = location;
        this.visitorCapacity = visitorCapacity;
        this.acts = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getVisitorCapacity() {
        return visitorCapacity;
    }

    public void setVisitorCapacity(int visitorCapacity) {
        this.visitorCapacity = visitorCapacity;
    }

    public List<Act> getActs() {
        return acts;
    }

    public void setActs(List<Act> acts) {
        this.acts = acts;
    }

}
