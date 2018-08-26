package models;

import javax.persistence.*;
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

}
