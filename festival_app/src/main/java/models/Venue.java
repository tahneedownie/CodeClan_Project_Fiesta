package models;

import db.DBHelper;
import db.DBVenue;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "venues")
public class Venue {

    private int id;
    private String name;
    private String location;
    private int visitorCapacity;
    private List<LineUp> lineUps;

    public Venue() {
    }

    public Venue(String name, String location, int visitorCapacity) {
        this.name = name;
        this.location = location;
        this.visitorCapacity = visitorCapacity;
        this.lineUps = new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "location")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Column(name = "capacity")
    public int getVisitorCapacity() {
        return visitorCapacity;
    }

    public void setVisitorCapacity(int visitorCapacity) {
        this.visitorCapacity = visitorCapacity;
    }

    @OneToMany(mappedBy = "venue", fetch = FetchType.LAZY)
    public List<LineUp> getLineUps() {
        return lineUps;
    }

    public void setLineUps(List<LineUp> lineUps) {
        this.lineUps = lineUps;
    }

    public boolean isThereADateAlready(LineUp lineUp){
        LocalDate potentialDate = lineUp.getDate();
        for(LineUp each_lineUp : lineUps){
            if(each_lineUp.getDate().equals(potentialDate)){
                return true;
            }
        }
        return false;
    }

    public boolean isThisLineupAlreadyAtaVenue(LineUp lineUp){
        List<Venue> allVenues = DBHelper.getAll(Venue.class);
        for(Venue each_venue: allVenues){
            for(LineUp each_lineup : each_venue.getLineUps()){
                if(each_lineup.equals(lineUp)){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean addLineUpToVenue(LineUp lineUp){
//        A venue cannot have multiple lineups on same date
//        A venue has lots of lineups but only one lineUp per date
//        get array list of lineup dates
//        if the lineup you are trying to add has the same date you cannot add it
        if(!isThereADateAlready(lineUp) || isThisLineupAlreadyAtaVenue(lineUp)){
            lineUps.add(lineUp);
            return true;
        }
        return false;
    }

}
