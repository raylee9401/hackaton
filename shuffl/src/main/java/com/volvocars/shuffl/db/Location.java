/**
 * Copyright (c) 2019 Volvo Car Corporation. All rights reserved.
 *
 * NOTICE: This file contains material that is confidential and proprietary to
 * Volvo Cars and/or other developers. No license is granted under any intellectual or
 * industrial property rights of Volvo Cars except as may be provided in an agreement with
 * Volvo Cars. Any unauthorized copying or distribution of content from this file is prohibited.
 */
package com.volvocars.shuffl.db;

import java.util.List;

public class Location {

    private List<Coordinate> coordinates;

    private boolean isOccupied;

    public Location() {
    }

    public Location(List<Coordinate> coordinates, boolean isOccupied) {
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
