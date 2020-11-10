package airmanagement;

import java.util.*;
import java.time.LocalDateTime;

public class CentralManager {
    private List<Place> places = new ArrayList<Place>();
    private Map<LocalDateTime, Map<String, Map<String, Integer>>> logData = new HashMap<LocalDateTime, Map<String, Map<String, Integer>>>();

    public CentralManager() {
        Place p1 = new Place("Washroom", 25.431993, 81.770237);
        Place p2 = new Place("Lecture Room 1", 25.432424, 81.770116);
        Place p3 = new Place("HOD Room", 25.432150, 81.770746);
        this.places.add(p1);
        this.places.add(p2);
        this.places.add(p3);
    }

    //Central Manager's getData calls place manager's get data which then calls sensors get data.
    //A Hash <String, <String, Integer>> with the Place name(first string), sensors name and the sensors data that is randomly generated
    public Map<String, Map<String, Integer>> getData() {
        Map<String, Map<String, Integer>> data = new HashMap<String, Map<String, Integer>>();

        for(Place place: this.places) {
            data.put(place.name, place.getData());
        }
        this.logData.put(LocalDateTime.now(), data);
        return data;
    }
    
    public List<Place> getPlaces(){
        return places;
    }

    public Map<LocalDateTime, Map<String, Map<String, Integer>>> getLogs() {
        return this.logData;
    }

    public Map<String, String> checkLevels() {
        Map<String, String> data = new HashMap<String, String>();
        for(Place place: this.places) {
            data.put(place.name, place.checkLevels());
        }
        return data;
    }
}
