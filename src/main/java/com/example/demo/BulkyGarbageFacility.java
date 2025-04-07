package com.example.demo;

public class BulkyGarbageFacility {
    private int latitude;
    private int longitude;
    private String prefecture;
    private String facilityName;

    public BulkyGarbageFacility(int latitude, int longitude, String prefecture, String facilityName) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.prefecture = prefecture;
        this.facilityName = facilityName;
    }

    // Getters and Setters
    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public String getPrefecture() {
        return prefecture;
    }

    public void setPrefecture(String prefecture) {
        this.prefecture = prefecture;
    }

    public String getFacilityName() {
        return facilityName;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    @Override
    public String toString() {
        return "BulkyGarbageFacility{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", prefecture='" + prefecture + '\'' +
                ", facilityName='" + facilityName + '\'' +
                '}';
    }
}
