package models;

public abstract class Artist {

    private int id;
    private String name;
    private String manager;
    private double account;
    private Act act;

    public Artist() {
    }

    public Artist(String name, String manager, double account, Act act) {
        this.name = name;
        this.manager = manager;
        this.account = account;
        this.act = act;
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

    public Act getAct() {
        return act;
    }

    public void setAct(Act act) {
        this.act = act;
    }

}
