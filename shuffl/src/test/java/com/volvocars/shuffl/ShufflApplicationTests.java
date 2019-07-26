/**
 * Copyright (c) 2019 Volvo Car Corporation. All rights reserved.
 *
 * NOTICE: This file contains material that is confidential and proprietary to
 * Volvo Cars and/or other developers. No license is granted under any intellectual or
 * industrial property rights of Volvo Cars except as may be provided in an agreement with
 * Volvo Cars. Any unauthorized copying or distribution of content from this file is prohibited.
 */
package com.volvocars.shuffl;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.volvocars.shuffl.Controller.DBController;
import com.volvocars.shuffl.boot.ShufflApplication;
import com.volvocars.shuffl.db.Coordinate;
import com.volvocars.shuffl.db.Location;
import com.volvocars.shuffl.db.LocationData;
import com.volvocars.shuffl.db.LocationEntity;
import com.volvocars.shuffl.db.LocationRepository;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ShufflApplication.class, DBController.class})
public class ShufflApplicationTests {

    @Autowired
    private DBController dbController;

    @Autowired
    private LocationRepository locationRepository;

    @Test
    public void saveTest() throws Exception {
        locationRepository.deleteAll();
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS, true);
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        LocationData locationData = mapper.readValue(ClassLoader.getSystemResourceAsStream("locationData.json"), LocationData.class);
        for (Location location : locationData.getLocations()) {
            String str = calculateCenterPoint(location);
            LocationEntity locationEntity = new LocationEntity(str, null, location.getIsOccupied());
            List<String> list = new ArrayList<>(8);
            for (Coordinate coordinate : location.getCoordinates()) {
                list.add(coordinate.getLatitude().toString());
                list.add(coordinate.getLongitude().toString());
            }
            locationEntity.setCoordinates(list);
            locationEntity.setId(str);
            dbController.saveLocation(locationEntity);
        }
    }

    @Test
    public void convertTest() throws Exception {
        locationRepository.deleteAll();
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS, true);
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Location location = mapper.readValue(ClassLoader.getSystemResourceAsStream("location.json"), Location.class);
        String str = calculateCenterPoint(location);
        LocationEntity locationEntity = new LocationEntity(str, null, location.getIsOccupied());
        List<String> list = new ArrayList<>(8);
        for (Coordinate coordinate : location.getCoordinates()) {
            list.add(coordinate.getLatitude().toString());
            list.add(coordinate.getLongitude().toString());
        }
        locationEntity.setCoordinates(list);

        locationEntity.setId(str);
        dbController.saveLocation(locationEntity);
        LocationEntity entity = dbController.getById(str);
        Location result = new Location(null, entity.isOccupied());
        List<Coordinate> coordinates = new ArrayList<>();
        int size = locationEntity.getCoordinates().size() / 2;
        for (int i = 0; i < size; i++) {
            Coordinate coordinate = new Coordinate(Double.valueOf(entity.getCoordinates().get(2*i)), Double.valueOf(entity.getCoordinates().get(2*i+1)));
            coordinates.add(coordinate);
        }
        result.setCoordinates(coordinates);
    }

    @Test
    @Ignore
    public void deleteData() {
        locationRepository.deleteAll();
    }

    public String calculateCenterPoint(Location location) {
        Double x = 0.0;
        Double y = 0.0;
        for (Coordinate coordinate : location.getCoordinates()) {
            x += coordinate.getLongitude();
            y += coordinate.getLatitude();
        }
        x /= 4;
        y /= 4;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(x);
        stringBuilder.append(",");
        stringBuilder.append(y);
        System.out.println(stringBuilder.toString());  // TODO - ideally should later use logging here
        return stringBuilder.toString();
    }
}
