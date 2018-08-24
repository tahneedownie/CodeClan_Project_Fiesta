package models;

import interfaces.iPerformable;

import java.util.List;

public abstract class Group implements iPerformable {

    private int id;
    private String name;
    private String manager;
    private double account;
    private List<Artist> artists;

    public Group() {
    }

    public Group(String name, String manager, double account) {
        this.name = name;
        this.manager = manager;
        this.account = account;
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

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public double getAccount() {
        return account;
    }

    public void setAccount(double account) {
        this.account = account;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

}
