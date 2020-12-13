package com.example.simplemarkerapp.db;

public class Model {
    private int id;
    private String title;
    private String latitude,longitude;

    public Model() {
    }

    public Model(int id, String title, String latitude, String longitude) {
        this.id = id;
        this.title = title;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Model(String title, String latitude, String longitude) {
        this.title = title;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
