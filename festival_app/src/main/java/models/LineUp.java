package models;

import db.DBHelper;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "lineups")
public class LineUp {

    private int id;
    private String description;
    private LocalDate date;
    private Venue venue;
    private List<Performance> performances;

    public LineUp() {
    }

    public LineUp(String description, LocalDate date) {
        this.description = description;
        this.date = date;
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

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "date")
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @ManyToOne
    @JoinColumn(name = "venue_id")
    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    @OneToMany(mappedBy = "lineUp", fetch = FetchType.LAZY)
    public List<Performance> getPerformances() {
        return performances;
    }

    public void setPerformances(List<Performance> performances) {
        this.performances = performances;
    }

    // ADDING A PERFORMANCE TO A LINEUP______________________________________________________________________________________
//    (1) CHECK TIME
// A lineUP cannot have performances starting at the same time
// A Line-Up cannot have another performance starting while another is still playing (duration)
// SO... A lineup has lots of performance but can only have one per time slot
// If the performance you are trying to add has the same time as one already happening in the line up you cannot add it

    public boolean checkTimeSlotTaken(Performance performance){
        LocalTime startTime = performance.getTime();
        LocalTime endTime = performance.getTime().plusMinutes(performance.getDuration());
        for(Performance each_performance : performances){
            if(each_performance.getTime().equals(startTime) || each_performance.getTime().isBefore(endTime))
            {
                return true;
            }
        }
        return false;
    }

    //    (2) CHECK LINEUP CLASH
//    A Performance has a set time and so cannot be added to two different lineups on the same day at the same time
//    ... OR the same performance could be added to two different lineups (on the same day)
//    So check all line ups for their performances to make sure that the performance hasn't already been added to another line up on the same day
    public boolean checkLineUpTaken(Performance performance){
        List<LineUp> allLineUps = DBHelper.getAll(LineUp.class);
        for(LineUp each_lineup: allLineUps){
            for(Performance each_performance : each_lineup.getPerformances()){
//                TODO: CHECK THE LOGIC IN LINE BELOW
                if(each_performance.equals(performance) || each_lineup.getDate().equals(date) ){
                    return true;
                }
            }
        }
        return false;
    }

    //    (3) YOU CAN ADD PERFORMANCE TO LINEUP if time and lineup(date) are not already taken
    public boolean addPerformance(Performance performance){
        if(!checkTimeSlotTaken(performance) || !checkLineUpTaken(performance)){
            this.performances.add(performance);
            return true;
        }
        return false;
    }

//    Get the start time of a lineup from the start time of its first performance

    public LocalTime getLineUpStartTime(){
        for(Performance each_performance: this.performances){
            for(Performance each_performance : each_lineup.getPerformances()){
//                TODO: CHECK THE LOGIC IN LINE BELOW
                if(each_performance.equals(performance) || each_lineup.getDate().equals(date) ){
                    return true;
                }
            }



    }


//    Get the end time of a lineup from the starttime+duration of its last performance

//    Get the number of artists attributed to a LineUp through its performances





}
