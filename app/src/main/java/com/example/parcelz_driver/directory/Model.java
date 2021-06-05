package com.example.parcelz_driver.directory;

public class Model {
    String baladia, willaya, type, title, description, uid;
    double latitude, longitude, latitude_destination, longitude_destination;
    String key;

    Model() {

    }

    public String getBaladia() {
        return baladia;
    }

    public void setBaladia(String baladia) {
        this.baladia = baladia;
    }

    public String getWillaya() {
        return willaya;
    }

    public void setWillaya(String willaya) {
        this.willaya = willaya;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude_destination() {
        return latitude_destination;
    }

    public void setLatitude_destination(double latitude_destination) {
        this.latitude_destination = latitude_destination;
    }

    public double getLongitude_destination() {
        return longitude_destination;
    }

    public void setLongitude_destination(double longitude_destination) {
        this.longitude_destination = longitude_destination;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Model(String baladia, String willaya, String type, String title, String description, String uid, double latitude, double longitude, double latitude_destination, double longitude_destination, String key) {
        this.baladia = baladia;
        this.willaya = willaya;
        this.type = type;
        this.title = title;
        this.description = description;
        this.uid = uid;
        this.latitude = latitude;
        this.longitude = longitude;
        this.latitude_destination = latitude_destination;
        this.longitude_destination = longitude_destination;
        this.key = key;
    }
}
