/**
 * Copyright (c) 2019 Volvo Car Corporation. All rights reserved.
 *
 * NOTICE: This file contains material that is confidential and proprietary to
 * Volvo Cars and/or other developers. No license is granted under any intellectual or
 * industrial property rights of Volvo Cars except as may be provided in an agreement with
 * Volvo Cars. Any unauthorized copying or distribution of content from this file is prohibited.
 */
package com.volvocars.shuffl.Controller;

import com.volvocars.shuffl.db.LocationEntity;
import com.volvocars.shuffl.db.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DBController {

    @Autowired
    private LocationRepository locationRepository;

    public void saveLocation(LocationEntity location) {
        locationRepository.save(location);
    }

    public LocationEntity getById(String id) {
        return locationRepository.findById(id).orElse(null);
    }

    public void deleteAll() {
        locationRepository.deleteAll();
    }

    public List<LocationEntity> getAllAvailable() {
        LinkedList<LocationEntity> res = new LinkedList<>();
        Iterable<LocationEntity> entities = locationRepository.findAll();
        entities.forEach(res::add);
        return res.stream().filter(obj -> !obj.isOccupied()).collect(Collectors.toList());
    }
}
