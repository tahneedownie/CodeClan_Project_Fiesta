package models;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "performances")
public class Performance {

    private int id;
    private LocalTime time;
    private int duration;
    private LineUp lineUp;
    private List<Artist> artists;
    private List<Visitor> visitors;


    public Performance() {
    }

    public Performance(LocalTime time, int duration, LineUp lineUp) {
        this.time = time;
        this.duration = duration;
        this.lineUp = lineUp;
        this.artists = new ArrayList<>();
        this.visitors = new ArrayList<>();
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

    @Column
    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    @Column(name = "duration")
    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @ManyToOne
    @JoinColumn(name = "lineUp_id")
//    nullable can be true as performance can exist separate of a line-up
    public LineUp getLineUp() {
        return lineUp;
    }

    public void setLineUp(LineUp lineUp) {
        this.lineUp = lineUp;
    }

    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @ManyToMany
    @JoinTable(name = "performance_artists",
            joinColumns = {@JoinColumn(name = "performance_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "artist_id", nullable = false, updatable = false)})
    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

//    TODO: ADDING ARTISTS TO PERFORMANCES__________________________________________________________________________
//    An artist cannot be added to a performance that is occuring at the same time (performance) on the same day (lineup)
//    The performance can be at a different time as long as it is a different day
//    The performance can be the same day as long as the time of the performance does not overlap

    public void addArtistToPerformance(Artist artist){
        this.artists.add(artist);
    }



    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @ManyToMany
    @JoinTable(name = "performance_visitors",
            joinColumns = {@JoinColumn(name = "performance_id", updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "visitor_id", updatable = false)})
    public List<Visitor> getVisitors() {
        return visitors;
    }

    public void setVisitors(List<Visitor> visitors) {
        this.visitors = visitors;
    }

    public boolean addVisitor(Visitor visitor){
        if (lineUp.getVenue().getVisitorCapacity() < this.visitors.size()){
            this.visitors.add(visitor);
            return true;
        } return false;
    }



}
