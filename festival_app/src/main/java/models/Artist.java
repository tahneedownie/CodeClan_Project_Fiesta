package models;

import interfaces.iPerformable;

import javax.persistence.*;

@Entity
@Table(name = "artists")
public abstract class Artist implements iPerformable {

    private int id;
    private String name;
    private String manager;
    private double account;

    public Artist() {
    }

    public Artist(String name, String manager, double account, Performance performance) {
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

    @Override
    public boolean perform() {
        return true;
    }

}
