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

    @OneToMany(mappedBy = "venue", fetch = FetchType.EAGER)
    public List<LineUp> getLineUps() {
        return lineUps;
    }

    public void setLineUps(List<LineUp> lineUps) {
        this.lineUps = lineUps;
    }

//    CAN I ADD A VENUE (CAN'T IF ALREADY TAKEN)___________________________________________________________________
    public boolean cantAddSameVenue(){
        List<Venue> venues = DBHelper.getAll(Venue.class);
        for(Venue each_venue : venues){
            if(each_venue.getName().equals(this.name)){
                return true;
            }
        }
        return false;
    }

// ADDING A LINEUP TO A VENUE______________________________________________________________________________________
//    (1) CHECK DATE
// A single venue cannot have multiple line-ups on the same day (one line-up per day)
// SO... A venue has lots of line-ups but can only have one line-up per day
// If the line-up you are trying to add has the same date as one already happening at the venue you cannot add it

    public boolean checkDateTaken(LineUp lineUp){
        LocalDate date = lineUp.getDate();
        for(LineUp each_lineUp : lineUps){
            if(each_lineUp.getDate().equals(date)){
                return true;
            }
        }
        return false;
    }

//    (2) CHECK VENUE
//    A line-up has a set date and so cannot appear at two different venues
//    ... OR the same line-up could be added to two different venues (on the same day)
    public boolean checkVenueTaken(LineUp lineUp){
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

//    (3) YOU CAN ADD LINEUP TO VENUE if date and venue are not already taken
    public boolean addLineUpToVenue(LineUp lineUp) {

        if (!checkDateTaken(lineUp) || !checkVenueTaken(lineUp)) {
                lineUps.add(lineUp);
                return true;
            }
            return false;
        }

    }
