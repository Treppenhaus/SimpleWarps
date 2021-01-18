package eu.treppi.simplewarps.utils;

import org.bukkit.Location;

public class Warp {
    String name, creator;
    Location location;
    long creationTime;


    public Warp(String name, Location location, String creator, long creationTime) {
        this.name = name;
        this.location = location;
        this.creator = creator;
        this.creationTime = creationTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public long getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(long creationTime) {
        this.creationTime = creationTime;
    }
}
