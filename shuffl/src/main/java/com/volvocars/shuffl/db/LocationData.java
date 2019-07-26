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

public class LocationData {
    private List<Location> locations;

    public LocationData() {
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
