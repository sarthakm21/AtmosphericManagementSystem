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

public class Place {
    public String name;
    public Double latitude;
    public Double longitude;
    private List<Sensor> sensors = new ArrayList<Sensor>();

    public Place(String name, Double latitude, Double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        Sensor s1 = new Sensor("no2");
        Sensor s2 = new Sensor("pm10");
        Sensor s3 = new Sensor("o3");
        Sensor s4 = new Sensor("pm2.5");
        Sensor s5 = new Sensor("hum");
        Sensor s6 = new Sensor("temp");
        this.sensors.add(s1);
        this.sensors.add(s2);
        this.sensors.add(s3);
        this.sensors.add(s4);
        this.sensors.add(s5);
        this.sensors.add(s6);
    }

    public Map<String, Integer> getData() {
        Map<String, Integer> data = new HashMap<String, Integer>();

        for(Sensor sensor: this.sensors) {
            data.put(sensor.type, sensor.getData());
        }

        return data;
    }
}
