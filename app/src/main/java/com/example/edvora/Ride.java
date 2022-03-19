package com.example.edvora;

import java.util.List;

public class Ride {
    private String map_url, state,city, date;
    private int id, destination_station_code, origin_station_code, distance;
    private List<String> station_path;

    public Ride(String map_url, String state, String city, int id, int destination_station_code, int origin_station_code, String date, List<String> station_path) {
        this.map_url = map_url;
        this.state = state;
        this.city = city;
        this.id = id;
        this.destination_station_code = destination_station_code;
        this.origin_station_code = origin_station_code;
        this.date = date;
        this.station_path = station_path;
    }

    public String getMap_url() {
        return map_url;
    }

    public void setMap_url(String map_url) {
        this.map_url = map_url;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDestination_station_code() {
        return destination_station_code;
    }

    public void setDestination_station_code(int destination_station_code) {
        this.destination_station_code = destination_station_code;
    }

    public int getOrigin_station_code() {
        return origin_station_code;
    }

    public void setOrigin_station_code(int origin_station_code) {
        this.origin_station_code = origin_station_code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getDistance() {
        return distance;
    }

    public List<String> getStation_path() {
        return station_path;
    }

    public void setStation_path(List<String> station_path) {
        this.station_path = station_path;
    }
}
