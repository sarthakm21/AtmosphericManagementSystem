/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hp
 */

import java.util.*;
import java.time.LocalDateTime;

public class CentralManager {
    private List<Place> places = new ArrayList<Place>();
    private Map<LocalDateTime, Map<String, Map<String, Integer>>> logData = new HashMap<LocalDateTime, Map<String, Map<String, Integer>>>();

    public CentralManager() {
        Place p1 = new Place("Entrance", 25.431993, 81.770237);
        Place p2 = new Place("TopLeft", 25.432424, 81.770116);
        Place p3 = new Place("TopRight", 25.432150, 81.770746);
        this.places.add(p1);
        this.places.add(p2);
        this.places.add(p3);
    }

    public Map<String, Map<String, Integer>> getData() {
        Map<String, Map<String, Integer>> data = new HashMap<String, Map<String, Integer>>();

        for(Place place: this.places) {
            data.put(place.name, place.getData());
        }
        this.logData.put(LocalDateTime.now(), data);
        return data;
    }

    public Map<LocalDateTime, Map<String, Map<String, Integer>>> getLogs() {
        return this.logData;
    }
}
