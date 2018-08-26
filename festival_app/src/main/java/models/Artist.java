package models;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "artists")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Artist {

    private int id;
    private String firstName;
    private String lastName;
    private String manager;
    private double account;
    private List<Performance> performances;

    public Artist() {
    }

    public Artist(String firstName, String lastName, String manager) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.manager = manager;
        this.performances = new ArrayList<>();
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

    @Column(name = "firstName")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "lastName")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "manager")
    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    @Column(name = "account")
    public double getAccount() {
        return account;
    }

    public void setAccount(double account) {
        this.account = account;
    }

    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @ManyToMany
    @JoinTable(name = "artist_performances",
            joinColumns = {@JoinColumn(name = "artist_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "performance_id", nullable = false, updatable = false)})
    public List<Performance> getPerformances() {
        return performances;
    }

    public void setPerformances(List<Performance> performances) {
        this.performances = performances;
    }

    public void addPerformance(Performance performance){
        this.performances.add(performance);
    }

}
