package com.example.schedulingtasks;

import java.util.Date;

public class PackageInfo {

    public PackageInfo(String version, String name, String author, Date lastUpdate, int state) {
        this.version = version;
        this.name = name;
        this.author  = author;
        this.lastUpdate = lastUpdate;
        State = state;
    }

    private String version ;
    private String name;
    private String author;
    private Date lastUpdate;
    private int State;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public int getState() {
        return State;
    }

    public void setState(int state) {
        State = state;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
