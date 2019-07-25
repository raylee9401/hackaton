package com.volvocars.shuffl.Controller;

import com.volvocars.shuffl.db.Coordinate;
import com.volvocars.shuffl.db.Location;
import com.volvocars.shuffl.db.LocationData;
import com.volvocars.shuffl.db.LocationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class DataController {

    @Autowired
    private DBController dbController;

    @RequestMapping(value = "/push", method = RequestMethod.POST)
    public void pushOneData(@RequestBody Location location){
        dbController.saveLocation(convert(location));
    }

    @RequestMapping(value = "/pushList", method = RequestMethod.POST)
    public void pushListData(@RequestBody LocationData locationData){
        for (Location location : locationData.getLocations()){
            dbController.saveLocation(convert(location));
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public void pushOneData(){
        dbController.deleteAll();
    }

    @RequestMapping(value = "/pull/{latitude}/{longitude}/{meters}", method = RequestMethod.GET)
    public List<Location> getParkingSpots(@PathVariable Double latitude, @PathVariable Double longitude, @PathVariable Double meters){
        List<LocationEntity> list = dbController.getAllAvaliable();
        List<Location> res = new LinkedList<>();
        List<LocationEntity> filterList;
        final Double meter = meters / 111000;
        filterList =  list.stream().filter(locationEntity -> {
            String[] lalongtitude = locationEntity.getId().split(",");
            Double lati = Double.valueOf(lalongtitude[0]);
            Double longi = Double.valueOf(lalongtitude[1]);
            Double lati_dif = Math.abs(lati - latitude);
            Double longi_dif = Math.abs(longi - longitude);
            Double hypo = Math.hypot(lati_dif, longi_dif);
            System.out.println(hypo);
            if ( hypo <= meter){
                return true;
            } else return false;
        }).collect(Collectors.toList());

        filterList.stream().forEach(locationEntity -> {
            Location result = new Location(null, locationEntity.isOccupied());
            List<Coordinate> coordinates = new ArrayList<>();
            int size = locationEntity.getCoordinates().size() / 2;
            for (int i = 0; i < size; i++){
                Coordinate coordinate = new Coordinate(Double.valueOf(locationEntity.getCoordinates().get(2*i)), Double.valueOf(locationEntity.getCoordinates().get(2*i+1)));
                coordinates.add(coordinate);
            }
            result.setCoordinates(coordinates);
            res.add(result);
        });

       return res;
    }

    private LocationEntity convert(Location location){
        String str = calculateCenterPoint(location);
        LocationEntity locationEntity = new LocationEntity(str, null, location.getIsOccupied());
        List<String> list = new ArrayList<>(8);
        for (Coordinate coordinate : location.getCoordinates()){
            list.add(coordinate.getLatitude().toString());
            list.add(coordinate.getLongitude().toString());
        }
        locationEntity.setCoordinates(list);
        locationEntity.setId(str);
        return locationEntity;
    }

    private String calculateCenterPoint(Location location){
        Double x = 0.0;
        Double y = 0.0;
        for (Coordinate coordinate : location.getCoordinates()){
            x += coordinate.getLongitude();
            y += coordinate.getLatitude();
        }
        x /=4;
        y /= 4;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(y);
        stringBuilder.append(",");
        stringBuilder.append(x);
        return stringBuilder.toString();
    }
}
