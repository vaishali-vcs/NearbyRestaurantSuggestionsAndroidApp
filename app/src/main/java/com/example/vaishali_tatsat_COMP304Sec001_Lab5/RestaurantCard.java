package com.example.vaishali_tatsat_COMP304Sec001_Lab5;

import com.google.android.gms.maps.model.LatLng;

public class RestaurantCard {
    private String name, type, address;
    private LatLng latLng;

    public RestaurantCard(String name, String type, String address, LatLng latLng) {
        this.name = name;
        this.type = type;
        this.address = address;
        this.latLng = latLng;
    }

    public String getName() {
        return name;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
