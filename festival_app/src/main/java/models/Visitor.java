package models;

import java.util.ArrayList;
import java.util.List;

public class Visitor {

    private int id;
    private String firstName;
    private String lastName;
    private List<Ticket> tickets;

    public Visitor() {
    }

    public Visitor(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.tickets = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

}
