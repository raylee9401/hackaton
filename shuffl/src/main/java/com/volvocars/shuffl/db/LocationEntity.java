/**
 * Copyright (c) 2019 Volvo Car Corporation. All rights reserved.
 *
 * NOTICE: This file contains material that is confidential and proprietary to
 * Volvo Cars and/or other developers. No license is granted under any intellectual or
 * industrial property rights of Volvo Cars except as may be provided in an agreement with
 * Volvo Cars. Any unauthorized copying or distribution of content from this file is prohibited.
 */
package com.volvocars.shuffl.db;

import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.List;

@Table("location")
public class LocationEntity {

    @Id
    private String id;

    @Column("coordinates")
    private List<String> coordinates;

    @Column("isOccupied")
    private boolean isOccupied;

    public LocationEntity() {
    }

    public LocationEntity(String id, List<String> coordinates, boolean isOccupied) {
        this.id = id;
        this.coordinates = coordinates;
        this.isOccupied = isOccupied;
    }

    public List<String> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<String> coordinates) {
        this.coordinates = coordinates;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    @Override public String toString() {
        return "LocationEntity{" + "coordinates=" + coordinates + ", isOccupied=" + isOccupied + '}';
    }
}
