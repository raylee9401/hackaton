package com.volvocars.shuffl.db;

import java.util.List;

public class Location {

    private List<Coordinate> coordinates;

    private boolean isOccupied;

    public Location(){

    }

    public Location( List<Coordinate> coordinates, boolean isOccupied) {
        this.coordinates = coordinates;
        this.isOccupied = isOccupied;
    }

    public List<Coordinate> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Coordinate> coordinates) {
        this.coordinates = coordinates;
    }

    public boolean getIsOccupied() {
        return isOccupied;
    }

    public void setIsOccupied(boolean isOccupied) {
        this.isOccupied = isOccupied;
    }

    @Override public String toString() {
        return "Location{" + "coordinates=" + coordinates + ", isOccupied=" + isOccupied + '}';
    }
}
