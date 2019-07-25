package com.volvocars.shuffl.Controller;

import com.volvocars.shuffl.db.LocationEntity;
import com.volvocars.shuffl.db.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Component

public class DBController {

    @Autowired
    private LocationRepository locationRepository;

    public void saveLocation(LocationEntity location){
        locationRepository.save(location);
    }

    public LocationEntity getById(String id){
        return locationRepository.findById(id).orElse(null);
    }

    public void deleteAll(){
        locationRepository.deleteAll();
    }

    public List<LocationEntity> getAllAvaliable(){
        LinkedList<LocationEntity> res = new LinkedList<>();
        Iterable<LocationEntity> entities = locationRepository.findAll();
        entities.forEach(res::add);
        return res.stream().filter(obj -> !obj.isOccupied()).collect(Collectors.toList());
    }
}
