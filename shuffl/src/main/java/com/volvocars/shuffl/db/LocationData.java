package com.volvocars.shuffl.db;

import java.util.List;

public class LocationData {
    private List<Location> locations;

    public LocationData(){

    }

    public LocationData(List<Location> locations) {
        this.locations = locations;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    @Override public String toString() {
        return "LocationData{" + "locations=" + locations + '}';
    }
}
