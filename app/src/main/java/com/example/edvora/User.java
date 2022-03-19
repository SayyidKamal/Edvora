package com.example.edvora;

public class User {
    private int station_code;
    private String name, url;

    public User(int station_code, String name, String url) {
        this.station_code = station_code;
        this.name = name;
        this.url = url;
    }

    public int getStation_code() {
        return station_code;
    }

    public void setStation_code(int station_code) {
        this.station_code = station_code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}
